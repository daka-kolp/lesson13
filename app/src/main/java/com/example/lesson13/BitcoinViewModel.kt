package com.example.lesson13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson13.models.CryptCurrency
import com.example.lesson13.network.CryptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BitcoinViewModel(private val repository: CryptRepository) : ViewModel() {
    private val _uiBitcoinState = MutableLiveData<UIBitcoinState>(UIBitcoinState.Empty)
    val uiHeroesState: LiveData<UIBitcoinState> = _uiBitcoinState

    fun getBitcoin() {
        _uiBitcoinState.value = UIBitcoinState.Processing
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var value: UIBitcoinState = UIBitcoinState.Processing
                _uiBitcoinState.postValue(value)
                try {
                    val result = repository.getBitcoin()
                    value = UIBitcoinState.Result(result)
                } catch (e: Exception) {
                    value = UIBitcoinState.Error(e.localizedMessage ?: e.toString())
                } finally {
                    _uiBitcoinState.postValue(value)
                }
            }
        }
    }

    sealed class UIBitcoinState {
        data object Empty : UIBitcoinState()
        data object Processing : UIBitcoinState()
        class Result(val bitcoin: CryptCurrency) : UIBitcoinState()
        class Error(val error: String) : UIBitcoinState()
    }
}