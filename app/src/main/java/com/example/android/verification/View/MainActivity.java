package com.example.android.verification.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.verification.Model.CountryModel;
import com.example.android.verification.R;
import com.example.android.verification.ViewModel.ListViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.country)
    RecyclerView countrylist;

    @BindView(R.id.error)
    TextView error;

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout refreshLayout;

    private ListViewModel viewModel;
    private CountryListAdapter countryListAdapter = new CountryListAdapter(new ArrayList<CountryModel>());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();
        countrylist.setLayoutManager(new LinearLayoutManager(this));
        countrylist.setAdapter(countryListAdapter);
        observeViewModel();
    }
    private void observeViewModel(){
        viewModel.countries.observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                if(countryModels!= null)
                {
                    countrylist.setVisibility(View.VISIBLE);
                    countryListAdapter.updateCountry(countryModels);
                }
            }
        });
        viewModel.error.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if(isError != null)
                {
                    error.setVisibility(isError ? View.VISIBLE : View.GONE);
                }
            }
        });
        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean load) {
                if(load != null)
                {
                    loading.setVisibility(load ? View.VISIBLE : View.GONE);
                    if(load)
                    {
                        error.setVisibility(View.GONE);
                        countrylist.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}
