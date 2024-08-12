package com.example.lesson13.models

data class CryptCurrency (
    val currencySymbol: String,
    val rateUsd: String
) {
    fun shortInfo() :String {
        return "1$currencySymbol = ${formattedRateUsd()}\$"
    }

    private fun formattedRateUsd(): String = String.format("%.2f", rateUsd.toDouble())
}