package com.example.lesson13.network

import com.example.lesson13.models.CryptCurrency

class CryptRepository(private val client: ApiClient) {
    suspend fun getBitcoin(): CryptCurrency {
        val api = client.retrofit.create(CryptInterface::class.java)
        return api.getBitcoin().data
    }
}