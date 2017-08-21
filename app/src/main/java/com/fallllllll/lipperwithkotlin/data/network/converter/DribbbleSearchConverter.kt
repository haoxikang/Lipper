package com.fallllllll.lipperwithkotlin.data.network.converter

import com.fallllllll.lipperwithkotlin.data.databean.Images
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.databean.User
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

/**
 * Created by fall on 2017/7/5/005.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class DribbbleSearchConverter private constructor() : Converter<ResponseBody, List<ShotBean>> {
    private val HOST = "https://dribbble.com"
    private val PATTERN_PLAYER_ID: Pattern = Pattern.compile("users/(\\d+?)/", Pattern.DOTALL)
    private val DATE_FORMAT: SimpleDateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)


  class Factory : Converter.Factory() {
        override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
            return DribbbleSearchConverter()
        }
  }

    override fun convert(value: ResponseBody): List<ShotBean> {
        val shotElements = Jsoup.parse(value.string(), HOST).select("li[id^=screenshot]")
        val shots = ArrayList<ShotBean>()
        shotElements.forEach {
            shots.add(parseShot(it, DATE_FORMAT))
        }
        return shots
    }

    private fun parseShot(element: Element, dataFormat: SimpleDateFormat): ShotBean {
        val descriptionBlock = element.select("a.dribbble-over").first()
        var description = descriptionBlock.select("span.comment").text().trim()
        if (!description.isNullOrEmpty()) {
            description = "<p>$description</p>"
        }
        var imaUrl = element.select("img").first().attr("src")
        if (imaUrl.contains("_teaser.")) imaUrl = imaUrl.replace("_teaser.", ".")
        var createAt: Date? = null
        try {
            createAt = dataFormat.parse(descriptionBlock.select("timestamp").first().text())
        } catch (e: Exception) {
        }
        return ShotBean(id = element.id().replace("screenshot-", "").toLong()
                , htmlUrl = HOST + element.select("a.dribbble-link").first().attr("href")
                , title = descriptionBlock.select("strong").first().text()
                , description = description
                , images = Images(imaUrl, null, null)
                , animated = element.select("div.gif-indicator").first() != null
                , createdAt = createAt
                , likesCount = element.select("li.views").first().child(0)
                .text().replace(",".toRegex(), "").toInt()
                , user = parsePlayer(element.select("h2").first())
        )
    }

    private fun parsePlayer(element: Element): User {
        val userBlock = element.select("a.url").first()
        var avatarUrl = userBlock.select("img.photo").first().attr("src")
        if (avatarUrl.contains("/mini/")) {
            avatarUrl = avatarUrl.replace("/mini/", "/normal/")
        }
        val matchId = PATTERN_PLAYER_ID.matcher(avatarUrl)
        var id = -1L
        if (matchId.find() && matchId.groupCount() == 1) {
            id = matchId.group(1).toLong()
        }
        val slashUsername = userBlock.attr("href")
        val username = if (slashUsername.isNullOrEmpty()) null else slashUsername.substring(1)
        return User(id = id, name = userBlock.text(), username = username, htmlUrl = HOST + slashUsername, avatarUrl = avatarUrl, pro = element.select("span.badge-pro").size > 0)
    }
}