package com.example.retrofitfinal.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitfinal.MainRepository
import com.example.retrofitfinal.data.local.CityDatabase
import com.example.retrofitfinal.data.local.CountryDatabase
import com.example.retrofitfinal.model.local.CityEntity
import com.example.retrofitfinal.model.local.CountryEntity
import com.example.retrofitfinal.model.local.PeopleEntity
import com.example.retrofitfinal.model.presentation.City
import com.example.retrofitfinal.model.presentation.Country
import com.example.retrofitfinal.model.presentation.People
import com.example.retrofitfinal.presentation.adapters.FilterAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepository()
    private val dao = CountryDatabase.getDatabase(application).countryDao()
    private val cityDao = CityDatabase.getDatabase(application).cityDao()

    private val _selectedCountryIds = MutableStateFlow(setOf<Int>())
    private val _selectedCityIds = MutableStateFlow(setOf<Int>())
    private val _countries = MutableStateFlow<List<Country>?>(null)

    private val _selectedCountryNames = MutableStateFlow(setOf<String>())



    val peoples = combine(
        _countries,
        _selectedCountryIds,
        _selectedCityIds
    ) { allCountries, selectedCountryIds, selectedCityIds ->
        allCountries
            ?.filter { it.id in selectedCountryIds }
            ?.flatMap { it.cities }
            ?.filter { it.id in selectedCityIds }
            ?.flatMap { it.people }
            ?.map { it.fullName }
    }

    val countries = combine(
        _countries,
        _selectedCountryNames,
    ) { allCountries, selectedCountryNames ->
        allCountries
            ?.filter { it.name in selectedCountryNames }
    }


    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val networkData = repository.getNetworkData().orEmpty().map {
                CountryEntity(
                    id = it.countryId ?: -1,
                    name = it.name ?: "-",
                    cities = it.cityList.orEmpty().map {it1->
                        CityEntity(
                            id = it1?.cityId ?: -1,
                            name = it1?.name ?: "-",
                            people = it1?.peopleList.orEmpty().map {it2->
                                PeopleEntity(
                                    id = it2?.humanId ?: -1,
                                    fullName = it2?.name + it2?.surname
                                )
                            }
                        )
                    }
                )
            }
            dao.insert(networkData)

            fetchData()
        }
    }

    fun fetchCountryNames(): List<FilterAdapter.FilterItem>{
        return dao.getCountryNames()
    }
    fun fetchCityNames(): List<FilterAdapter.FilterItem>{
        return cityDao.getCities()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _countries.value = dao.getCountries()
                ?.map {
                    Country(
                        id = it.id,
                        name = it.name,
                        cities = it.cities.map {it1->
                            City(
                                id = it1.id,
                                name = it1.name,
                                people = it1.people.map {it2->
                                    People(
                                        id = it2.id,
                                        fullName = it2.fullName
                                    )
                                }

                            )
                        }
                    )
                }
                .orEmpty()
        }
    }

/*
    fun toggleCountry(filterItem: FilterAdapter.FilterItem) {
        val currentCountries = _selectedCountryIds.value.toMutableSet()

        val toggledCountry = currentCountries.find {
        }



        val alreadySelectedItem = currentCountries.find {
            id == filterItem.id }

        if (alreadySelectedItem == null) {
            currentCountries.add(filterItem.copy(isSelected = true))
        } else {
            currentCountries.remove(filterItem)
        }

        _selectedCountries.value = currentCountries
    }
*/

}
