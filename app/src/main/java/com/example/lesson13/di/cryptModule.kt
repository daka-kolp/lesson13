package com.example.lesson13.di

import com.example.lesson13.BitcoinViewModel
import com.example.lesson13.network.ApiClient
import com.example.lesson13.network.CryptRepository
import org.koin.dsl.module

val cryptModule = module {
    single { ApiClient }
    single { CryptRepository(get()) }
    single { BitcoinViewModel(get()) }
}