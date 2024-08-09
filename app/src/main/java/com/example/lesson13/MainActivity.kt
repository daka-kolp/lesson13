package com.example.lesson13

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lesson13.models.CryptCurrency


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: BitcoinViewModel
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[BitcoinViewModel::class.java]
        viewModel.uiHeroesState.observe(this) { onViewUpdate(it) }

        textView = findViewById(R.id.value)
        val button = findViewById<Button>(R.id.make_request)

        button.setOnClickListener { viewModel.getBitcoin() }
    }

    private fun onViewUpdate(uiState: BitcoinViewModel.UIBitcoinState) {

        when (uiState) {
            is BitcoinViewModel.UIBitcoinState.Result -> onValueFetched(uiState.bitcoin)
            is BitcoinViewModel.UIBitcoinState.Error -> onValueFetchedError(uiState.error)
            is BitcoinViewModel.UIBitcoinState.Empty -> Unit
            is BitcoinViewModel.UIBitcoinState.Processing -> Unit
        }
    }

    private fun onValueFetched(value: CryptCurrency) {
        textView.text = value.shortInfo()
    }

    private fun onValueFetchedError(error: String) {
        textView.text = "Error: $error"
    }
}
