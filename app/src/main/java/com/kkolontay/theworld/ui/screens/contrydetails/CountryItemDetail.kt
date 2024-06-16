package com.kkolontay.theworld.ui.screens.contrydetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kkolontay.theworld.R
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.model.CountryFlags
import com.kkolontay.theworld.model.CountryName

@Composable
fun CountryItemDetail(country: Country) {

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row {
            Text(text = stringResource(R.string.capital1))
            Text(text = country.name.common)
        }
        Row {
            Text(text = stringResource(R.string.population))
            Text(text = country.population.toString())
        }
        Row {
            Text(text = stringResource(R.string.area))
            Text(text = country.area.toString())
        }
        AsyncImage(
            model = country.flags.png,
            contentDescription = stringResource(R.string.translated_description_of_what_the_image_contains),
            modifier = Modifier
                .padding(vertical = 16.dp)
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
    CountryItemDetail(country = country)
}