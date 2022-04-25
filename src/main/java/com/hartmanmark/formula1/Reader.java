package com.hartmanmark.formula1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Reader {

    private Map<String, String> startMap = new HashMap<>();
    private Map<String, String> endMap = new HashMap<>();
    private Map<String, String> abbreviationsMap = new LinkedHashMap<>();
    private File pathToEndLog;
    private File pathToStartLog;
    private File pathToAbbreviations;
    private String name;
    private String car;
    private String abbrev;
    private FileReader fileReader;
    private Properties properties;
    private Enumenator enumenator;
    private Printer printer;

    public Reader(Enumenator enumenator, Printer printer) {
        this.enumenator = enumenator;
        this.printer = printer;
    }

    public String enter() throws IOException, ParseException {
        setFileReader(new FileReader("resources/path.properties"));
        setProperties(new Properties());
        getProperties().load(getFileReader());
        readEndLog();
        readStartLog();
        readAbbreviationsMap();
        return printer.printer(enumenator.calculateTime(getStartMap(), getEndMap()), abbreviationsMap);
    }

    private void readStartLog() throws IOException {
        File startLog = new File(properties.getProperty("startLog"));
        setPathToStartLog(startLog);
        parseStartLog();
    }

    private void parseStartLog() throws FileNotFoundException {
        Scanner scannerStartLog = new Scanner(getPathToStartLog());
        while (scannerStartLog.hasNextLine()) {
            String lineFromStartLog = scannerStartLog.nextLine();
            startMap.put(lineFromStartLog.substring(0, 3), lineFromStartLog.substring(14, 26));
        }
        scannerStartLog.close();
        setStartMap(startMap);
    }

    private void readEndLog() throws IOException {
        File endLog = new File(properties.getProperty("endLog"));
        setPathToEndLog(endLog);
        parseEndLog();
    }

    private void parseEndLog() throws FileNotFoundException {
        Scanner scannerEndLog = new Scanner(getPathToEndLog());
        while (scannerEndLog.hasNextLine()) {
            String lineFromStartLog = scannerEndLog.nextLine();
            endMap.put(lineFromStartLog.substring(0, 3), lineFromStartLog.substring(14, 26));
        }
        scannerEndLog.close();
        setEndMap(endMap);
    }

    private void readAbbreviationsMap() throws IOException {
        File abbreviations = new File(properties.getProperty("abbreviations"));
        setPathToAbbreviations(abbreviations);
        parseAbbreviations();
    }

    private void parseAbbreviations() throws FileNotFoundException {
        Scanner scannerAbbreviations = new Scanner(getPathToAbbreviations());
        while (scannerAbbreviations.hasNextLine()) {
            String lineFromAbbreviations = scannerAbbreviations.nextLine();
            String[] parts = lineFromAbbreviations.split("_");
            abbrev = parts[0];
            name = parts[1];
            car = parts[2];
            abbreviationsMap.put(abbrev, name + " | " + car);
        }
        scannerAbbreviations.close();
        setAbbreviationsMap(abbreviationsMap);
    }

    public Map<String, String> getStartMap() {
        return startMap;
    }

    public void setStartMap(Map<String, String> startMap) {
        this.startMap = startMap;
    }

    public Map<String, String> getEndMap() {
        return endMap;
    }

    public void setEndMap(Map<String, String> endMap) {
        this.endMap = endMap;
    }

    public Map<String, String> getAbbreviationsMap() {
        return abbreviationsMap;
    }

    public void setAbbreviationsMap(Map<String, String> abbreviationsMap) {
        this.abbreviationsMap = abbreviationsMap;
    }

    public File getPathToEndLog() {
        return pathToEndLog;
    }

    public void setPathToEndLog(File pathToEndLog) {
        this.pathToEndLog = pathToEndLog;
    }

    public File getPathToStartLog() {
        return pathToStartLog;
    }

    public void setPathToStartLog(File pathToStartLog) {
        this.pathToStartLog = pathToStartLog;
    }

    public File getPathToAbbreviations() {
        return pathToAbbreviations;
    }

    public void setPathToAbbreviations(File pathToAbbreviations) {
        this.pathToAbbreviations = pathToAbbreviations;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
