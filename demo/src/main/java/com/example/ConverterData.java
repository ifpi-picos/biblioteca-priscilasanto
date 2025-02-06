package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterData {
    public static Date converterParaData(String dataStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data. Use o formato dd/MM/yyyy.");
            return null;
        }
    }
}