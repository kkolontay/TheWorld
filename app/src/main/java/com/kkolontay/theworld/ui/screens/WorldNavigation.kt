package com.kkolontay.theworld.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kkolontay.theworld.R
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.model.CountryFlags
import com.kkolontay.theworld.model.CountryName

enum class AppScreens() {
    ListCountry,
    CountryDetail
}

@Composable
fun WorldNavigation(
   // viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    context: Context
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreens.valueOf(
        backStackEntry?.destination?.route ?: AppScreens.ListCountry.name
    )
    var title = rememberSaveable {
        mutableStateOf("List")
    }
//    var choosenCountry by remember {
//        mutableStateOf<Country>(Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
//            png = "some"
//        )))
//    }
    Scaffold(
        topBar = {
            AppBar(
                title = title.value,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    title.value = context.getString(R.string.list1)
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        val countries = remember {
            listOf(
            Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
            png = "https://mainfacts.com/media/images/coats_of_arms/md.png"
        )
            ),
            Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
            png = "https://mainfacts.com/media/images/coats_of_arms/md.png"
        )
            ), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
            png = "https://mainfacts.com/media/images/coats_of_arms/md.png"
        )
            ), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
            png = "https://mainfacts.com/media/images/coats_of_arms/md.png"
        )
            ), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
            png = "https://mainfacts.com/media/images/coats_of_arms/md.png"
        )
            )
        )
        }

        //val uiState by viewModel.uiState.collectAsState()
        NavHost(navController = navController, startDestination = AppScreens.ListCountry.name, modifier = Modifier.padding(innerPadding)) {
            composable(AppScreens.ListCountry.name) {
                CountryList(countries = countries) {
                   // choosenCountry.value = it
                    title.value = it.name.common
                    navController.navigate(AppScreens.CountryDetail.name)
                }
            }
            composable(AppScreens.CountryDetail.name) {
                CountryItemDetail(country = Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
       png = "https://mainfacts.com/media/images/coats_of_arms/md.png"
      )), navigateUP = {
                    navController.previousBackStackEntry
                })
            }
        }
    }
}