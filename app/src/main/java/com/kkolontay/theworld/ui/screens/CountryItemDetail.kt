package com.kkolontay.theworld.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.model.CountryFlags
import com.kkolontay.theworld.model.CountryName

@Composable
fun CountryItemDetail(country: Country, navigateUP: () -> Unit, modifier: Modifier = Modifier) {

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row {
            Text(text = "Capital: ")
            Text(text = country.name.common.toString())
        }
        Row {
            Text(text = "Population: ")
            Text(text = country.population.toString())
        }
        Row {
            Text(text = "Area: ")
            Text(text = country.area.toString())
        }
        AsyncImage(
            model = country.flags.png,
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.5f)
                .clip(RoundedCornerShape(5.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CountryItemDetailPreview() {
    val country = Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
        png = "some"
    ))
    CountryItemDetail(country = country, navigateUP = {})
}