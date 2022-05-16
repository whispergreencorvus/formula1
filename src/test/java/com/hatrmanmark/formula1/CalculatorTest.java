package com.hatrmanmark.formula1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;

import com.hartmanmark.formula1.service.Calculator;
import com.hartmanmark.formula1.service.Parser;

class CalculatorTest {

    private Calculator calculator = new Calculator();
    private Parser parcer = new Parser();

    @Test
    void calculateTime_shouldReturnSolutionOnCalculateTime_whenInputMapsContain_VBM_120105125__VBM_121513987()
            throws ParseException, IOException {
        String pathToProperties = "resources/path.properties";
        FileReader fileReader = new FileReader(pathToProperties);
        Properties properties = new Properties();
        properties.load(fileReader);
        File startLog = new File(properties.getProperty("startLog"));
        File endLog = new File(properties.getProperty("endLog"));
        Map<String, String> endMap = parcer.parseEndLog(endLog);
        Map<String, String> startMap = parcer.parseStartLog(startLog);
        Map<String, String> actualMap = new HashMap<>();
        actualMap.putAll(calculator.calculateTime(startMap, endMap));
        assertEquals(actualMap, calculator.calculateTime(startMap, endMap));
    }

    @Test
    void calculateTime_shouldIllegalArgumentException_whenEndMapContainWrongTimeFormat() {
        Map<String, String> startMap = new HashMap<String, String>();
        startMap.put("VBM", "12:01:05.125");
        Map<String, String> endMap = new HashMap<String, String>();
        endMap.put("VBM", "another time format");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTime(startMap, endMap);
        });
        assertEquals("Format of chosen time (should be HH:mm:ss.SSS) is invalid: " + "another time format",
                exception.getMessage());
    }

    @Test
    void calculateTime_shouldIllegalArgumentException_whenStartMapContainWrongTimeFormat() {
        Map<String, String> startMap = new HashMap<String, String>();
        startMap.put("VBM", "another time format");
        Map<String, String> endMap = new HashMap<String, String>();
        endMap.put("VBM", "12:15:13.987");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTime(startMap, endMap);
        });
        assertEquals("Format of chosen time (should be HH:mm:ss.SSS) is invalid: " + "another time format",
                exception.getMessage());
    }
}
