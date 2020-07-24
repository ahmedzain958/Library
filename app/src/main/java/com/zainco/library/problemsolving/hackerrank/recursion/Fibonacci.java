package com.zainco.library.problemsolving.hackerrank.recursion;

import java.util.Scanner;

/**
 * Created by Ahmed Zain on 7/4/2020.
 */
class Fibonacci {
    public static int fibonacci(int n) {
        int[] fib = new int[n];
        int i;
        fib[0] = 0;
        fib[1] = 1;
        for (i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
