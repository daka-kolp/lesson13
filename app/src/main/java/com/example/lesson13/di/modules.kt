package com.example.lesson13.di

import com.example.lesson13.network.ApiClient
import com.example.lesson13.network.CryptRepository
import org.koin.dsl.module

val cryptModule = module {
    single { ApiClient.retrofit }
    single { CryptRepository(get()) }
}