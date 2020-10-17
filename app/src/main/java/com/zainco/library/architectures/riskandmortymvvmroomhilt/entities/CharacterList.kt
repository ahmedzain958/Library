package com.zainco.library.architectures.riskandmortymvvmroomhilt.entities

import com.example.rickandmorty.data.entities.Character

data class CharacterList(
    val info: Info,
    val results: List<Character>
)