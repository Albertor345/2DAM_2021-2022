package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.domain.Persona
import alberto.gonzalez.crudpersonasv2.databinding.ListActivityBinding
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