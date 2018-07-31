package com.glowtest.presenter

import com.glowtest.model.CollectionModel
import com.glowtest.util.ITEM_COLLECTION
import com.glowtest.util.ITEM_HEADER
import com.glowtest.view.CollectionView

class CollectionPresenter(val collectionView: CollectionView) {

    private val listCollection = ArrayList<CollectionModel>()

    init {
        fetchCollectionListing()
    }

    /*
    * Mock
    * Fetching Data from server
    * */
    private fun fetchCollectionListing() {
        listCollection.add(CollectionModel(ITEM_HEADER,"",""))
        for (i in 1..500 ){
            listCollection.add(CollectionModel(ITEM_COLLECTION,"",""))
        }
        collectionView.updateListing(listCollection)
    }

}