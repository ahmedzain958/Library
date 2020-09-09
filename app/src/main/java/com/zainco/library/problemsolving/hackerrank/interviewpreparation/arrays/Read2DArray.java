package com.zainco.library.problemsolving.hackerrank.interviewpreparation.arrays;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Ahmed Zain on 9/9/2020.
 */
class Read2DArray {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        int rows = 4;
        int columns = 4;
        int[][] myArray = new int[rows][columns];
        while (reader.lines().count() > 0) {
            for (int i = 0; i < myArray.length; i++) {
                String[] line = new String[0];
                try {
                    line = reader.readLine().trim().split(" ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < line.length; j++) {
                    myArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        System.out.println(Arrays.deepToString(myArray));
    }
}
