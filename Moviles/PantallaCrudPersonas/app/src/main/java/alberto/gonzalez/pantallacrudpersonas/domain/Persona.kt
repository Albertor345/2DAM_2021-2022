package alberto.gonzalez.pantallacrudpersonas.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Persona(var id: Int, var name: String, var age: Int, var gender: String) : Parcelable{

    override fun toString(): String {
        return "$name, $gender de $age años"
    }


}


