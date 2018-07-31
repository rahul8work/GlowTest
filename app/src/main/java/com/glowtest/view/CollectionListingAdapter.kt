package com.glowtest.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.glowtest.R
import com.glowtest.model.CollectionModel
import com.glowtest.util.IMAGE_URL
import com.glowtest.util.ITEM_COLLECTION
import com.glowtest.util.ITEM_HEADER
import com.glowtest.util.Utility
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import java.util.*


class CollectionListingAdapter(private val mContext: Context, private val list: List<CollectionModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return when (viewType) {
            ITEM_HEADER -> HeaderViewHolder(mInflater.inflate(R.layout.collection_item_header, null))
            else -> CollectionViewHolder(mInflater.inflate(R.layout.collection_item_collection, null))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(list[position].type){
            ITEM_HEADER -> {
                (holder as HeaderViewHolder).bindHeaderView(list[position])
            }

            ITEM_COLLECTION -> {
                (holder as CollectionViewHolder).bindCollectionView(list[position])
            }
        }

    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val latestFilter : TextView = itemView.findViewById<TextView>(R.id.collection_item_header_filter_latest)
        private val lowestFilter: TextView = itemView.findViewById<TextView>(R.id.collection_item_header_filter_lowest)
        private val bestFilter : TextView = itemView.findViewById<TextView>(R.id.collection_item_header_filter_best)


        fun bindHeaderView(collectionModel: CollectionModel) {

            latestFilter.text = Utility.multiSizeText("Latest Arrivals","FRESH STOCK")
            lowestFilter.text = Utility.multiSizeText("Lowest Prices","Upto 90% OFF")
            bestFilter.text = Utility.multiSizeText("Best Selling","Our Top Products")

            latestFilter.setOnClickListener { updateSelectedView(latestFilter) }
            lowestFilter.setOnClickListener { updateSelectedView(lowestFilter) }
            bestFilter.setOnClickListener { updateSelectedView(bestFilter) }
        }

        /*
        * Setting if view is selected or not.
        * On this basis selector changes background of view
        *
        * */
        private fun updateSelectedView(view: TextView){
            latestFilter.isSelected = view == latestFilter
            lowestFilter.isSelected = view == lowestFilter
            bestFilter.isSelected = view == bestFilter
        }
    }

    class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val collectionBadge = itemView.findViewById<TextView>(R.id.collection_item_collection_main_image_badge)
        private val collectionBanner = itemView.findViewById<TextView>(R.id.collection_item_collection_banner)
        private val collectionTitle = itemView.findViewById<TextView>(R.id.collection_item_collection_title)

        private val mainImage = itemView.findViewById<ImageView>(R.id.collection_item_collection_main_image)

        fun bindCollectionView(collectionModel: CollectionModel) {
            val transformation = RoundedTransformationBuilder()
                    .cornerRadiusDp(5f)
                    .oval(false)
                    .build()
            Picasso.get().load(IMAGE_URL+Random().nextInt(1000)).fit().transform(transformation).into(mainImage)

            ViewCompat.setBackgroundTintList(collectionBadge, ColorStateList.valueOf(Utility.randColor()))
            ViewCompat.setBackgroundTintList(collectionBanner, ColorStateList.valueOf(Utility.randColor()))

            collectionTitle.text = Utility.multiSizeText("Product Name", "Its description")
            collectionBadge.text = Utility.multiSizeText("50%", "OFF")
        }
    }
}