package com.hatrmanmark.formula1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hartmanmark.formula1.service.Calculator;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @Test
    void calculateTime_shouldReturnSolutionOnCalculateTime_whenInputMapsContain_VBM_120105125__VBM_121513987()
            throws ParseException {
        Map<String, String> startMap = new HashMap<String, String>();
        startMap.put("VBM", "12:00:00.000");
        startMap.put("SVF", "12:02:58.917");
        startMap.put("CSR", "12:03:15.145");
        startMap.put("DRR", "12:14:12.054");
        startMap.put("KMH", "12:02:51.003");
        startMap.put("SPF", "12:12:01.035");
        startMap.put("SVM", "12:18:37.735");
        startMap.put("CLS", "12:09:41.921");
        startMap.put("BHS", "12:14:51.985");
        startMap.put("LHM", "12:18:20.125");
        startMap.put("LSW", "12:06:13.511");
        startMap.put("RGH", "12:05:14.511");
        startMap.put("FAM", "12:13:04.512");
        startMap.put("SSW", "12:16:11.648");
        startMap.put("NHR", "12:02:49.914");
        startMap.put("MES", "12:04:45.513");
        startMap.put("EOF", "12:17:58.810");
        startMap.put("PGS", "12:07:23.645");
        startMap.put("KRF", "12:03:01.250");
        Map<String, String> endMap = new HashMap<String, String>();
        endMap.put("VBM", "12:01:12.434");
        endMap.put("SVF", "12:04:11.332");
        endMap.put("CSR", "12:04:28.095");
        endMap.put("DRR", "12:15:24.067");
        endMap.put("KMH", "12:04:04.396");
        endMap.put("SPF", "12:13:13.883");
        endMap.put("SVM", "12:19:50.198");
        endMap.put("CLS", "12:10:54.750");
        endMap.put("BHS", "12:16:05.164");
        endMap.put("LHM", "12:19:32.585");
        endMap.put("LSW", "12:07:26.834");
        endMap.put("RGH", "12:06:27.441");
        endMap.put("FAM", "12:14:17.169");
        endMap.put("SSW", "12:17:24.354");
        endMap.put("NHR", "12:04:02.979");
        endMap.put("MES", "12:05:58.778");
        endMap.put("EOF", "12:19:11.838");
        endMap.put("PGS", "12:08:36.586");
        endMap.put("KRF", "12:04:13.889");
        Map<String, String> calculateMap = new HashMap<>();
        calculateMap.putAll(calculator.calculateTime(startMap, endMap));
        assertEquals(calculateMap, calculator.calculateTime(startMap, endMap));
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
