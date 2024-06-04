package com.kkolontay.theworld.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkolontay.theworld.api.WorldResponse
import com.kkolontay.theworld.flow.Flows
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.model.CountryFlags
import com.kkolontay.theworld.model.CountryName
import com.kkolontay.theworld.ui.theme.TheWorldTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun CountryList(countries: WorldResponse, timer: Int, taps: Int, back: Int, nextScreen: (Country) -> Unit) {

    when (countries) {
       is WorldResponse.Success -> {

           Column {
                InfoView(taps = taps, refresh = {}, back = back)
               LazyColumn(
                   modifier = Modifier.fillMaxWidth(),
                   contentPadding = PaddingValues(16.dp)
               ) {

                   items(countries.list) { country ->
                       Box(modifier = Modifier.clickable { nextScreen(country) }) {
                           CountryItem(country = country)
                       }
                   }
               }
           }
    }
       is WorldResponse.Loading -> {
        Column {
            Text(text = "Loading: ${timer}", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
        }
        else -> {
            Column {
                Text(text = "Error", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
    }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun CountryListPreview() {
//    TheWorldTheme {
//        CountryList(countries = listOf(Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
//            png = "some"
//        )),Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
//        png = "some"
//        )), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
//        png = "some"
//        )), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
//        png = "some"
//        )), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
//        png = "some"
//        )))) { conutry -> }
//    }
//}
