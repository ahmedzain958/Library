package com.zainco.library.databinding.vogellabaseadapter;


public interface MainActivityContract {
    interface Presenter {
        void onShowCelesiusData(TemperatureData temperatureData);

        void showList();
    }

    interface View {
        void showData(TemperatureData temperatureData);

        void startSecondActivity();
    }

}
