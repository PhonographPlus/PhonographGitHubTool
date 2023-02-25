package tools.phonograph

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Release(
    @SerialName("tag_name")
    val tagName: String,
    @SerialName("git_hash")
    var hash: String = "",
    @SerialName("name")
    val name: String,
    @SerialName("prerelease")
    val preRelease: Boolean,
    @SerialName("draft")
    val draft: Boolean,
    @SerialName("body")
    val body: String,
    @SerialName("url")
    val url: String,
    @SerialName("html_url")
    val htmlUrl: String?,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("published_at")
    val publishedAt: String,
    @SerialName("assets")
    val assets: List<Asset>?,
) {
    @Serializable
    data class Asset(
        @SerialName("name")
        val name: String,
        @SerialName("content_type")
        val contentType: String,
        @SerialName("size")
        val size: Long,
        @SerialName("browser_download_url")
        val browserDownloadUrl: String,
        @SerialName("download_count")
        val downloadCount: Long,
        @SerialName("label")
        val label: String?,
        @SerialName("created_at")
        val createdAt: String,
        @SerialName("updated_at")
        val updatedAt: String?,
        @SerialName("url")
        val url: String,
    )
}