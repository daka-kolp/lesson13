package com.example.lesson13

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lesson13.models.CryptCurrency
import com.example.lesson13.network.CryptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class BitcoinViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun getSuccessResponse() {
        val repo = Mockito.mock(CryptRepository::class.java)
        val successfulResult = CryptCurrency("₿", "1000.0")
        val viewModel = BitcoinViewModel(repo)
        val events = mutableListOf<BitcoinViewModel.UIBitcoinState>()
        viewModel.uiBitcoinState.observeForever { events.add(it) }
        runBlocking {
            Mockito.`when`(repo.getBitcoin()).thenReturn(successfulResult)
        }
        viewModel.getBitcoin()
        Assert.assertEquals(BitcoinViewModel.UIBitcoinState.Empty, events[0])
        Assert.assertEquals(BitcoinViewModel.UIBitcoinState.Processing, events[1])
        val value = events[2] as BitcoinViewModel.UIBitcoinState.Result
        Assert.assertEquals(successfulResult, value.bitcoin)

    }

    @Test(expected = Exception::class)
    fun getErrorResponse() {
        val repo = Mockito.mock(CryptRepository::class.java)
        val error = Exception("Test Error")
        val viewModel = BitcoinViewModel(repo)
        val events = mutableListOf<BitcoinViewModel.UIBitcoinState>()
        viewModel.uiBitcoinState.observeForever { events.add(it) }
        runBlocking {
            Mockito.`when`(repo.getBitcoin()).thenThrow(error)
        }
        viewModel.getBitcoin()
        Assert.assertEquals(BitcoinViewModel.UIBitcoinState.Empty, events[0])
        Assert.assertEquals(BitcoinViewModel.UIBitcoinState.Processing, events[1])
        Assert.assertEquals(BitcoinViewModel.UIBitcoinState.Error(Exception("Test Error")), events[2])
    }
}
