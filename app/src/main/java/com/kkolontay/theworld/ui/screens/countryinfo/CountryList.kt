package com.kkolontay.theworld.ui.screens.countryinfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kkolontay.theworld.R
import com.kkolontay.theworld.api.CountryInfoState
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.ui.screens.contrydetails.CountryItem

@Composable
fun CountryList(viewModel: CountryInfoViewModel, timer: Int, taps: Int, back: Int, refresh: () -> Unit, nextScreen: (Country) -> Unit) {
    val state = viewModel.uiState.collectAsState()
    when (state.value) {
        is CountryInfoState.Success -> {

            Column {
                InfoView(taps = taps, refresh = refresh, back = back)
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(state.value.list) { country ->
                        Box(modifier = Modifier.clickable { nextScreen(country) }) {
                            CountryItem(country = country)
                        }
                    }
                }
            }
        }
        is CountryInfoState.Loading -> {
            Column {
                Text(text = "Loading: $timer", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
        else -> {
            Column {
                Text(text = "Error", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Button(onClick = {
                    viewModel.updateCountryList()
                }, modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Text(text = stringResource(R.string.update_list))
                }
            }
        }
    }
}