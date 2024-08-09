package com.example.lesson13.models

data class CryptCurrency (
    val id: String,
    val symbol: String,
    val currencySymbol: String,
    val type: String,
    val rateUsd: String
) {
    fun shortInfo() :String {
        return "1$currencySymbol = ${formattedRateUsd()}\$"
    }

    private fun formattedRateUsd(): String = String.format("%.2f", rateUsd.toDouble())
}