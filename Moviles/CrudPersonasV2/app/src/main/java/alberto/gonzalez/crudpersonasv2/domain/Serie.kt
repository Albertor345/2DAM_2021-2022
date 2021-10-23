package alberto.gonzalez.crudpersonasv2.domain


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Serie(
    val name: String,
    val resourceURI: String
)