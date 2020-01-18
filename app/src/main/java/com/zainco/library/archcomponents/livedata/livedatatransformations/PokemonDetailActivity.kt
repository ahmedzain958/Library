package com.zainco.library.archcomponents.livedata.livedatatransformations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)


        val id = intent.getLongExtra("id", 0)

        val pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        pokemonViewModel.setPokemon(id)
        pokemonViewModel.getPokemon().observe(this, Observer {
            pokemon_details.text = it.toString()
        })
    }
}
