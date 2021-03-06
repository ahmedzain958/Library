package com.zainco.library.threading;

public class VolatileTesting {
    //ideal output
    /*
     *Say Hello
     * Hello World
     * Say Bye
     * Good Bye */
    //but real output
    /*
     *Say Hello
     * Say Bye*/
    private /* VOLATILE KEYWORD here solves the issue*/ volatile static boolean sayHello ;
//volatile makes sure that all threads always have the most updated version of this variable and not the cached version
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {// this thread isn't aware of changes happening in main thread
            while (!sayHello) {
            }
            System.out.println("Hello World");
            while (sayHello) {
            }
            System.out.println("Good Bye");
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Say Hello");
        sayHello = true;//change here doesn't affect the  while (!sayHello) in the inner thread until it the variable sayHello is declared as volatile
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Say Bye");
    }
}
