package com.zainco.library.problemsolving;

import java.util.HashMap;

/**
 * Created by Ahmed Zain on 7/5/2020.
 */
class RepeatedELement {
    public static void main(String[] args) {

        HashMap<String, String> m = new HashMap<>();
        String[] Arr = {"a", "a", "a", "a", "a", "b", "c", "d", "a"};
        for (String key : Arr)
            m.put(key, "Present");
        System.out.println(m);

    }
}
