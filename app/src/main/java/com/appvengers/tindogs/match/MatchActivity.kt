package com.appvengers.tindogs.match

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection

class MatchActivity : BaseActivity(), MatchContract.View {

    companion object {

        val DOG_MATCHING_ID = "DOG_MATCHING_ID"
        fun intent(context: Context, dog: Dog): Intent {
            val intent = Intent(context, MatchActivity::class.java)

            intent.putExtra(DOG_MATCHING_ID, dog._id)

            return intent
        }
    }

    private lateinit var presenter: MatchContract.Presenter

    private var cardStackView : CardStackView? = null
    private var adapter : DogMatchCardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_match)

        associatePresenter()
        setup()
        presenter.getDogsList()
        reload()
    }

    private fun associatePresenter()
    {
        presenter = MatchPresenter(this,ObjectInjector.buildDogMatchInteractor(this))
    }

    private fun reload() {
        cardStackView?.visibility = View.GONE
        Handler().postDelayed({
            adapter = createDogMatchCardAdapter()
            cardStackView?.setAdapter(adapter)
            cardStackView?.visibility = View.VISIBLE
        }, 1000)
    }

    private fun createDogMatchCardAdapter(): DogMatchCardAdapter {
        val adapter = DogMatchCardAdapter(this)
        //adapter.addAll(createDogsList())
        return adapter
    }



    private fun setup() {

        cardStackView = findViewById(R.id.activity_match_card_stack_view) as CardStackView

        cardStackView?.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
                Log.d("CardStackView", "onCardDragging")
            }

            override fun onCardSwiped(direction: SwipeDirection) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString())
                Log.d("CardStackView", "topIndex: " + cardStackView?.getTopIndex())
                if (cardStackView?.getTopIndex() === adapter?.count?.minus(1)) {
                    Log.d("CardStackView", "Paginate: " + cardStackView?.getTopIndex())
                    presenter.getDogsList()
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

    private fun paginate(dogs: MutableList<Dog>) {
        Log.d("MatchActivity","Paginate")
        cardStackView?.setPaginationReserved()
        adapter?.addAll(dogs)
        adapter?.notifyDataSetChanged()
    }

    override fun updateDogsList(dogs: MutableList<Dog>) {
        paginate(dogs)
    }
}
