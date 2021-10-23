package alberto.gonzalez.crudpersonasv2.domain


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comic(
    val name: String,
    val resourceURI: String
)