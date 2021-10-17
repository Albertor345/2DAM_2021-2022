package alberto.gonzalez.pantallacrudpersonas.ui

import alberto.gonzalez.pantallacrudpersonas.domain.Persona
import alberto.gonzalez.pantallacrudpersonas.databinding.ListActivityBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

class ListActivity : AppCompatActivity(){

    private lateinit var activityBinding : ListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ListActivityBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        loadList()
    }

    fun loadList(){
        intent.getParcelableArrayListExtra<Persona>("lista")?.let {
            activityBinding.recycler.adapter = PersonaAdapter(it)
            activityBinding.recycler.layoutManager = GridLayoutManager(this, 1)
        }
    }


}