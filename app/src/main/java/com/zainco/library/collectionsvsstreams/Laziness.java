package com.zainco.library.collectionsvsstreams;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Laziness {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
//List of names
        List<String> names = Arrays.asList(new String[]{
                "barry",
                "andy",
                "ben",
                "chris",
                "bill"
        });
//map and filter are piped and the stream is stored
        List<String> namesStream = names.stream()
                .map(n -> {
                    System.out.println("In map - " + n);
                    return n.toUpperCase();
                }).filter(upperName -> {
                    System.out.println("In filter - " + upperName);
                    return upperName.startsWith("B");
                }).limit(2).collect(Collectors.toList());
        System.out.println("In filter - " + namesStream);


    }
}


