package com.glowtest.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.glowtest.R
import com.glowtest.model.CollectionModel
import com.glowtest.presenter.CollectionPresenter
import kotlinx.android.synthetic.main.activity_main.*

class CollectionListingActivity : AppCompatActivity(), CollectionView {

    private val listCollection = ArrayList<CollectionModel>()
    private var adapter : CollectionListingAdapter? = null
    private var collectionPresenter : CollectionPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        collectionPresenter = CollectionPresenter(this)
        adapter = CollectionListingAdapter(this, listCollection)

        activity_main_recycle_view.layoutManager = LinearLayoutManager(this)
        activity_main_recycle_view.adapter = adapter
    }


    override fun updateListing(listCollection: ArrayList<CollectionModel>) {
        this.listCollection.addAll(listCollection)
        adapter?.notifyDataSetChanged()
    }
}
