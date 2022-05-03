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

public class Parcer {

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
            startMap.put(lineFromStartLog.substring(0, 3), lineFromStartLog.substring(14, 26));
        }
        scannerStartLog.close();
        return startMap;
    }

    public Map<String, String> parseEndLog(File pathToEndLog) throws FileNotFoundException {
        Scanner scannerEndLog = new Scanner(pathToEndLog);
        while (scannerEndLog.hasNextLine()) {
            String lineFromStartLog = scannerEndLog.nextLine();
            endMap.put(lineFromStartLog.substring(0, 3), lineFromStartLog.substring(14, 26));
        }
        scannerEndLog.close();
        return endMap;
    }

    public Map<String, String> parseAbbreviations(File pathToAbbreviations) throws FileNotFoundException {
        Racer racer = new Racer(name, car, abbreviation);
        Scanner scannerAbbreviations = new Scanner(pathToAbbreviations);
        while (scannerAbbreviations.hasNextLine()) {
            String lineFromAbbreviations = scannerAbbreviations.nextLine();
            List<String> l = Arrays.asList(lineFromAbbreviations.split("_"));
            racer.setAbbreviation(l.get(0));
            racer.setName(l.get(1));
            racer.setCar(l.get(2));
            abbreviationsMap.put(racer.getAbbreviation(), racer.getName() + " | " + racer.getCar());
        }
        scannerAbbreviations.close();
        return abbreviationsMap;
    }
}
