package org.example.sep_projecta;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventModel {
    // Added id field to store the auto-generated id from the database.
    private int id;

    private String eventName;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;
    private String eventLocation;
    private String eventCategory;
    private String eventDescription;
    private LocalDate eventDate;
    private int eventMaxAtt;
    private int eventAttQuant;
    private int creatorid;

    // Constructor without id for creating new events.
    public EventModel(String eventName, LocalTime eventStartTime, LocalTime eventEndTime,
                      String eventCategory, String eventLocation, String eventDescription,
                      LocalDate eventDate, int eventMaxAtt, int eventAttQuant, int creatorid) {
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventCategory = eventCategory;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventMaxAtt = eventMaxAtt;
        this.eventAttQuant = eventAttQuant;
        this.creatorid = creatorid;
    }

    // Constructor with id for loading events from the database.
    public EventModel(int id, String eventName, LocalTime eventStartTime, LocalTime eventEndTime,
                      String eventCategory, String eventLocation, String eventDescription,
                      LocalDate eventDate, int eventMaxAtt, int eventAttQuant, int creatorid) {
        this.id = id;
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventCategory = eventCategory;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventMaxAtt = eventMaxAtt;
        this.eventAttQuant = eventAttQuant;
        this.creatorid = creatorid;
    }

    // Getter and setter for id.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName(){
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalTime eventEndTime) {
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

    public int getEventMaxAtt(){
        return eventMaxAtt;
    }

    public void setEventMaxAtt(int eventMaxAtt) {
        this.eventMaxAtt = eventMaxAtt;
    }

    public int getEventAttQuant(){
        return eventAttQuant;
    }

    public void setEventAttQuant(int eventAttQuant) {
        this.eventAttQuant = eventAttQuant;
    }

    public int getCreatorid(){
        return creatorid;
    }

    public void setCreatorid(int creatorid) {
        this.creatorid = creatorid;
    }
}