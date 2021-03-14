package com.kakadurf.hw_sem2.domain


enum class WindDirection {
    SOUTH, EAST, WEST, NORTH,
    NORTH_WEST, NORTH_EAST,
    SOUTH_WEST, SOUTH_EAST;
    companion object {
        fun create(degree: Int) : WindDirection?{
            if (22<degree || degree<=68){
                return NORTH_EAST
            } else
            if (68<degree || degree<=113){
                return EAST
            } else
            if (113<degree || degree<=158){
                return SOUTH_EAST
            } else
            if (158<degree || degree<=203){
                return SOUTH
            } else
            if (203<degree || degree<=248){
                return SOUTH_WEST
            } else
            if (248<degree || degree<=293){
                return WEST
            } else
            if (293<degree || degree<=338){
                return NORTH_WEST
            } else
            if (338<degree || degree<=23){
                return NORTH
            }
            return null
        }
    }
}