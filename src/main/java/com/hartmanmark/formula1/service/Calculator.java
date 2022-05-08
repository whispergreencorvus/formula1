package com.hartmanmark.formula1.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Calculator {

    private Date timeFromEndMap;
    private Date timeFromStartMap;

    public Map<String, String> calculateTime(Map<String, String> startMap, Map<String, String> endMap)
            throws ParseException {
        Map<String, String> calculateMap = new HashMap<>();
        endMap.forEach((key, value) -> {
            if (startMap.keySet().contains(key)) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                try {
                    timeFromEndMap = sdf.parse(endMap.get(key));
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Format of chosen time (should be HH:mm:ss.SSS) is invalid: " + endMap.get(key));
                }
                try {
                    timeFromStartMap = sdf.parse(startMap.get(key));
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Format of chosen time (should be HH:mm:ss.SSS) is invalid: " + startMap.get(key));
                }
                long durationTime = timeFromEndMap.getTime() - timeFromStartMap.getTime();
                calculateMap.put(key, formatDuration(durationTime));
            }
        });
        return calculateMap;
    }

    private final String formatDuration(long duration) {
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        long milliseconds = duration % 1000;
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }
}
