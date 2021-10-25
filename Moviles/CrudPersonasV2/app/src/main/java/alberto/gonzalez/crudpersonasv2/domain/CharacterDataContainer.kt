package alberto.gonzalez.crudpersonasv2.domain


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    @Json(name = "results")
    val characters: MutableList<Character>
)