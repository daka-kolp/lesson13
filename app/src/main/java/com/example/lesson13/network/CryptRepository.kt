package com.example.lesson13.network

import com.example.lesson13.models.CryptCurrency
import retrofit2.Retrofit

class CryptRepository(private val client: Retrofit) {
    suspend fun getBitcoin(): CryptCurrency {
        val api = client.create(CryptInterface::class.java)
        return api.getBitcoin().data
    }
}