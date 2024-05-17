package com.example.transpapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.transpapptest.ui.states.VehicleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(VehicleListState())

    fun onEvent() {

    }

}