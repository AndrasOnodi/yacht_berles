
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        try {
            CsvReader csvReader = new CsvReader();
            List<Berles> berlesek = csvReader.readRentals("yacht_berlesek_2024.csv");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Adja meg a kívánt hónapot (1-12): ");
            int honap = scanner.nextInt();

            int haviBevetel = 0;
            for (Berles b : berlesek) {
                LocalDate monthStart = LocalDate.of(2024, honap, 1);
                LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

                LocalDate start = b.getStartDate().isAfter(monthStart) ? b.getStartDate() : monthStart;
                LocalDate end = b.getEndDate().isBefore(monthEnd) ? b.getEndDate() : monthEnd;

                if (!(b.getEndDate().isBefore(monthStart) || b.getStartDate().isAfter(monthEnd))) 
                {
                    haviBevetel += b.getTotalPrice();
                }
            }

            System.out.println("A(z) " + honap + ". hónap bevétele: " + haviBevetel + " Euró");
            
            int osszBevetel = berlesek.stream().mapToInt(Berles::getTotalPrice).sum();
            System.out.println("A 2024-es év összes bevétele: " + osszBevetel + " Euró");

            Berles maxBerles = berlesek.stream()
                .max((a, b) -> Integer.compare(a.getTotalPrice(), b.getTotalPrice()))
                .orElse(null);

            if (maxBerles != null) 
            {
                System.out.println("A legdrágább bérelhető Yacht neve: " + maxBerles.getYachtName());
                System.out.println("Teljes ára: " + maxBerles.getTotalPrice() + " Euró");

            long yachtokSzama = berlesek.stream()
                .map(Berles::getYachtId)
                .distinct()
                .count();

            System.out.println("A bérelt Yachtok száma összesen: " + yachtokSzama);

            Map<String, Long> yachtBérlések = berlesek.stream()
                .collect(Collectors.groupingBy(Berles::getYachtName, Collectors.counting()));

            String legtobbszorBereltYacht = yachtBérlések.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nincs adat");

            System.out.println("A leggyakrabban bérelt yacht a: " + legtobbszorBereltYacht);

            double atlagosIdotartam = berlesek.stream()
                .mapToInt(Berles::getDays)
                .average()
                .orElse(0);

            System.out.printf("Átlagos bérlési időtartam: %.2f nap\n", atlagosIdotartam);
}

        } catch (Exception e) {
            System.err.println("Hiba az adatok feldolgozása során: " + e.getMessage());
        }
    }
}