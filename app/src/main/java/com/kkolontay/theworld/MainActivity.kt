package com.kkolontay.theworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kkolontay.theworld.repository.CountryRepositoryImplementation
import com.kkolontay.theworld.ui.screens.WorldNavigation
import com.kkolontay.theworld.ui.screens.countryinfo.CountryInfoViewModel
import com.kkolontay.theworld.ui.theme.TheWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   WorldNavigation(composableViewModel = viewModel(factory =  CountryInfoViewModel.provideFactory(
                       CountryRepositoryImplementation(), this)), context = baseContext)
                }
            }
        }
    }
}
