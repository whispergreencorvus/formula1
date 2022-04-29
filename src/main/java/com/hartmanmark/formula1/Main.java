package com.hartmanmark.formula1;

import java.io.IOException;
import java.text.ParseException;

import com.hartmanmark.formula1.exception.ParseExceptionInEndMap;
import com.hartmanmark.formula1.exception.ParseExceptionInStartMap;
import com.hartmanmark.formula1.printer.Printer;
import com.hartmanmark.formula1.reader.Reader;
import com.hartmanmark.formula1.service.Calculator;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, ParseExceptionInEndMap, ParseExceptionInStartMap {
        Calculator calculator = new Calculator();
        Printer printer = new Printer();
        Reader reader = new Reader(calculator, printer);
        System.out.println(reader.read());
    }
}
