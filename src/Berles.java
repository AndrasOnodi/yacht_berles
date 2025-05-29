import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Berles {
    private int uid;
    private int yachtId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int dailyPrice;
    private String yachtName;

    public Berles(int uid, int yachtId, LocalDate startDate, LocalDate endDate, int dailyPrice, String yachtName) {
        this.uid = uid;
        this.yachtId = yachtId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = dailyPrice;
        this.yachtName = yachtName;
    }

    public int getUid() { return uid; }
    public int getYachtId() { return yachtId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getDailyPrice() { return dailyPrice; }
    public String getYachtName() { return yachtName; }

    // Napok száma (mindkét napot beleértve)
    public int getDays() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    // Teljes bérleti díj
    public int getTotalPrice() {
        return getDays() * dailyPrice;
    }
}