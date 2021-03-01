package com.kakadurf.hw_sem2.presentation.models

import android.util.Log
import androidx.annotation.ColorInt
import com.kakadurf.hw_sem2.R


enum class TemperatureData(@ColorInt val colour: Int) {
    ICE_AGE(R.color.weather_icy),COLD(R.color.weather_cold),NORMAL(R.color.weather_normal),
    WARM(R.color.weather_warm),HOT(R.color.weather_hot);
    companion object {
        fun create(tempr: Double): TemperatureData? {
            if (tempr <= -40) {
                Log.d("hi",tempr.toString())
                return ICE_AGE
            } else
                if (-40 < tempr && tempr <= 0) {
                    return COLD
                } else
                    if (0 < tempr && tempr <= 20) {
                        Log.d("hi",tempr.toString() + "norm")
                        return NORMAL
                    } else
                        if (20 < tempr && tempr <= 40) {
                            return WARM
                        } else
                            if (40 < tempr) {
                                return HOT
                            }
            return null
        }

    }}