package com.kkolontay.theworld.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkolontay.theworld.R
import com.kkolontay.theworld.api.WorldResponse
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.model.CountryFlags
import com.kkolontay.theworld.model.CountryName
import com.kkolontay.theworld.viewmodel.CountryViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

enum class AppScreens() {
    ListCountry,
    CountryDetail
}

@Composable
fun WorldNavigation(
    viewModel: CountryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController(),
    context: Context
) {

    var title = rememberSaveable {
        mutableStateOf("")
    }
    var choosenCountry = remember {
        mutableStateOf<Country>(Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(png = "some")))
    }
    Scaffold(
        topBar = {
            AppBar(
                title = title.value,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    title.value = context.getString(R.string.list1)
                    MainScope().launch {
                        viewModel.flows.tapBack()
                    }
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        val uiState = remember {viewModel.uiState}
        var timerState = viewModel.timerState.collectAsState()
        var  tap = viewModel.tap.collectAsState()
        var back = viewModel.back.collectAsState()

        NavHost(navController = navController, startDestination = AppScreens.ListCountry.name, modifier = Modifier.padding(innerPadding)) {
            composable(AppScreens.ListCountry.name) {
                CountryList(countries = uiState.collectAsState().value, timer = timerState.value, taps = tap.value, back = back.value, refresh = {
                   MainScope().launch {
                       viewModel.flows.refresh()
                       viewModel.refresh()
                   }
                }) {
                    choosenCountry.value = it
                    MainScope().launch {
                        viewModel.flows.tap()
                    }
                    title.value = it.name.common
                    navController.navigate(AppScreens.CountryDetail.name)
                }
            }
            composable(AppScreens.CountryDetail.name) {
                CountryItemDetail(country = choosenCountry.value, taps = tap.value, back = back.value, refresh = {
                    MainScope().launch {
                        viewModel.flows.refresh()
                        viewModel.refresh()
                    }
                    navController.navigateUp()
                })
            }
        }
    }
}