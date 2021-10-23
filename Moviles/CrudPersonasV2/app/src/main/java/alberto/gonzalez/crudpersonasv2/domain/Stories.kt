package alberto.gonzalez.crudpersonasv2.domain


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stories(
    val available: Int,
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Story>,
    val returned: Int
)