package alberto.gonzalez.crudpersonasv2.config

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object ConfigMoshi {
    private var moshi: Moshi? = null

    fun getInstance(): Moshi? {
        if (moshi == null) {
            moshi = Moshi.Builder()
                .add(LocalDateTimeAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
        }
        return moshi
    }

    class LocalDateTimeAdapter {
        @ToJson
        fun toJson(value: LocalDateTime): String {
            return FORMATTER.format(value)
        }

        @FromJson
        fun fromJson(value: String): LocalDateTime {
            return LocalDateTime.from(FORMATTER.parse(value))
        }

        companion object {
            private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss-SSSS")
        }
    }
}