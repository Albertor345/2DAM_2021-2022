package alberto.gonzalez.crudpersonasv2.domain


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<Url>,
    @Json(name = "thumbnail")
    val image: Image,
    @Json(name = "comics")
    val comicsWrapper: Comics,
    @Json(name = "events")
    val eventsWrapper: Events,
    @Json(name = "series")
    val seriesWrapper: Series,
    @Json(name = "stories")
    val storiesWrapper: Stories
)