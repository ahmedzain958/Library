package com.zainco.library.databinding.alimuzaffar.mvvmloadingbaseobservable.net;


import com.zainco.library.databinding.alimuzaffar.mvvmloadingbaseobservable.model.DogBreedImages;

import retrofit2.Callback;

public abstract class DogImagesCallback implements Callback<DogBreedImages> {
    private String breed;

    protected DogImagesCallback(String breed) {
        this.breed = breed;
    }

    protected String getBreed() {
        return this.breed;
    }

}
