package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.databinding.ListActivityBinding
import alberto.gonzalez.crudpersonasv2.services.Services
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

class ListActivity : AppCompatActivity(){
    private lateinit var activityBinding : ListActivityBinding
    private lateinit var services: Services

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ListActivityBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        services = Services()
        loadList()
    }

    fun loadList(){
        services.getListaCharacters(assets.open("data.json"))?.let {
            activityBinding.recycler.adapter = CharacterAdapter(it)
            activityBinding.recycler.layoutManager = GridLayoutManager(this, 1)
        }
    }


}