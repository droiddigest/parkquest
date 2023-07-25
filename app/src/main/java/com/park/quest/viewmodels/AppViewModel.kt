package com.park.quest.viewmodels

import android.content.Context
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.park.quest.database.ParksRepository
import com.park.quest.rawdata.RawPark
import com.park.quest.rawdata.RawParkStamp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.InputStream
import java.util.*
import javax.inject.Inject
import com.park.quest.R
import com.park.quest.database.Park
import com.park.quest.database.Stamp
import com.park.quest.rawdata.RawStamp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */

@HiltViewModel
internal class AppViewModel @Inject constructor() : ViewModel() {
    lateinit var navController: NavHostController
}

internal val LocalAppViewModel = compositionLocalOf { AppViewModelInfo() }

@Composable
fun ProvideAppViewModel(
    navHostController: NavHostController,
    content: @Composable () -> Unit
) {
    val appViewModel: AppViewModel = hiltViewModel()

    appViewModel.navController = navHostController

    val appViewModelInfo = AppViewModelInfo(appViewModel)

    val state = remember { appViewModelInfo }

    CompositionLocalProvider(
        LocalAppViewModel provides state
    ) {
        content()
    }
}

internal class AppViewModelInfo(
    var appViewModel: AppViewModel? = null
)

object Utility {
    fun readRawResource(context: Context, resourceId: Int): String? {
        return try {
            val inputStream: InputStream = context.resources.openRawResource(resourceId)
            val scanner: Scanner = Scanner(inputStream).useDelimiter("\\A")
            if (scanner.hasNext()) scanner.next() else ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}

