package com.zainco.library.threading

import org.jsoup.Jsoup

fun main() {
    val words: List<String> = getWebsiteText().split(" ")
    val eachCountMap: Map<String, Int> = words.filter {
        (it.trim() == "" && it.trim() == " ").not()
    }.groupingBy { it }.eachCount()
   /* eachCountMap.forEach {
        println(eachCountMap)
    }*/
    println(eachCountMap)
}

fun getWebsiteText(): String {
    val doc = Jsoup.connect("https://instabug.com").get()
    val webSiteText = doc.body().text().replace(("[^\\w\\d ]").toRegex(), "").replace("  "," ")
    return webSiteText
}