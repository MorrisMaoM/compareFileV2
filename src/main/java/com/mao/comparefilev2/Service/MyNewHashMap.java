package com.mao.comparefilev2.Service;

import java.util.HashMap;

public class MyNewHashMap<K> extends HashMap<String, String> {

    @Override
    public String put(String key, String value) {
        String newV = value;
        if (containsKey(key)) {
            String oldV = get(key);
            newV = oldV + "," + newV;
        }
        return super.put(key, newV);
    }
}
