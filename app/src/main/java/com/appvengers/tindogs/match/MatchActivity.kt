package com.appvengers.tindogs.match

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog.Builder
import android.util.Log
import android.view.View
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.appvengers.tindogs.router.Router
import com.appvengers.utils.LogTindogs
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : BaseActivity(), MatchContract.View {

    companion object {

        const val DOG_MATCHING_ID = "DOG_MATCHING_ID"
        const val SWIPE_LEFT = "Left"
        const val SWIPE_RIGHT = "Right"
        fun intent(context: Context, dog: Dog): Intent {
            val intent = Intent(context, MatchActivity::class.java)

            intent.putExtra(DOG_MATCHING_ID, dog._id)

            return intent
        }
    }

    private lateinit var presenter: MatchContract.Presenter

    private lateinit var dogId : String
    private lateinit var userId : String
    private lateinit var token : String
    private lateinit var selectedDog : Dog

    private lateinit var cardStackView : CardStackView
    private var adapter : DogMatchCardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle("Matches")
        setContentView(R.layout.activity_match)
        dogId = intent.getStringExtra(DOG_MATCHING_ID)

        associatePresenter()
        setup()
        reload()

    }

    private fun associatePresenter()
    {
        presenter = MatchPresenter(this,
                ObjectInjector.buildDogMatchInteractor(this),
                ObjectInjector.buildNewDogLikeInteractor(this),
                ObjectInjector.buidNewDogDislikeInteractor(this))
    }

    private fun setup() {
        adapter = DogMatchCardAdapter(this)
        cardStackView = findViewById(R.id.activity_match_card_stack_view)

        cardStackView.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
                Log.d("CardStackView", "onCardDragging")
            }

            override fun onCardSwiped(direction: SwipeDirection) {

                if (cardStackView.topIndex == adapter?.count?.minus(1)) {
                    Log.d("CardStackView", "Paginate: " + cardStackView.topIndex)
                    presenter.getDogsList(userId, dogId, token)
                }

                Log.d("CardStackView", "onCardSwiped: " + direction.toString())
                when(direction.toString()) {
                    SWIPE_RIGHT -> {
                        //hacemos like al perrete que acabamos de ver
                        onNewDogLike(adapter?.getItem(cardStackView.topIndex - 1)!!)
                    }
                    SWIPE_LEFT -> {
                        //hacemos dislike al perrete que acabamos de ver
                        onNewDogDislike(adapter?.getItem(cardStackView.topIndex - 1)!!)

                    }
                }
            }

            override fun onCardReversed() {
                Log.d("CardStackView", "onCardReversed")
            }

            override fun onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin")
            }

            override fun onCardClicked(index: Int) {
                Log.d("CardStackView", "onCardClicked: " + index)
            }
        })

        like_button_match.setOnClickListener {
            if(cardStackView.topIndex != adapter?.count) {
                swipeRight()
                onNewDogLike(adapter?.getItem(cardStackView.topIndex)!!)

            }
        }

        dislike_button_match.setOnClickListener {
            if(cardStackView.topIndex != adapter?.count) {
                swipeLeft()
                var dogSelected = adapter?.getItem(cardStackView.topIndex)!!
                onNewDogDislike(dogSelected)
            }
        }

    }

    private fun reload() {
        cardStackView.visibility = View.GONE
        cardStackView.setAdapter(adapter)
        cardStackView.visibility = View.VISIBLE
        userId =  getTokenAndUserId()?.userId!!
        token = getTokenAndUserId()?.token!!
        presenter.getDogsList(userId,dogId, token)
    }

    private fun paginate(dogs: MutableList<Dog>) {
        cardStackView.setPaginationReserved()
        adapter?.addAll(dogs)
        adapter?.notifyDataSetChanged()
    }

    override fun updateDogsList(dogs: MutableList<Dog>) {
        paginate(dogs)
    }

    override fun onMatchView(msg: String) {
        Builder(this)
            .setTitle(msg)
            .setPositiveButton("Seguir deslizando", { _ : DialogInterface, _    : Int ->

            })
            .setNegativeButton("Ir al perfil" , { _ : DialogInterface, _ : Int ->

                Router.navigateToDetailDogProfile(this, "","")
            })
            .show()
    }

    override fun onMatchViewError(msg: String) {
        this.setError(match_main_view, msg)
    }

    private fun swipeLeft() {

        val target = cardStackView.topView
        val targetOverlay = cardStackView.topView.overlayContainer

        val rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", -10f))
        rotation.duration = 200
        val translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, -2000f))
        val translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
        translateX.startDelay = 100
        translateY.startDelay = 100
        translateX.duration = 500
        translateY.duration = 500
        val cardAnimationSet = AnimatorSet()
        cardAnimationSet.playTogether(rotation, translateX, translateY)

        val overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f)
        overlayAnimator.duration = 200
        val overlayAnimationSet = AnimatorSet()
        overlayAnimationSet.playTogether(overlayAnimator)

        cardStackView.swipe(SwipeDirection.Left, cardAnimationSet, overlayAnimationSet)
    }

    private fun swipeRight() {

        val target = cardStackView.topView
        val targetOverlay = cardStackView.topView.overlayContainer

        val rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", 10f))
        rotation.duration = 200
        val translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f))
        val translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
        translateX.startDelay = 100
        translateY.startDelay = 100
        translateX.duration = 500
        translateY.duration = 500
        val cardAnimationSet = AnimatorSet()
        cardAnimationSet.playTogether(rotation, translateX, translateY)

        val overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f)
        overlayAnimator.duration = 200
        val overlayAnimationSet = AnimatorSet()
        overlayAnimationSet.playTogether(overlayAnimator)

        cardStackView.swipe(SwipeDirection.Right, cardAnimationSet, overlayAnimationSet)
    }

    private fun onNewDogLike(dog: Dog) {
        selectedDog = dog
        presenter.newDogLike(userId,dog,dogId,token)
    }

    private fun onNewDogDislike(dog: Dog) {
        presenter.newDogDislike(userId,dog,dogId,token)
    }
}