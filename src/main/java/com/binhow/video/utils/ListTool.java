package com.binhow.video.utils;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListTool {

    public List<Long> intersection(List<Long> arr1, List<Long> arr2) {
        List<Long> resultList = new ArrayList<>();
        Map<String, Long> map = new HashMap<>();
        arr1.forEach(a1 -> map.put(a1 + "", a1));
        arr2.forEach(a2 -> {
            Long obj = map.get(a2 + "");
            if (obj != null) {
                resultList.add(obj);
            }
        });
        return resultList;
    }
}
