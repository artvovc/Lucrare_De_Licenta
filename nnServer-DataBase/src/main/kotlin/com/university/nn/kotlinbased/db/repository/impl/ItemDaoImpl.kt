package com.university.nn.kotlinbased.db.repository.impl

import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import com.university.nn.kotlinbased.db.model.Feed
import com.university.nn.kotlinbased.db.model.FeedData
import com.university.nn.kotlinbased.db.model.Item
import com.university.nn.kotlinbased.db.repository.FeedDataRepository
import com.university.nn.kotlinbased.db.repository.FeedRepository
import com.university.nn.kotlinbased.db.repository.ItemDao
import com.university.nn.kotlinbased.db.repository.ItemRepository
import com.university.nn.kotlinbased.utils.Container
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.net.URL
import java.util.*

@Repository
open class ItemDaoImpl
@Autowired
constructor(val itemRepository: ItemRepository, val feedRepository: FeedRepository,val feedDataRepository: FeedDataRepository) : ItemDao {

    override fun searchByTag(tag: String): List<FeedData> {
      return feedDataRepository.findByTags(tag)
    }

    override fun saveFeedData(fedData: FeedData) {
        feedDataRepository.insert(fedData)
    }

    companion object {
        val USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0"
        val TIMEOUT = 10000
    }

    override fun getItems(flinks: List<String>): List<Item> {
        val links = flinks.toMutableList()
        val list: MutableList<Item> = itemRepository.findByFeedLinkInOrderByCreatedDateDesc(links).toMutableList()
        links.removeAll(list.map(Item::feedLink).toSet())
        val addLast = links.flatMap { url ->
            SyndFeedInput().build(XmlReader(URL(url))).entries
                    .map { entry ->
                        Item(url, entry.title, entry.author, entry.link, entry.description.value, entry.publishedDate)
                    }
        }
        list.addAll(addLast)
        itemRepository.save(addLast)
        return list
    }

    override fun searchItems(key: String): Container {
        val container: Container = Container()

        val feed = feedRepository.findByKey(key)
        if (feed!=null && feed.feeds.isNotEmpty()) {
            with(container) {
                container.iconUrl = feed.iconUrl
                container.feeds = ArrayList(feed.feeds)
            }
            return container
        }

        val baseUrl = "http://www.google.com/search?num=5&q=$key"
        val document = Jsoup.connect(baseUrl).userAgent(USER_AGENT).timeout(TIMEOUT).get()
        val cite = document.getElementsByTag("cite")
                .map { cite -> cite.html().replace("<b>|</b>".toRegex(), "") }
                .filter { cite -> cite.matches(("""^(www|https://www|https://|https://blog)?[.]?$key.*[.](com|ru|en|md|ua).*""").toRegex()) && !cite.contains(("?")) }
        cite.forEach { cite ->
            var url = cite
            if (!cite.contains("http"))
                url = "http://$url"

            val doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get()
            val icon = doc.head().select("link[href~=.*\\.(ico|png)]").first()
            if (icon != null) {
                val iconlink = icon.attr("href")
                container.iconUrl = if (iconlink.contains("http")) iconlink else url + iconlink
            }
            if (doc.html().contains("application/rss+xml")) {
                val link = doc.head().select("link[type=application/rss+xml]").attr("href")
                container.feeds.add(if (link.contains("http".toRegex())) link else url + link)
            } else {
                deepExtractFeed(container, doc)
            }

        }

        if (container.feeds.isNotEmpty())
            feedRepository.save(Feed(key, container.iconUrl, container.feeds))

        return container
    }

    private fun deepExtractFeed(container: Container, doc: Document) {
        val hrefs = doc.select("a[href]")
                .map { href -> href.attr("href") }
                .filter { href -> href.contains("feed".toRegex()) && !href.contains("feedback") && href.contains("http".toRegex()) && !href.contains("(yahoo|facebook|twitter)".toRegex()) }
        hrefs.forEach {
            href ->
            val docFeed = Jsoup.connect(href).userAgent(USER_AGENT).timeout(TIMEOUT).get()
            if (docFeed.html().contains("application/rss+xml"))
                container.feeds.add(href)
            else {
                deepExtractFeed(container, docFeed)
            }

        }
    }
}