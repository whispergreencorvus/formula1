package com.hartmanmark.formula1;

import java.io.IOException;
import java.text.ParseException;

import com.hartmanmark.formula1.printer.Printer;
import com.hartmanmark.formula1.reader.Reader;
import com.hartmanmark.formula1.service.Calculator;
import com.hartmanmark.formula1.service.Parser;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Calculator calculator = new Calculator();
        Printer printer = new Printer();
        Parser parcer = new Parser();
        Reader reader = new Reader(calculator, printer, parcer);
        System.out.println(reader.read());
    }
}
