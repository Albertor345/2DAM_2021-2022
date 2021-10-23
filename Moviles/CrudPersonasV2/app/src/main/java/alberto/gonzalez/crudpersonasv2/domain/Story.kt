package alberto.gonzalez.crudpersonasv2.domain


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Story(
    val name: String,
    val resourceURI: String,
    val type: String
)