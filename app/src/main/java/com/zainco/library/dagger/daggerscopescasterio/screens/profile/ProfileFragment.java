package com.zainco.library.dagger.daggerscopescasterio.screens.profile;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.zainco.library.R;
import com.zainco.library.dagger.daggerscopescasterio.DemoApplication;
import com.zainco.library.dagger.daggerscopescasterio.models.SomeBigObject;

import javax.inject.Inject;


public class ProfileFragment extends Fragment {

    @Inject
    protected Resources resources;
    @Inject
    protected SomeBigObject someBigObject;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((DemoApplication) getActivity().getApplication()).createProfileComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((DemoApplication) getActivity().getApplication()).releaseProfileComponent();
    }

}
