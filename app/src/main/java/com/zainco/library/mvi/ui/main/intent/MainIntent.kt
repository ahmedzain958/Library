package com.zainco.library.mvi.ui.main.intent

sealed class MainIntent {
    object FetchUser : MainIntent()
}