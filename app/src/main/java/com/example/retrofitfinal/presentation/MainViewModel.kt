package com.example.retrofitfinal.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitfinal.MainRepository
import com.example.retrofitfinal.model.local.CountryDatabase
import com.example.retrofitfinal.model.local.CountryEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application){

    private val repository = MainRepository()
    private val dao = CountryDatabase.getDatabase(application).countryDao()

//    private val _countries = MutableLiveData<List<CountryEntity>>()
//    val countries: LiveData<List<CountryEntity>> = _countries
//
    private val _countries = MutableStateFlow(emptyList<CountryEntity>())
    val countries = _countries.asStateFlow()


    fun getCountriesFromRoom(){
        viewModelScope.launch {
            val countryList = dao.getCountries() ?: emptyList()
            _countries.value = countryList
        }
    }

}
