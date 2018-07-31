package com.glowtest.util

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.SpannableString
import java.util.*


object Utility {


    fun multiSizeText(top: String, bottom: String): SpannableString {
        val spannableString = SpannableString(top+"\n"+bottom)
        spannableString.setSpan(RelativeSizeSpan(1.2f), 0, top.length, 0) // set size
        return spannableString
    }

    fun randColor() : Int{
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}