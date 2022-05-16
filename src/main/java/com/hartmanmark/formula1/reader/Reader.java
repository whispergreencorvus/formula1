package com.hartmanmark.formula1.reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import com.hartmanmark.formula1.printer.Printer;
import com.hartmanmark.formula1.service.Calculator;
import com.hartmanmark.formula1.service.Parser;

public class Reader {

    private File pathToEndLog;
    private File pathToStartLog;
    private File pathToAbbreviations;
    private FileReader fileReader;
    private Properties properties;
    private Calculator calculator;
    private Printer printer;
    private Parser parcer;
    private String pathToProperties = "resources/path.properties";

    public Reader(Calculator calculator, Printer printer, Parser parcer) {
        this.calculator = calculator;
        this.printer = printer;
        this.parcer = parcer;
    }

    public String read() throws IOException, ParseException {
        setFileReader(new FileReader(pathToProperties));
        setProperties(new Properties());
        getProperties().load(getFileReader());
        readEndLog();
        readStartLog();
        readAbbreviationsMap();
        return printer.print(calculator.calculateTime(parcer.parseStartLog(getPathToStartLog()),
                parcer.parseEndLog(getPathToEndLog())), parcer.parseAbbreviations(getPathToAbbreviations()));
    }

    private void readStartLog() throws IOException {
        File startLog = new File(properties.getProperty("startLog"));
        setPathToStartLog(startLog);
    }

    private void readEndLog() throws IOException {
        File endLog = new File(properties.getProperty("endLog"));
        setPathToEndLog(endLog);
    }

    private void readAbbreviationsMap() throws IOException {
        File abbreviations = new File(properties.getProperty("abbreviations"));
        setPathToAbbreviations(abbreviations);
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
