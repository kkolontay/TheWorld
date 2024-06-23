package com.kodeco.android.countryinfo.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.sample.sampleCountry
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

enum class FlagState {
    Collapsed,
    Expanded
}

@Composable
fun CountryDetails(
    country: Country,
    modifier: Modifier,
) {
    val currentState = remember { mutableStateOf(FlagState.Collapsed) }
    val transition = updateTransition(currentState.value, label = "box state")
    val rect by transition.animateFloat(label = "rectangle") { state ->
        when (state) {
            FlagState.Collapsed -> 1f
            FlagState.Expanded -> 1.8f
        }
    }
    LazyColumn(modifier = modifier) {
        item { Text(text = "Capital: ${country.mainCapital}") }
        item { Text(text = "Population: ${country.population}") }
        item { Text(text = "Area: ${country.area}") }
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(country.flagUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Flag",
                contentScale = ContentScale.Fit,
                modifier = Modifier.border(1.dp, color = MaterialTheme.colorScheme.primary)
                    .height((120 * rect).dp)
                    .width((200 * rect).dp)
                    .clickable {
                        currentState.value = when (currentState.value) {
                            FlagState.Collapsed -> FlagState.Expanded
                            FlagState.Expanded -> FlagState.Collapsed
                        }
                    }
            )
        }
    }
}

@Preview
@Composable
fun CountryDetailsPreview() {
    MyApplicationTheme {
        CountryDetails(
            country = sampleCountry,
            modifier = Modifier,
        )
    }
}
