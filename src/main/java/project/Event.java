package project;

import java.time.LocalDate;

public class Event {
    public String eventName;
    public String eventStartTime;
    public String eventEndTime;
    public String eventLocation;
    public String eventCategory;
    public String eventDescription;
    public LocalDate eventDate;

    public Event(String eventName, String eventStartTime, String eventEndTime, String eventCategory, String eventLocation, String eventDescription, LocalDate eventDate){
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventCategory = eventCategory;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    }

    public String getEventName(){
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
