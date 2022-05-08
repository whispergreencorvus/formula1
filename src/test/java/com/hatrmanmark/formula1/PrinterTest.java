package com.hatrmanmark.formula1;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import com.hartmanmark.formula1.printer.Printer;

class PrinterTest {

    @Test
    void print_shouldReturnSortedListOfRacers_whenInputMapsContain_Time_Name_Car() throws ParseException {
        Printer printer = new Printer();
        Map<String, String> calculateMap = new HashMap<String, String>();
        calculateMap.put("VBM", "00:01:12.434");
        calculateMap.put("SVF", "00:01:12.415");
        calculateMap.put("CSR", "00:01:12.950");
        calculateMap.put("DRR", "00:01:12.013");
        calculateMap.put("KMH", "00:01:13.393");
        calculateMap.put("SPF", "00:01:12.848");
        calculateMap.put("SVM", "00:01:12.463");
        calculateMap.put("CLS", "00:01:12.829");
        calculateMap.put("BHS", "00:01:13.179");
        calculateMap.put("LHM", "00:01:12.460");
        calculateMap.put("LSW", "00:01:13.323");
        calculateMap.put("RGH", "00:01:12.930");
        calculateMap.put("FAM", "00:01:12.657");
        calculateMap.put("SSW", "00:01:12.706");
        calculateMap.put("NHR", "00:01:13.065");
        calculateMap.put("MES", "00:01:13.265");
        calculateMap.put("EOF", "00:01:13.028");
        calculateMap.put("PGS", "00:01:12.434");
        calculateMap.put("KRF", "00:01:12.639");
        Map<String, String> abbreviationsMap = new HashMap<String, String>();
        abbreviationsMap.put("DRR", "Daniel Ricciardo | RED BULL RACING TAG HEUER");
        abbreviationsMap.put("SVF", "Sebastian Vettel | FERRARI");
        abbreviationsMap.put("LHM", "Lewis Hamilton | MERCEDES");
        abbreviationsMap.put("KRF", "Kimi Raikkonen | FERRARI");
        abbreviationsMap.put("VBM", "Valtteri Bottas | MERCEDES");
        abbreviationsMap.put("EOF", "Esteban Ocon | FORCE INDIA MERCEDES");
        abbreviationsMap.put("FAM", "Fernando Alonso | MCLAREN RENAULT");
        abbreviationsMap.put("CSR", "Carlos Sainz | RENAULT");
        abbreviationsMap.put("SPF", "Sergio Perez | FORCE INDIA MERCEDES");
        abbreviationsMap.put("PGS", "Pierre Gasly | SCUDERIA TORO ROSSO HONDA");
        abbreviationsMap.put("NHR", "Nico Hulkenberg | RENAULT");
        abbreviationsMap.put("SVM", "Stoffel Vandoorne | MCLAREN RENAULT");
        abbreviationsMap.put("SSW", "Sergey Sirotkin | WILLIAMS MERCEDES");
        abbreviationsMap.put("CLS", "Charles Leclerc | SAUBER FERRARI");
        abbreviationsMap.put("RGH", "Romain Grosjean | HAAS FERRARI");
        abbreviationsMap.put("BHS", "Brendon Hartley | SCUDERIA TORO ROSSO HONDA");
        abbreviationsMap.put("MES", "Marcus Ericsson | SAUBER FERRARI");
        abbreviationsMap.put("LSW", "Lance Stroll | WILLIAMS MERCEDES");
        abbreviationsMap.put("KMH", "Kevin Magnussen | HAAS FERRARI");
        assertEquals("1. Daniel Ricciardo | RED BULL RACING TAG HEUER | 00:01:12.013\n"
                + "2. Sebastian Vettel | FERRARI | 00:01:12.415\n"
                + "3. Pierre Gasly | SCUDERIA TORO ROSSO HONDA | 00:01:12.434\n"
                + "4. Valtteri Bottas | MERCEDES | 00:01:12.434\n" + "5. Lewis Hamilton | MERCEDES | 00:01:12.460\n"
                + "6. Stoffel Vandoorne | MCLAREN RENAULT | 00:01:12.463\n"
                + "7. Kimi Raikkonen | FERRARI | 00:01:12.639\n"
                + "8. Fernando Alonso | MCLAREN RENAULT | 00:01:12.657\n"
                + "9. Sergey Sirotkin | WILLIAMS MERCEDES | 00:01:12.706\n"
                + "10. Charles Leclerc | SAUBER FERRARI | 00:01:12.829\n"
                + "11. Sergio Perez | FORCE INDIA MERCEDES | 00:01:12.848\n"
                + "12. Romain Grosjean | HAAS FERRARI | 00:01:12.930\n" + "13. Carlos Sainz | RENAULT | 00:01:12.950\n"
                + "14. Esteban Ocon | FORCE INDIA MERCEDES | 00:01:13.028\n"
                + "15. Nico Hulkenberg | RENAULT | 00:01:13.065\n"
                + "--------------------------------------------------------------\n"
                + "16. Brendon Hartley | SCUDERIA TORO ROSSO HONDA | 00:01:13.179\n"
                + "17. Marcus Ericsson | SAUBER FERRARI | 00:01:13.265\n"
                + "18. Lance Stroll | WILLIAMS MERCEDES | 00:01:13.323\n"
                + "19. Kevin Magnussen | HAAS FERRARI | 00:01:13.393\n" + "",
                printer.print(calculateMap, abbreviationsMap));
    }
}
