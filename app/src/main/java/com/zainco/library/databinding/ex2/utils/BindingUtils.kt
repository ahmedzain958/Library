package com.zainco.library.databinding.ex2.utils

import kotlin.math.ln
import kotlin.math.pow

object BindingUtils {
    // https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp
// Converts the number to K, M suffix
// Ex: 5500 will be displayed as 5.5k
    @JvmStatic
    fun convertToSuffix(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format(
            "%.1f%c",
            count / 1000.0.pow(exp.toDouble()),
            "kmgtpe"[exp - 1]
        )
    }
}