package com.zainco.library.archcomponents.livedata.livedatatransformations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        // Setting the list
        val pokemonAdapter = PokemonAdapter { pokemon ->
            // Item click listener
            val intent = Intent(this, PokemonDetailActivity::class.java).apply {
                putExtra("id", pokemon.id)
            }
            startActivity(intent)
        }
        pokemon_list.layoutManager = LinearLayoutManager(this)
        pokemon_list.adapter = pokemonAdapter

        // Getting data from ViewModel
        val pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        pokemonViewModel.getPokemons().observe(this, Observer {
            pokemonAdapter.swapData(it!!)
        })
    }
}
