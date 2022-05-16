package com.hartmanmark.formula1.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hartmanmark.formula1.model.Racer;

public class Parser {

    private Map<String, String> startMap = new HashMap<>();
    private Map<String, String> endMap = new HashMap<>();
    private Map<String, String> abbreviationsMap = new LinkedHashMap<>();
    private String name;
    private String car;
    private String abbreviation;

    public Map<String, String> parseStartLog(File pathToStartLog) throws FileNotFoundException {
        Scanner scannerStartLog = new Scanner(pathToStartLog);
        while (scannerStartLog.hasNextLine()) {
            String lineFromStartLog = scannerStartLog.nextLine();
            String abbrev = checkAbbreviation(lineFromStartLog.substring(0, 3));
            checkDate(lineFromStartLog.substring(3, 13));
            String time = checkTime(lineFromStartLog.substring(14, 26));
            startMap.put(abbrev, time);
        }
        scannerStartLog.close();
        return startMap;
    }

    private String checkAbbreviation(String str) {
        if (str.matches("[A-Z]+")) {
            return str;
        } else {
            throw new IllegalArgumentException(
                    "The input abbreviations must be letters, 3 characters long, uppercase. For example: [ABC]");
        }
    }

    private String checkDate(String data) {
        String datePattern = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        if (data.matches(datePattern)) {
            return data;
        } else {
            throw new IllegalArgumentException(
                    "The format of input date must be [yyyy-MM-dd]. For example: [2018-05-24]");
        }
    }

    private String checkTime(String time) {
        String datePattern = "(?:[0-9]{2}:[0-9]{2}:[0-9]{2})?(?:[.][0-9]{3})?";
        if (time.matches(datePattern)) {
            return time;
        } else {
            throw new IllegalArgumentException(
                    "The format of input time must be [HH:MM:SS.mmm]. For example: [12:02:58.917]");
        }
    }

    public Map<String, String> parseEndLog(File pathToEndLog) throws FileNotFoundException {
        Scanner scannerEndLog = new Scanner(pathToEndLog);
        while (scannerEndLog.hasNextLine()) {
            String lineFromEndLog = scannerEndLog.nextLine();
            String abbrev = checkAbbreviation(lineFromEndLog.substring(0, 3));
            checkDate(lineFromEndLog.substring(3, 13));
            String time = checkTime(lineFromEndLog.substring(14, 26));
            endMap.put(abbrev, time);
        }
        scannerEndLog.close();
        return endMap;
    }

    public Map<String, String> parseAbbreviations(File pathToAbbreviations) throws FileNotFoundException {
        Racer racer = new Racer(name, car, abbreviation);
        Scanner scannerAbbreviations = new Scanner(pathToAbbreviations);
        while (scannerAbbreviations.hasNextLine()) {
            String lineFromAbbreviations = scannerAbbreviations.nextLine();
            List<String> list = Arrays.asList(lineFromAbbreviations.split("_"));
            checkAbbreviation(list.get(0));
            racer.setAbbreviation(list.get(0));
            racer.setName(list.get(1));
            racer.setCar(list.get(2));
            abbreviationsMap.put(racer.getAbbreviation(), racer.getName() + " | " + racer.getCar());
        }
        scannerAbbreviations.close();
        return abbreviationsMap;
    }
}
