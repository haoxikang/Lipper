package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.content.res.ColorStateList
import android.os.Build
import android.support.annotation.ColorInt
import android.text.*
import android.text.style.URLSpan
import com.fallllllll.lipperwithkotlin.utils.TouchableUrlSpan
import okhttp3.HttpUrl

/**
 * Created by fall on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
fun String.numberToK(): String {
    var s = this
    if (!this.isEmpty() && TextUtils.isDigitsOnly(this)) {
        val a = Integer.parseInt(this)
        if (a > 999) {
            s = (a / 1000).toString() + "k"
        }
    }
    return s
}

//fun String.parseDribbbleHtml(linkTextColor: ColorStateList, @ColorInt linkHighlightColor: Int): Spanned {
//    val ssb = parseHtml(this, linkTextColor, linkHighlightColor)
//val urlSpans = ssb.getSpans(0,ssb.length,TouchableUrlSpan::class.java)
//    urlSpans.forEach {
//        val start = ssb.getSpanStart(it)
//        if (ssb.subSequence(start,start+1).toString()=="@"){
//            val end = ssb.getSpanEnd(it)
//            ssb.removeSpan(it)
//            val url = HttpUrl.parse(it.getURL())
//            var playerId =-1l
//        }
//    }
//}

fun parseHtml(input: String,
              linkTextColor: ColorStateList,
              @ColorInt linkHighlightColor: Int): SpannableStringBuilder {
    var spanned = fromHtml(input)
    while (spanned[spanned.length - 1] == '\n') {
        spanned = spanned.delete(spanned.length - 1, spanned.length)
    }
    return linkifyPlainLinks(spanned, linkTextColor, linkHighlightColor)
}

private fun linkifyPlainLinks(input: CharSequence, linkTextColor: ColorStateList, @ColorInt linkHighlightColor: Int): SpannableStringBuilder {
    val plainLinks = SpannableString(input)
    val urlSpans = plainLinks.getSpans(0, plainLinks.length, URLSpan::class.java)
    val ssb = SpannableStringBuilder(input)
    urlSpans.forEach {
        ssb.removeSpan(urlSpans)
        ssb.setSpan(TouchableUrlSpan(it.url, linkTextColor, linkHighlightColor),
                plainLinks.getSpanStart(it),
                plainLinks.getSpanEnd(it),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    return ssb
}

private fun fromHtml(input: String): SpannableStringBuilder {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY) as SpannableStringBuilder
    } else {
        Html.fromHtml(input) as SpannableStringBuilder
    }
}

