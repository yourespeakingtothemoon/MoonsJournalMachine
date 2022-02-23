package edu.neumont.oop.model;
import java.time.LocalDate;

public class Entry {
    // Entry properties
    private String title;
    private String body;
    private LocalDate date;
    private String category;
    //Contructor
    public Entry(){}
    public Entry(String bodyText, LocalDate date, String titleText, String category) {
        this.body = bodyText;
        this.date = date;
        this.title = titleText;
       this.category = category.toUpperCase();
    }
    //getters and setters
    public void setCategory(String category) {this.category = category.toUpperCase();}
    public void setBody(String body) {this.body = body;}
    public void setDate(LocalDate date) {this.date = date;}
    public void setTitle(String title) {this.title = title;}
    public String getTitle() {return this.title;}
    public LocalDate getDate() {return this.date;}
    public String getBody() {return this.body;}
    public String getCategory() {return this.category;}
//2 String 2 Override
    @Override
    public String toString() {
        return "--" + this.title + "--" + "\n=======================\n\n" +
                this.body + "\n" +
                "Entry Date:" + this.date+" || "+"Category"+this.category+"\n";
    }

}