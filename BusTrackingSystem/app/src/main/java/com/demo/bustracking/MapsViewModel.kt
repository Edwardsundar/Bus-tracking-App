package com.demo.bustracking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MapsViewModel:ViewModel() {
    var state by mutableStateOf(MapState())
}