package com.hartmanmark.formula1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Reader {

    Map<String, Map<String, String>> abbreviationsMap = new LinkedHashMap<>();
    Map<String, String> startMap = new HashMap<>();
    Map<String, String> endMap = new HashMap<>();
    Map<String, String> calculateMap = new HashMap<>();
    String name;
    String car;
    String abbrev;
    String keyEndMap;
    String valueEndMap;
    String keyStartMap;
    String valueStartMap;

    public void readerFile() throws ParseException, FileNotFoundException {
        File startLog = new File("/home/user/java/GitLab/Task 6/task-6/start.log");
        File endLog = new File("/home/user/java/GitLab/Task 6/task-6/end.log");
        Scanner scannerStartLog = new Scanner(startLog);
        File abbreviations = new File("/home/user/java/GitLab/Task 6/task-6/abbreviations.txt");
        Scanner scannerAbbreviations = new Scanner(abbreviations);
        System.out.println("---------nameMap---------");
        while (scannerAbbreviations.hasNextLine()) {
            Map<String, String> nameMap = new HashMap<>();
            String lineFromAbbreviations = scannerAbbreviations.nextLine();
            String[] parts = lineFromAbbreviations.split("_");
            abbrev = parts[0];
            name = parts[1];
            car = parts[2];
            putValueToCache(abbrev, nameMap);
            nameMap.put(name, car);
            for (Entry<String, String> map : nameMap.entrySet()) {
                System.out.println(map.getKey() + " | " + map.getValue());
            }
        }
        System.out.println("---------abbreviationsMap---------");
        for (Entry<String, Map<String, String>> map : abbreviationsMap.entrySet()) {
            System.out.println(map.getKey() + " | " + map.getValue());
        }
        scannerAbbreviations.close();
        while (scannerStartLog.hasNextLine()) {
            String lineFromStartLog = scannerStartLog.nextLine();
            startMap.put(lineFromStartLog.substring(0, 3), lineFromStartLog.substring(14, 26));
        }
        scannerStartLog.close();
        Scanner scannerEndLog = new Scanner(endLog);
        while (scannerEndLog.hasNextLine()) {
            String lineFromEndFog = scannerEndLog.nextLine();
            endMap.put(lineFromEndFog.substring(0, 3), lineFromEndFog.substring(14, 26));
        }
        scannerEndLog.close();
        for (String key : endMap.keySet()) {
            if (startMap.keySet().contains(key)) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                Date timeFromEndMap = sdf.parse(endMap.get(key));
                Date timeFromStartMap = sdf.parse(startMap.get(key));
                long durationTime = timeFromEndMap.getTime() - timeFromStartMap.getTime();
                calculateMap.put(key, formatDuration(durationTime));
            }
        }
        System.out.println("---------calculateMap.sorted---------");
        calculateMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
    }

    public void putValueToCache(String key, Map<String, String> value) {
        abbreviationsMap.put(key, value);
    }

    public String formatDuration(long duration) {
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        long milliseconds = duration % 1000;
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }
}
