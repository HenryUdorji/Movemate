package com.ifechukwu.movemate.data

import com.ifechukwu.movemate.R

data class Vehicle(
    val name: String,
    val type: String,
    val icon: Int
) {
    companion object {
        fun availableVehicles(): MutableList<Vehicle> {
            return mutableListOf(
                Vehicle(name = "Ocean Freight", type = "International", icon = R.drawable.ocean_freight),
                Vehicle(name = "Cargo Freight", type = "Reliable", icon = R.drawable.cargo_freight),
                Vehicle(name = "Air Freight", type = "International", icon = R.drawable.air_freight),
            )
        }
    }
}


