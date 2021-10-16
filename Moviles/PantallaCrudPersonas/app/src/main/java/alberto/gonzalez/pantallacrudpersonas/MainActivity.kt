package alberto.gonzalez.pantallacrudpersonas

import alberto.gonzalez.pantallacrudpersonas.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.text.set
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityMainBinding
    private var currentIndex: Int = 0
    private lateinit var list: MutableList<Persona>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        with(activityBinding) {
            buttonAdd.isEnabled = false
            buttonUpdate.isEnabled = false
            personName.isEnabled = false
            personAge.isEnabled = false
            radioGroup.isEnabled = false
        }
        setListeners()

    }

    fun setListeners() {
        with(activityBinding) {
            buttonAdd.setOnClickListener {
                addPerson()
            }
            buttonDelete.setOnClickListener {
                delPerson()
            }
            buttonUpdate.setOnClickListener {
                updatePerson(activityBinding.radioButtonMale.isSelected)
            }
            buttonPrevious.setOnClickListener {
                previousPerson()
            }
            buttonNext.setOnClickListener {
                nextPerson()
            }
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                checkBoxListener()
            }
        }
    }

    fun checkBoxListener() {
        with(activityBinding) {
            buttonAdd.isEnabled = !buttonAdd.isEnabled
            buttonUpdate.isEnabled = !buttonUpdate.isEnabled
            buttonPrevious.isEnabled = currentIndex > 0
            buttonNext.isEnabled = currentIndex < list.size - 1
            buttonDelete.isEnabled = !buttonDelete.isEnabled
            personName.isEnabled = !personName.isEnabled
            personAge.isEnabled = !personAge.isEnabled
            radioGroup.isEnabled = !radioGroup.isEnabled
        }
    }

    fun addPerson() {
        val p = Persona(
            (list.size + 1),
            activityBinding.personName.editText?.text.toString(),
            activityBinding.personAge.editText?.text.toString().toInt(),
            if (activityBinding.radioButtonMale.isSelected)
                activityBinding.radioButtonMale.text.toString()
            else activityBinding.radioButtonFemale.text.toString()
        )
        if (validatePerson(p)) {
            list.add(p)
            Toast.makeText(this, "Persona añadida con exito", Toast.LENGTH_SHORT).show()
            println(p)
            loadPerson(list[0])
            currentIndex = 0
            activityBinding.checkbox.isChecked = false
        } else Toast.makeText(this, "Datos introducidos no validos", Toast.LENGTH_SHORT).show()
    }

    fun validatePerson(p: Persona): Boolean {
        return activityBinding.personAge.editText?.text.toString().isDigitsOnly()
                && activityBinding.personName.editText?.text.toString().isNotEmpty()
                && activityBinding.personName.editText?.text.toString().isNotBlank()

    }

    fun delPerson() {
        if (list.size != 0) {
            list.removeAt(currentIndex)
            currentIndex = 0
            if (list.size != 0) {
                loadPerson(list[0])
                activityBinding.buttonPrevious.isEnabled = false
                activityBinding.buttonNext.isEnabled = currentIndex < list.size - 1
            } else clear()
            Toast.makeText(this, "Persona eliminada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No hay personas, añade una primero", Toast.LENGTH_SHORT).show()
        }
    }

    fun updatePerson(gender: Boolean) {
        with(activityBinding) {
            var persona = list[currentIndex]
            persona.name = personName.editText?.text.toString()
            persona.age = personAge.editText?.text.toString().toInt()
            persona.gender = if (gender) radioButtonMale.text.toString()
            else radioButtonFemale.text.toString()
            checkbox.isEnabled = false
            loadPerson(list[0])
        }
        Toast.makeText(this, "Datos modificados con exito", Toast.LENGTH_SHORT).show()
    }

    fun previousPerson() {
        currentIndex--
        activityBinding.buttonPrevious.isEnabled = currentIndex > 0
        activityBinding.buttonNext.isEnabled = true
        var persona = list[currentIndex]
        loadPerson(persona)
    }

    fun nextPerson() {
        currentIndex++
        activityBinding.buttonNext.isEnabled = currentIndex < list.size - 1
        activityBinding.buttonPrevious.isEnabled = true
        var persona = list[currentIndex]
        loadPerson(persona)
    }

    fun loadPerson(persona: Persona) {
        with(activityBinding) {
            personName.editText?.setText(persona.name)
            personAge.editText?.setText(persona.age.toString())
            if (persona.gender == "Hombre") radioGroup.check(radioButtonMale.id)
            else radioGroup.check(radioButtonFemale.id)

            radioGroup.check(persona.gender)
        }
    }

    fun clear() {
        textInputName.editText?.setText("")
        textInputAge.editText?.setText("")
    }

}