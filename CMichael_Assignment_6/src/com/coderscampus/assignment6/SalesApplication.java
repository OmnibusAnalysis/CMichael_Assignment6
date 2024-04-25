package com.coderscampus.assignment6;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesApplication {

    public static final String Model3 = "model3.csv";
    public static final String ModelS = "modelS.csv";
    public static final String ModelX = "modelX.csv";

    public static void main(String[] args) throws IOException {

        FileService tsla = new FileService();
        List<SalesData> m3 = tsla.loadSalesData(Model3);
        List<SalesData> ms = tsla.loadSalesData(ModelS);
        List<SalesData> mx = tsla.loadSalesData(ModelX);

        generateSalesDataReport(m3, "Model 3");
        generateSalesDataReport(ms, "Model S");
        generateSalesDataReport(mx, "Model X");

    }

    private static void generateSalesDataReport(List<SalesData> salesData, modelSalesData String carModel) {

        System.out.println(carModel + " Yearly Sales Report");
        System.out.println("------------------");

        Map<Integer, List<SalesData>> salesByYear = modelSalesData.stream();
        .collect(Collectors.groupingBy())

    }
}
