import com.coderscampus.assignment6.CSVParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TeslaSales {
    public static void main(String[] args) {
       try {
           analyzeSales("model3.csv", 2017, 2019, "Model 3 Report");
           analyzeSales("modelS.csv", 2016, 2019, "Model S Report");
           analyzeSales("modelX.csv", 2016, 2019, "Model X Report");
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    private static void analyzeSales(String fileName, int startYear, int endYear, String modelName) throws IOException {
        Map<LocalDate, Integer> salesData = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        try (CSVParser parser = new CSVParser(new FileReader(fileName), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record = parser) {
                LocalDate date = LocalDate.parse(record.get("Date"), formatter);
                if (date.getYear() >= startYear && date.getYear() <= endYear) {
                    int sales = Integer.parseInt(record.get("Sales"));
                    salesData.merge(date.withDayOfMonth(1), sales, Integer::sum);
                }
            }
        }

        LocalDate bestMonth = null; LocalDate worstMonth = null;
        int bestSales = 0, worstSales = Integer.MAX_VALUE;

        for (Map.Entry<LocalDate, Integer> entry : salesData.entrySet()) {
            if (entry.getValue() > bestSales) {
                bestSales = entry.getValue();
                bestMonth = entry.getKey();
            }
            if (entry.getValue() < worstSales) {
                worstSales = entry.getValue();
                worstMonth = entry.getKey();
            }
        }

        writeAnalysisToFile(modelName, bestMonth, bestSales, worstMonth, worstSales);
    }

    private static void writeAnalysisToFile(String modelName, LocalDate bestMonth, int bestSales, LocalDate worstMonth, int worstSales) throws IOException {
        try (FileWriter writer = new FileWriter("sales_analysis.txt", true)) {
            writer.write(modelName + "\n");
            writer.write("Best Month: " + bestMonth + ", Sales: " + bestSales + "\n");
            writer.write("Worst Month: " + worstMonth + ", Sales: " + worstSales + "\n\n");
        }
    }

}
