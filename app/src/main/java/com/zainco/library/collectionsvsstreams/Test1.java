package com.zainco.library.collectionsvsstreams;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test1 {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(2, 3, 4, 5);
        int even = number.stream().filter(
                x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);
        System.out.println(even);//6

        Stream<Integer> integerStream = number.stream().filter(
                x -> x % 2 == 0).map(s ->
                s - 1);
        System.out.println(integerStream);//6
    }
}
