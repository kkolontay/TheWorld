package com.kkolontay.theworld.ui.screens.contrydetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkolontay.theworld.R
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.model.CountryFlags
import com.kkolontay.theworld.model.CountryName

@Composable
fun CountryItem(country: Country) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(
            colorResource(id = R.color.gray)
        )
    ) {
       Column(modifier = Modifier
           .padding(16.dp)
       ) {
            Row {
                Text(text = stringResource(R.string.name))
                Text(text = country.name.common)
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = stringResource(R.string.capital))
                Text(text = country.capital?.first() ?: "")
            }
        }
    }
    }


@Preview(showBackground = true)
@Composable
fun CountryItemPreview() {
    val country = Country(name = CountryName(common = "some"), capital = listOf("other"), population = 34, area = 45.0, flags = CountryFlags(
        png = "some"
    )
    )
    CountryItem(country = country)
}