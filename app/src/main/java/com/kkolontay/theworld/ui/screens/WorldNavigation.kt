package com.kkolontay.theworld.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkolontay.theworld.viewmodel.CountryViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kkolontay.theworld.ui.screens.contrydetails.CountryItemDetail
import com.kkolontay.theworld.ui.screens.countryinfo.CountryInfoViewModel
import com.kkolontay.theworld.ui.screens.countryinfo.CountryList

enum class AppScreens {
    ListCountry
}

@Composable
fun WorldNavigation(
    viewModel: CountryViewModel = viewModel(),
    composableViewModel: CountryInfoViewModel,
    navController: NavHostController = rememberNavController(),
    context: Context
) {

    val title = rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            AppBar(
                title = title.value,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    title.value = ""
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->

        val timerState = viewModel.timerState.collectAsState()


        NavHost(navController = navController, startDestination = AppScreens.ListCountry.name, modifier = Modifier.padding(innerPadding)) {
            composable(AppScreens.ListCountry.name) {
                CountryList(viewModel = composableViewModel, timer = timerState.value, refresh = {
                   MainScope().launch {
                       viewModel.flows.refresh()
                       viewModel.refresh()
                   }
                }) {
                    title.value = it.name.common
                    navController.navigate("detail/${it.name.common}")
                }
            }
            composable(route = "detail/{country}") {
                val countryName = it.arguments?.getString("country") ?: error("Country is required")

                   val country = composableViewModel.fetchCountry(countryName)
                   CountryItemDetail(
                       country = country,
                       refresh = {
                           navController.navigateUp()
                       })
            }
        }
    }
}