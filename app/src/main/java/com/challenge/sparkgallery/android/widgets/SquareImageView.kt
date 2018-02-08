package com.challenge.sparkgallery.android.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by 805640 on 06.02.2018.
 */
class SquareImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}