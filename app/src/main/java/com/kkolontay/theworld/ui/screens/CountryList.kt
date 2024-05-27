package com.kkolontay.theworld.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.model.CountryFlags
import com.kkolontay.theworld.model.CountryName
import com.kkolontay.theworld.ui.theme.TheWorldTheme

@Composable
fun CountryList(countries: List<Country>, nextScreen: (Country) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(countries) { country ->
            Box(modifier = Modifier.clickable { nextScreen(country) }) {
                CountryItem(country = country)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListPreview() {
    TheWorldTheme {
        CountryList(countries = listOf(Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
            png = "some"
        )),Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
        png = "some"
        )), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
        png = "some"
        )), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
        png = "some"
        )), Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
        png = "some"
        )))) { conutry -> }
    }
}
