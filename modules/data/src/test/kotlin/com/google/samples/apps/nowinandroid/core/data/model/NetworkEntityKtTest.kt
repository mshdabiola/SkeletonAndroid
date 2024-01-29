/*
 *abiola 2024
 */

package com.google.samples.apps.nowinandroid.core.data.model

import com.google.samples.apps.nowinandroid.core.network.model.NetworkNewsResource
import com.google.samples.apps.nowinandroid.core.network.model.NetworkNewsResourceExpanded
import com.google.samples.apps.nowinandroid.core.network.model.NetworkTopic
import kotlinx.datetime.Instant
import org.junit.Test
import kotlin.test.assertEquals

class NetworkEntityKtTest {

    @Test
    fun network_topic_can_be_mapped_to_topic_entity() {
        val networkModel = NetworkTopic(
            id = "0",
            name = "Test",
            shortDescription = "short description",
            longDescription = "long description",
            url = "URL",
            imageUrl = "image URL",
        )
        val entity = networkModel.asEntity()

        assertEquals("0", entity.id)
        assertEquals("Test", entity.name)
        assertEquals("short description", entity.shortDescription)
        assertEquals("long description", entity.longDescription)
        assertEquals("URL", entity.url)
        assertEquals("image URL", entity.imageUrl)
    }

    @Test
    fun network_news_resource_can_be_mapped_to_news_resource_entity() {
        val networkModel =
            NetworkNewsResource(
                id = "0",
                title = "title",
                content = "content",
                url = "url",
                headerImageUrl = "headerImageUrl",
                publishDate = Instant.fromEpochMilliseconds(1),
                type = "Article 📚",
            )
        val entity = networkModel.asEntity()

        assertEquals("0", entity.id)
        assertEquals("title", entity.title)
        assertEquals("content", entity.content)
        assertEquals("url", entity.url)
        assertEquals("headerImageUrl", entity.headerImageUrl)
        assertEquals(Instant.fromEpochMilliseconds(1), entity.publishDate)
        assertEquals("Article 📚", entity.type)

        val expandedNetworkModel =
            NetworkNewsResourceExpanded(
                id = "0",
                title = "title",
                content = "content",
                url = "url",
                headerImageUrl = "headerImageUrl",
                publishDate = Instant.fromEpochMilliseconds(1),
                type = "Article 📚",
            )

        val entityFromExpanded = expandedNetworkModel.asEntity()

        assertEquals("0", entityFromExpanded.id)
        assertEquals("title", entityFromExpanded.title)
        assertEquals("content", entityFromExpanded.content)
        assertEquals("url", entityFromExpanded.url)
        assertEquals("headerImageUrl", entityFromExpanded.headerImageUrl)
        assertEquals(Instant.fromEpochMilliseconds(1), entityFromExpanded.publishDate)
        assertEquals("Article 📚", entityFromExpanded.type)
    }
}
