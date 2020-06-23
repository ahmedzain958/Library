package com.zainco.library.databinding.vogellabaseadapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.zainco.library.R;
import com.zainco.library.databinding.ActivityBaseadapterexamplefirstBinding;


public class BaseAdapterExampleFirstActivity extends Activity implements MainActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBaseadapterexamplefirstBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_baseadapterexamplefirst);
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);
        TemperatureData temperatureData = new TemperatureData("Hamburg", "10");
        binding.setTemp(temperatureData);
        binding.setPresenter(mainActivityPresenter);
    }

    @Override
    public void showData(TemperatureData temperatureData) {
        String celsius = temperatureData.getCelsius();
        Toast.makeText(this, celsius, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startSecondActivity() {
        Intent intent = new Intent(this, BaseAdapterExampleSecondActivity.class);
        startActivity(intent);
    }
}
