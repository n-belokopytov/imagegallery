package com.challenge.sparkgallery.android.features.gallery

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import com.challenge.sparkgallery.R
import com.challenge.sparkgallery.android.widgets.SquareImageView
import com.challenge.sparkgallery.data.gallery.model.Image
import com.squareup.picasso.Picasso
import java.util.*


/**
 * Created by 805640 on 06.02.2018.
 */
class GalleryAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dateFormat = DateFormat.getMediumDateFormat(context)


    companion object {
        val IMAGE_TYPE = 0;
        val HEADER_TYPE = 1;
    }

    var items: List<Any> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return when (viewType) {
            IMAGE_TYPE -> {
                val imageView = inflater.inflate(R.layout.image_gallery_item, parent, false)
                ImageItemViewHolder(imageView)
            }
            else -> {
                val headerView = inflater.inflate(R.layout.header_gallery_item, parent, false)
                DateHeaderViewHolder(headerView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
            if (items[position] is Image) {
                IMAGE_TYPE
            } else {
                HEADER_TYPE
            }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            HEADER_TYPE -> (holder as DateHeaderViewHolder).setDateHeader(dateFormat.format(items[position] as Date))
            IMAGE_TYPE -> (holder as ImageItemViewHolder).setImage((items[position] as Image).imageUrl)
        }
    }

    fun setImages(imagesNewSet: List<Image>) {
        val newItemSet = ImageGalleryItemBuilder(imagesNewSet).build()
        if (items.isNotEmpty()) {
            val delta = newItemSet - items
            val itemSetSizeOld = items.size
            items = newItemSet
            if (newItemSet.indexOf(delta.first()) < itemSetSizeOld - 1) {
                notifyDataSetChanged()
            } else {
                notifyItemRangeInserted(itemSetSizeOld - 1, newItemSet.size - itemSetSizeOld)
            }
        } else {
            items = newItemSet
            notifyItemRangeInserted(0, items.size)
        }
    }

    class DateHeaderViewHolder(headerLayout: View) : RecyclerView.ViewHolder(headerLayout) {
        private val textView = headerLayout.findViewById<TextView>(R.id.date_header)

        fun setDateHeader(date: String) {
            textView.text = date
        }
    }

    class ImageItemViewHolder(imageViewLayout: View) : RecyclerView.ViewHolder(imageViewLayout) {
        private val imageView = imageViewLayout.findViewById<SquareImageView>(R.id.image_view)

        fun setImage(imageUrl: String) {
            Picasso.with(imageView.context).load(imageUrl).placeholder(R.drawable.ic_landscape).into(imageView)
        }
    }
}