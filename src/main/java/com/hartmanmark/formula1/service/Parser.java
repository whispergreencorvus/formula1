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
    private Validator validator =  new Validator();

    public Map<String, String> parseStartLog(File pathToStartLog) throws FileNotFoundException {
        Scanner scannerStartLog = new Scanner(pathToStartLog);
        while (scannerStartLog.hasNextLine()) {
            String lineFromStartLog = scannerStartLog.nextLine();
            String abbrev = validator.checkAbbreviation(lineFromStartLog.substring(0, 3));
            validator.checkDate(lineFromStartLog.substring(3, 13));
            String time = validator.checkTime(lineFromStartLog.substring(14, 26));
            startMap.put(abbrev, time);
        }
        scannerStartLog.close();
        return startMap;
    }

    public Map<String, String> parseEndLog(File pathToEndLog) throws FileNotFoundException {
        Scanner scannerEndLog = new Scanner(pathToEndLog);
        while (scannerEndLog.hasNextLine()) {
            String lineFromEndLog = scannerEndLog.nextLine();
            String abbrev = validator.checkAbbreviation(lineFromEndLog.substring(0, 3));
            validator.checkDate(lineFromEndLog.substring(3, 13));
            String time = validator.checkTime(lineFromEndLog.substring(14, 26));
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
            validator.checkAbbreviation(list.get(0));
            racer.setAbbreviation(list.get(0));
            racer.setName(list.get(1));
            racer.setCar(list.get(2));
            abbreviationsMap.put(racer.getAbbreviation(), racer.getName() + " | " + racer.getCar());
        }
        scannerAbbreviations.close();
        return abbreviationsMap;
    }
}
