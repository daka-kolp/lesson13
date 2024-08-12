package com.example.lesson13.network

import com.example.lesson13.models.CryptCurrency

class CryptRepository(client: ApiClient) {
    private val api = client.retrofit.create(CryptInterface::class.java)

    suspend fun getBitcoin(): CryptCurrency {
        return api.getBitcoin().data
    }
}