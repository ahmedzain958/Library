package com.zainco.library.databinding.exinjava;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zainco.library.databinding.exinjava.model.Employee;
import com.zainco.library.databinding.exinjava.model.EmployeeRepository;

import java.util.List;

public class DatabindingInJavaViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;

    public DatabindingInJavaViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository();
    }

    public LiveData<List<Employee>> getAllEmployee() {
        return employeeRepository.getMutableLiveData();
    }
}
