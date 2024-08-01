package com.petros.efthymiou.dailypulse.articles

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(
    private val articlesService: ArticlesService
) {
    suspend fun getArticles() : List<Article> {
        val rawArticles = articlesService.fetchArticles();
        return mapArticles(rawArticles)
    }

    private fun mapArticles(rawArticles: List<ArticleRaw>): List<Article> =
        rawArticles.map { article ->
            Article(
                article.title ,
                description = article.description ?: "No description available",
                date = getDaysAgo(article.publishedAt),
                imageURL = article.imageURL ?: "placeholder"
            )
        }

    private fun getDaysAgo(date: String) : String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "today"
        }
        return result
    }
}