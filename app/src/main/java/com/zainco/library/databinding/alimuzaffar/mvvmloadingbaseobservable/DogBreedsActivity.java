package com.zainco.library.databinding.alimuzaffar.mvvmloadingbaseobservable;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.zainco.library.R;
import com.zainco.library.databinding.ActivityDogBreedsBinding;
import com.zainco.library.databinding.alimuzaffar.mvvmloadingbaseobservable.model.DogBreed;
import com.zainco.library.databinding.alimuzaffar.mvvmloadingbaseobservable.viewmodel.DogBreedsViewModel;

import java.util.List;


public class DogBreedsActivity extends AppCompatActivity {
    private DogBreedsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_breeds);
        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityDogBreedsBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_dog_breeds);
        viewModel = ViewModelProviders.of(this).get(DogBreedsViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupListUpdate();

    }

    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.fetchList();
        viewModel.getBreeds().observe(this, new Observer<List<DogBreed>>() {
            @Override
            public void onChanged(List<DogBreed> dogBreeds) {
                viewModel.loading.set(View.GONE);
                if (dogBreeds.size() == 0) {
                    viewModel.showEmpty.set(View.VISIBLE);
                } else {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setDogBreedsInAdapter(dogBreeds);
                }
            }
        });
        setupListClick();
    }

    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<DogBreed>() {
            @Override
            public void onChanged(DogBreed dogBreed) {
                if (dogBreed != null) {
                    Toast.makeText(DogBreedsActivity.this, "You selected a " + dogBreed.getBreed(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
