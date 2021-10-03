package alberto.gonzalez.pantallacrudpersonas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var list: MutableList<Persona>
    private lateinit var buttonPrevious: Button
    private lateinit var buttonNext: Button
    private lateinit var buttonAdd: Button
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button
    private lateinit var checkBox: CheckBox
    private lateinit var textInputName: TextInputLayout
    private lateinit var textInputAge: TextInputLayout
    private lateinit var radioGroup: RadioGroup
    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonPrevious = this.findViewById(R.id.buttonPrevious)
        buttonNext = this.findViewById(R.id.buttonNext)
        buttonAdd = this.findViewById(R.id.buttonAdd)
        buttonAdd.isEnabled = false
        buttonDelete = this.findViewById(R.id.buttonDelete)
        buttonUpdate = this.findViewById(R.id.buttonUpdate)
        buttonUpdate.isEnabled = false
        textInputName = this.findViewById(R.id.personName)
        textInputName.isEnabled = false
        textInputAge = this.findViewById(R.id.personAge)
        textInputAge.isEnabled = false
        checkBox = this.findViewById(R.id.checkbox)
        radioGroup = this.findViewById(R.id.radioGroup)
        radioGroup.isEnabled = false
        list = mutableListOf()
        setListeners()

    }

    fun setListeners() {
        buttonAdd.setOnClickListener {
            addPerson()
        }
        buttonDelete.setOnClickListener {
            delPerson()
        }
        buttonUpdate.setOnClickListener {
            updatePerson()
        }
        buttonPrevious.setOnClickListener {
            previousPerson()
        }
        buttonNext.setOnClickListener {
            nextPerson()
        }
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            checkBoxListener()
        }
    }

    fun checkBoxListener() {
        buttonAdd.isEnabled = !buttonAdd.isEnabled
        buttonUpdate.isEnabled = !buttonUpdate.isEnabled
        buttonPrevious.isEnabled = !buttonPrevious.isEnabled
        buttonNext.isEnabled = !buttonNext.isEnabled
        buttonDelete.isEnabled = !buttonDelete.isEnabled
        textInputName.isEnabled = !textInputName.isEnabled
        textInputAge.isEnabled = !textInputAge.isEnabled
        radioGroup.isEnabled = !radioGroup.isEnabled
    }

    fun addPerson() {
        try {
            val p = Persona(
                (list.size + 1),
                textInputName.editText?.text.toString(),
                textInputAge.editText?.text.toString().toInt(),
                radioGroup.checkedRadioButtonId
            )
            if (validatePerson(p)) {
                list.add(p)
                Toast.makeText(this, "Persona añadida con exito", Toast.LENGTH_SHORT).show()
                println(p)
                loadPerson(list[0])
                currentIndex = 0
                checkBox.isChecked = false
            } else Toast.makeText(this, "Datos introducidos no validos", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
    }

    fun validatePerson(p: Persona): Boolean {
        return textInputAge.editText?.text.toString()
            .isDigitsOnly() && textInputName.editText?.text.toString().isNotEmpty()
    }

    fun delPerson() {
        list.removeAt(currentIndex)
        if(list.size != 0) loadPerson(list[0])
        Toast.makeText(this, "Persona eliminada", Toast.LENGTH_SHORT).show()
    }

    fun updatePerson() {
        var persona = list[currentIndex]
        persona.name = textInputName.editText?.text.toString()
        persona.age = textInputAge.editText?.text.toString().toInt()
        persona.gender = radioGroup.checkedRadioButtonId
        Toast.makeText(this, "Datos modificados con exito", Toast.LENGTH_SHORT).show()
        checkBox.isEnabled = false
        loadPerson(list[0])
    }

    fun previousPerson() {
        currentIndex--
        buttonPrevious.isEnabled = currentIndex > 0
        buttonNext.isEnabled = true
        var persona = list[currentIndex]
        loadPerson(persona)
    }

    fun nextPerson() {
        currentIndex++
        buttonNext.isEnabled = currentIndex < list.size -1
        buttonPrevious.isEnabled = true
        var persona = list[currentIndex]
        loadPerson(persona)
    }

    fun loadPerson(persona: Persona) {
        textInputName.editText?.setText(persona.name)
        textInputAge.editText?.setText(persona.age.toString())
        radioGroup.check(persona.gender)
    }

}