package com.hartmanmark.formula1.printer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Printer {

    private static final int TOP_RASERS = 16;
    private Map<String, String> nonSortedMap = new HashMap<String, String>();
    private Map<String, String> sortedMap = new HashMap<String, String>();
    private StringBuffer result = new StringBuffer();

    public String printer(Map<String, String> calculateMap, Map<String, String> abbreviationsMap) {
        attach(calculateMap, abbreviationsMap);
        sortRasers();
        final AtomicInteger counter = new AtomicInteger();
        sortedMap.forEach((key, value) -> {
            counter.incrementAndGet();
            if (counter.intValue() == TOP_RASERS) {
                result.append(String.format("%62s", "").replace(' ', '-') + "\n");
            }
            result.append(counter + ". " + key.toString() + " | " + value.toString() + "\n");
        });
        return result.toString();
    }

    private void sortRasers() {
        sortedMap = nonSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private void attach(Map<String, String> calculateMap, Map<String, String> abbreviationsMap) {
        calculateMap.forEach((key, value) -> {
            if (calculateMap.keySet().contains(key)) {
                nonSortedMap.put(abbreviationsMap.get(key), calculateMap.get(key));
            }
        });
    }
}
