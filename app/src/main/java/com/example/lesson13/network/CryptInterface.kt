package com.example.lesson13.network

import com.example.lesson13.models.CryptCurrencyWrapper
import retrofit2.http.GET

interface CryptInterface {
    @GET("rates/bitcoin")
    suspend fun getBitcoin(): CryptCurrencyWrapper
}