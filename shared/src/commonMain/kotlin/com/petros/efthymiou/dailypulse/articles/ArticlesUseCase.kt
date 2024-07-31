package com.petros.efthymiou.dailypulse.articles

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
                article.publishedAt,
                imageURL = article.imageURL ?: "placeholder"
            )
        }
}