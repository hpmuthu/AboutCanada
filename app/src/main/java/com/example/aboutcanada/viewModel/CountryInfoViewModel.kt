package com.example.aboutcanada.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aboutcanada.pojo.CountryInfo
import com.example.aboutcanada.webService.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class CountryInfoViewModel : ViewModel() {

    public val countryInfo: MutableLiveData<CountryInfo> by lazy {
        MutableLiveData<CountryInfo>()
    }

    public fun getCountryInfo(): LiveData<CountryInfo> {
        return countryInfo
    }

    public fun loadCountryInfo() {

        var data: CountryInfo? = null

        runBlocking {
            launch(Dispatchers.IO) {
                val response: Response<CountryInfo> = ApiClient.getService()
                    .getCountryInfo("application/json").execute()

                if (response.isSuccessful && response.code() == 200) {
                    try {

                        data = response.body()!!

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        countryInfo.value = data

    }
}