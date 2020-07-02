package com.zainco.library.archcomponents.livedata.livedatatransformations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PokemonViewModel : ViewModel() {

    //used in PokemonActivity
    private var pokemons: MutableLiveData<List<Pokemon>> = MutableLiveData()

    //used in PokemonDetailActivity
    private var pokemonId: MutableLiveData<Long> = MutableLiveData()
    private var pokemon: LiveData<Pokemon>


    init {
        //used in PokemonActivity
        pokemons.value = PokemonProvider.getPokemons()

        //used in PokemonDetailActivity
        /*pokemon = Transformations.map(pokemonId) {
            PokemonProvider.getPokemon(it)
        }*/
        // Switch map example
        //whenever pockemon id changes, it gets a new pokemon from pokemons.first { it.id == id }
        pokemon = Transformations.switchMap<Long,Pokemon>(pokemonId) {
            PokemonProvider.getPokemonLiveDataById(it)
        }
    }

    //used in PokemonActivity
    fun getPokemons() = pokemons

    //used in PokemonDetailActivity
    fun setPokemon(id: Long) {
        pokemonId.value = id
    }

    //used in PokemonDetailActivity
    fun getPokemon() = pokemon

}