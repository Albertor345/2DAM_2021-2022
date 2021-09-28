package alberto.gonzalez.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val password = "root"
    private val user = "root"
    lateinit var button: Button
    lateinit var viewUser: EditText
    lateinit var viewPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.loginButton)
        viewUser = findViewById(R.id.editTextUser)
        viewPass = findViewById(R.id.editTextPassword)
        println(viewPass)
        setListeners()
    }

    private fun setListeners() {

        button.setOnClickListener {
            if (viewUser.text.toString() == user && viewPass.text.toString() == password)
                Toast.makeText(this, "Bienvenido $user", Toast.LENGTH_LONG).show()
             else
                Toast.makeText(this, "Usuario o Password no validos", Toast.LENGTH_SHORT).show()

            viewUser.setText("")
            viewPass.setText("")
        }
    }

}