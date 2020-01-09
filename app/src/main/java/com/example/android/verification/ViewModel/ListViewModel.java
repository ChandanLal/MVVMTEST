package com.example.android.verification.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.verification.Model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

   public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();
   public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
   public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

   public void refresh()
   {
       fetchCountries();
   }
    public  void fetchCountries()
    {
        CountryModel country1 = new CountryModel("India","New Delhi","");
        CountryModel country2 = new CountryModel("Australia", "Sydney","");
        CountryModel country3 = new CountryModel("SriLanka", "Colombo","");

        List<CountryModel> list = new ArrayList<>();
        list.add(country1);
        list.add(country2);
        list.add(country3);

        countries.setValue(list);
        error.setValue(false);
        loading.setValue(false);
    }
}
