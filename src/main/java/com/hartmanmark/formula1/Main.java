package com.hartmanmark.formula1;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Enumenator enumenator = new Enumenator();
        Printer printer = new Printer();
        Reader r = new Reader(enumenator, printer);
        System.out.println(r.enter());
    }
}
