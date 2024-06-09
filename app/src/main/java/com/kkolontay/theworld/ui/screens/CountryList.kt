package com.kkolontay.theworld.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kkolontay.theworld.api.WorldResponse
import com.kkolontay.theworld.model.Country

@Composable
fun CountryList(countries: WorldResponse, nextScreen: (Country) -> Unit) {
    when (countries) {
       is WorldResponse.Success -> {
           Column {
                InfoView(taps = 3, refresh = { }, back = 4)
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
            Text(text = "Loading", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
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
