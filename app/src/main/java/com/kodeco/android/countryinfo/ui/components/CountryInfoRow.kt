package com.kodeco.android.countryinfo.ui.components

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.R
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.sample.sampleCountry


@Composable
fun CountryInfoRow(
    country: Country,
    onTap: () -> Unit,
    favoriteTap: (Country) -> Unit
) {
    val transition = updateTransition(country.isFavorite, label = "selected state")
    Card(
        onClick = onTap,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(text = "Name: ${country.commonName}")
                Text(text = "Capital: ${country.capital?.firstOrNull() ?: "N/A"}")
            }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(255,255,255, 0), contentColor = Color.White),
                onClick = { favoriteTap(country) }) {
                Image(
                    painter = if (country.isFavorite) {
                        painterResource(R.drawable.star_filled)
                    } else {
                        painterResource(R.drawable.star_outline)
                    },
                    contentDescription = "Description for accessibility",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(40.dp, 40.dp)
                )
            }
        }

    }
}

@Preview
@Composable
fun CountryInfoRowPreview() {
    CountryInfoRow(
        country = sampleCountry,
        onTap = {},
        favoriteTap = { sampleCountry}
    )
}
