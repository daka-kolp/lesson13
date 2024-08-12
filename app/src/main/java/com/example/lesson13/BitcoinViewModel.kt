package com.example.lesson13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson13.models.CryptCurrency
import com.example.lesson13.network.CryptRepository
import kotlinx.coroutines.launch

class BitcoinViewModel(private val repository: CryptRepository) : ViewModel() {
    private val _uiBitcoinState = MutableLiveData<UIBitcoinState>(UIBitcoinState.Empty)
    val uiBitcoinState: LiveData<UIBitcoinState> = _uiBitcoinState

    fun getBitcoin() {
        _uiBitcoinState.postValue(UIBitcoinState.Processing)
        viewModelScope.launch {
            try {
                val result = repository.getBitcoin()
                _uiBitcoinState.postValue(UIBitcoinState.Result(result))
            } catch (e: Exception) {
                _uiBitcoinState.postValue(UIBitcoinState.Error(e))
            }
        }
    }

    sealed class UIBitcoinState {
        data object Empty : UIBitcoinState()
        data object Processing : UIBitcoinState()
        class Result(val bitcoin: CryptCurrency) : UIBitcoinState()
        class Error(val error: Exception) : UIBitcoinState()
    }
}