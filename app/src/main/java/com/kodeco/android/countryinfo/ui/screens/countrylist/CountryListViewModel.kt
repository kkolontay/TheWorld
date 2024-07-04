package com.kodeco.android.countryinfo.ui.screens.countrylist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.dao.database.repository.CountryDAORepository
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.utility.NetworkConnectivityService
import com.kodeco.android.countryinfo.utility.NetworkConnectivityServiceImpl
import com.kodeco.android.countryinfo.utility.NetworkStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountryListViewModel(
    private val repository: CountryRepository,
    private val daoRepository: CountryDAORepository,
    private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryListState>(CountryListState.Loading)

    val uiState: StateFlow<CountryListState> = _uiState.asStateFlow()
    val networkConnectivityService: NetworkConnectivityService = NetworkConnectivityServiceImpl(
        context = context
    )
    var valueNetworkStatus :  NetworkStatus = NetworkStatus.Unknown
    val networkStatus: StateFlow<NetworkStatus> = networkConnectivityService.networkStatus.stateIn(
        initialValue = NetworkStatus.Unknown,
        scope = viewModelScope,
        started = WhileSubscribed(5000)
    )

    init {
        viewModelScope.launch {
            networkStatus.collect { item ->
                valueNetworkStatus = item
            }
        }
        viewModelScope.launch {
            repository
                .countries
                .catch {
                    _uiState.value = CountryListState.Error(it)
                }
                .collect {
                    _uiState.value = CountryListState.Success(it)
                    daoRepository.addCountries(it)
                }
        }

        fetchCountries()
    }

    class CountryInfoViewModelFactory(private val repository: CountryRepository,
        private val daoRepository: CountryDAORepository,
                                      private val context: Context) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryListViewModel(repository, daoRepository = daoRepository, context = context) as T
    }

    fun fetchCountries() {
        _uiState.value = CountryListState.Loading
        if (valueNetworkStatus != NetworkStatus.Unknown) {

            viewModelScope.launch {
                try {
                    repository.fetchCountries()
                } catch (e: Exception) {
                    _uiState.value = CountryListState.Error(e)
                }
            }
        } else {
            viewModelScope.launch {
                _uiState.value = CountryListState.Success(daoRepository.getCountries())
            }
        }
    }

    fun favorite(country: Country) {
        viewModelScope.launch {
            repository.favorite(country)
        }
    }
}
