package com.hatrmanmark.formula1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;

import com.hartmanmark.formula1.service.Parser;

class ParserTest {

    private Parser parcer = new Parser();

    @Test
    void testParseStartLog_sholdReturnNullPointerException_whenInputKeyInPropertiesIsWrong() throws IOException {
        try {
            String pathToProperties = "/home/user/java/GitLab/Task 6/task-6/src/main/resources/path.properties";
            FileReader fileReader = new FileReader(pathToProperties);
            Properties properties = new Properties();
            properties.load(fileReader);
            File startLog = new File(properties.getProperty("wrongKey"));
            parcer.parseStartLog(startLog);
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testParseStartLog_shouldReturnParsedMap_whenInputFileContainsPath() throws IOException {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("VBM", "12:00:00.000");
        expectedMap.put("SVF", "12:02:58.917");
        expectedMap.put("CSR", "12:03:15.145");
        expectedMap.put("DRR", "12:14:12.054");
        expectedMap.put("KMH", "12:02:51.003");
        expectedMap.put("SPF", "12:12:01.035");
        expectedMap.put("SVM", "12:18:37.735");
        expectedMap.put("CLS", "12:09:41.921");
        expectedMap.put("BHS", "12:14:51.985");
        expectedMap.put("LHM", "12:18:20.125");
        expectedMap.put("LSW", "12:06:13.511");
        expectedMap.put("RGH", "12:05:14.511");
        expectedMap.put("FAM", "12:13:04.512");
        expectedMap.put("SSW", "12:16:11.648");
        expectedMap.put("NHR", "12:02:49.914");
        expectedMap.put("MES", "12:04:45.513");
        expectedMap.put("EOF", "12:17:58.810");
        expectedMap.put("PGS", "12:07:23.645");
        expectedMap.put("KRF", "12:03:01.250");
        String pathToProperties = "/home/user/java/GitLab/Task 6/task-6/src/main/resources/path.properties";
        FileReader fileReader = new FileReader(pathToProperties);
        Properties properties = new Properties();
        properties.load(fileReader);
        File startLog = new File(properties.getProperty("startTest"));
        assertEquals(expectedMap, parcer.parseStartLog(startLog));
    }

    @Test
    void testParseEndLog_shouldReturnParsedMap_whenInputFileContainsPath() throws IOException {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("VBM", "12:01:12.434");
        expectedMap.put("SVF", "12:04:11.332");
        expectedMap.put("CSR", "12:04:28.095");
        expectedMap.put("DRR", "12:15:24.067");
        expectedMap.put("KMH", "12:04:04.396");
        expectedMap.put("SPF", "12:13:13.883");
        expectedMap.put("SVM", "12:19:50.198");
        expectedMap.put("CLS", "12:10:54.750");
        expectedMap.put("BHS", "12:16:05.164");
        expectedMap.put("LHM", "12:19:32.585");
        expectedMap.put("LSW", "12:07:26.834");
        expectedMap.put("RGH", "12:06:27.441");
        expectedMap.put("FAM", "12:14:17.169");
        expectedMap.put("SSW", "12:17:24.354");
        expectedMap.put("NHR", "12:04:02.979");
        expectedMap.put("MES", "12:05:58.778");
        expectedMap.put("EOF", "12:19:11.838");
        expectedMap.put("PGS", "12:08:36.586");
        expectedMap.put("KRF", "12:04:13.889");
        String pathToProperties = "/home/user/java/GitLab/Task 6/task-6/src/main/resources/path.properties";
        FileReader fileReader = new FileReader(pathToProperties);
        Properties properties = new Properties();
        properties.load(fileReader);
        File endLog = new File(properties.getProperty("endTest"));
        assertEquals(expectedMap, parcer.parseEndLog(endLog));
    }

    @Test
    void testParseAbbreviations_shouldReturnParsedMap_whenInputFileContainsPath() throws IOException {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("VBM", "Valtteri Bottas | MERCEDES");
        expectedMap.put("SVF", "Sebastian Vettel | FERRARI");
        expectedMap.put("CSR", "Carlos Sainz | RENAULT");
        expectedMap.put("DRR", "Daniel Ricciardo | RED BULL RACING TAG HEUER");
        expectedMap.put("KMH", "Kevin Magnussen | HAAS FERRARI");
        expectedMap.put("SPF", "Sergio Perez | FORCE INDIA MERCEDES");
        expectedMap.put("SVM", "Stoffel Vandoorne | MCLAREN RENAULT");
        expectedMap.put("CLS", "Charles Leclerc | SAUBER FERRARI");
        expectedMap.put("BHS", "Brendon Hartley | SCUDERIA TORO ROSSO HONDA");
        expectedMap.put("LHM", "Lewis Hamilton | MERCEDES");
        expectedMap.put("LSW", "Lance Stroll | WILLIAMS MERCEDES");
        expectedMap.put("RGH", "Romain Grosjean | HAAS FERRARI");
        expectedMap.put("FAM", "Fernando Alonso | MCLAREN RENAULT");
        expectedMap.put("SSW", "Sergey Sirotkin | WILLIAMS MERCEDES");
        expectedMap.put("NHR", "Nico Hulkenberg | RENAULT");
        expectedMap.put("MES", "Marcus Ericsson | SAUBER FERRARI");
        expectedMap.put("EOF", "Esteban Ocon | FORCE INDIA MERCEDES");
        expectedMap.put("PGS", "Pierre Gasly | SCUDERIA TORO ROSSO HONDA");
        expectedMap.put("KRF", "Kimi Raikkonen | FERRARI");
        String pathToProperties = "/home/user/java/GitLab/Task 6/task-6/src/main/resources/path.properties";
        FileReader fileReader = new FileReader(pathToProperties);
        Properties properties = new Properties();
        properties.load(fileReader);
        File abbreviations = new File(properties.getProperty("abbreviationsTest"));
        assertEquals(expectedMap, parcer.parseAbbreviations(abbreviations));
    }

    @Test
    void test1ParseAbbreviations_shouldReturnIllegalArgumentException_whenInputFileContainsWrongAbbreviationsFormat() throws IOException {
        File abbreviationsTest = new File("/home/user/java/GitLab/Task 6/task-6/src/test/resources/abbreviationsError.txt");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            parcer.parseAbbreviations(abbreviationsTest);
        });
        assertEquals("The input abbreviations must be letters, 3 characters long, uppercase. For example: [ABC]",
                exception.getMessage());
    }

    @Test
    void testParseDate_shouldReturnIllegalArgumentException_whenInputFileContainsWrongDateFormat() throws IOException {
        File startTest = new File("/home/user/java/GitLab/Task 6/task-6/src/test/resources/startError.log");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            parcer.parseStartLog(startTest);
        });
        assertEquals("The format of input date must be [yyyy-MM-dd]. For example: [2018-05-24]",
                exception.getMessage());
    }

    @Test
    void testParseTime_shouldReturnIllegalArgumentException_whenInputFileContainsWrongTimeFormat() throws IOException {
        File endTest = new File("/home/user/java/GitLab/Task 6/task-6/src/test/resources/endError.log");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            parcer.parseEndLog(endTest);
        });
        assertEquals("The format of input time must be [HH:MM:SS.mmm]. For example: [12:02:58.917]",
                exception.getMessage());
    }
}
