package alberto.gonzalez.pantallacrudpersonas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    private lateinit var list: List<Persona>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = mutableListOf(Persona(1, "Melvin", 22));
    }
}