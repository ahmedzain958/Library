package com.zainco.library.databinding.exinjava;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zainco.library.R;
import com.zainco.library.databinding.ActivityDatabindingInjavaBinding;
import com.zainco.library.databinding.exinjava.adapter.EmployeeDataAdapter;
import com.zainco.library.databinding.exinjava.model.Employee;

import java.util.ArrayList;
import java.util.List;
/*
* You can use the inflate method on the generated class. This is useful for using data binding in fragments, ListView or RecyclerView.
* ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater(), container, attachToContainer);
// get the root view
View view = binding.getRoot();
* */
public class DatabindingInJavaActivity extends AppCompatActivity {
    private DatabindingInJavaViewModel databindingInJavaViewModel;
    private EmployeeDataAdapter employeeDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingInjavaBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_databinding_injava);

        // bind RecyclerView
        RecyclerView recyclerView = activityMainBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        databindingInJavaViewModel = ViewModelProviders.of(this).get(DatabindingInJavaViewModel.class);
        employeeDataAdapter = new EmployeeDataAdapter();
        recyclerView.setAdapter(employeeDataAdapter);
        getAllEmployee();
    }

    private void getAllEmployee() {
        databindingInJavaViewModel.getAllEmployee().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                employeeDataAdapter.setEmployeeList((ArrayList<Employee>) employees);
            }
        });
    }
}
