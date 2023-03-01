package com.example.car

import androidx.lifecycle.ViewModel


class CarModel: ViewModel() {
    val cars = mutableListOf<Car>()

}