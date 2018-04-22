package com.appvengers.tindogs.match

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : BaseActivity(), MatchContract.View {

    companion object {

        const val DOG_MATCHING_ID = "DOG_MATCHING_ID"
        fun intent(context: Context, dog: Dog): Intent {
            val intent = Intent(context, MatchActivity::class.java)

            intent.putExtra(DOG_MATCHING_ID, dog._id)

            return intent
        }
    }

    private lateinit var presenter: MatchContract.Presenter

    private var dogId : String? = null
    private var userId : String? =  null
    private var token : String? = null

    private var cardStackView : CardStackView? = null
    private var adapter : DogMatchCardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        cardStackView?.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
                Log.d("CardStackView", "onCardDragging")
            }

            override fun onCardSwiped(direction: SwipeDirection) {

                if (cardStackView?.getTopIndex() === adapter?.count?.minus(1)) {
                    Log.d("CardStackView", "Paginate: " + cardStackView?.topIndex)
                    presenter.getDogsList(userId!!, dogId!!, token!!)
                }

                Log.d("CardStackView", "onCardSwiped: " + direction.toString())
                if (direction.toString() == "Right") {
                    //hacemos like al perrete que estamos viendo
                    presenter.newDogLike(userId!!,adapter?.getItem(cardStackView?.topIndex!!)!!,dogId!!,token!!)

                } else if(direction.toString() == "Left") {
                    //hacemos dislike al perrete que estamos viendo
                    presenter.newDogDislike(userId!!,adapter?.getItem(cardStackView?.topIndex!!)!!,dogId!!, token!!)
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

    }

    private fun reload() {
        cardStackView?.visibility = View.GONE
        cardStackView?.setAdapter(adapter)
        cardStackView?.visibility = View.VISIBLE
        userId =  getTokenAndUserId()?.userId
        token = getTokenAndUserId()?.token
        presenter.getDogsList(userId!!,dogId!!, token!!)
    }

    private fun paginate(dogs: MutableList<Dog>) {
        cardStackView?.setPaginationReserved()
        adapter?.addAll(dogs)
        adapter?.notifyDataSetChanged()
    }

    override fun updateDogsList(dogs: MutableList<Dog>) {
        paginate(dogs)
    }

    override fun onMatchViewError(msg: String) {
        this.setError(match_main_view, msg)
    }
}
