package com.park.quest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.park.quest.navigation.App
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@AndroidEntryPoint
class AppStart: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}