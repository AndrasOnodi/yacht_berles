import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class CsvReader {
    public List<Berles> readRentals(String filePath) throws IOException {
        List<Berles> rentals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // fejléc átugrása
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length == 6) {
                    int uid = Integer.parseInt(values[0]);
                    int yachtId = Integer.parseInt(values[1]);
                    LocalDate startDate = LocalDate.parse(values[2]);
                    LocalDate endDate = LocalDate.parse(values[3]);
                    int dailyPrice = Integer.parseInt(values[4]);
                    String yachtName = values[5];
                    Berles rental = new Berles(uid, yachtId, startDate, endDate, dailyPrice, yachtName);
                    rentals.add(rental);
                }
            }
        }
        return rentals;
    }
}