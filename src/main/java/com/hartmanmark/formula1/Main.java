package com.hartmanmark.formula1;

import java.io.IOException;
import java.text.ParseException;
import com.hartmanmark.formula1.printer.Printer;
import com.hartmanmark.formula1.reader.Reader;
import com.hartmanmark.formula1.service.Calculator;
import com.hartmanmark.formula1.service.Parcer;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Calculator calculator = new Calculator();
        Printer printer = new Printer();
        Parcer parcer = new Parcer();
        Reader reader = new Reader(calculator, printer, parcer);
        System.out.println(reader.read());
    }
}
