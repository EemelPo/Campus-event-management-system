import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String name;
    private String category;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String name, String category, String location, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Event{name='" + name + "', category='" + category + "', location='" + location + "', date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
}
