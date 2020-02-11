package com.zainco.library.databinding.ex3baseadapter;


public interface MainActivityContract {
    public interface Presenter {
        void onShowData(TemperatureData temperatureData);
        void showList();
    }

    public interface View {
        void showData(TemperatureData temperatureData);
        void startSecondActivity();
    }

}
