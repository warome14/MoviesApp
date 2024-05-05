package com.example.moviesapp.model.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.api.api
import com.example.moviesapp.model.Boss
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val _bossState = mutableStateOf(BossState())
    val bossesState: State<BossState> = _bossState

    init {
        fetchCategories()
    }


    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = api.getBosses()
                _bossState.value = _bossState.value.copy(
                    list = response.data,
                    loading = false,
                    error = null
                )

            }catch (e: Exception){
                _bossState.value = _bossState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class BossState(
        val loading: Boolean = true,
        val list: List<Boss> = emptyList(),
        val error: String? = null
    )

}