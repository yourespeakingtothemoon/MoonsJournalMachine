package edu.neumont.oop.model;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Journal {
    //journal properties
    private String volumeName;
    private ArrayList<Entry> entries = new ArrayList<>();
    // private String pswd;
    private String filename;
    //constructors
    public Journal(){

    }
    public Journal(Entry firstEntry, String bookName) {
        //this.pswd = passwd;
        this.volumeName = bookName;
        this.entries.add(firstEntry);
    }
    public Journal(ArrayList<Entry> entries, String bookName) {
       // this.pswd = passwd;
        this.volumeName = bookName;
        this.entries = entries;

    }
    // add entries, see entry list, catlist generator, getter and setter for login
    public void addEntry(Entry entry) {
        this.entries.add(entry);

    }
    public ArrayList<Entry> getEntries() {
        return this.entries;
    }
    public String seeEntryList() {
        String entryList = "--ENTRIES--\n" +
                "======================\n";
        for (int pos = 0; pos < this.entries.size(); pos++) {
            entryList += this.entries.get(pos).getDate().toString() + " | " + this.entries.get(pos).getTitle() + "\n";
        }
        return entryList;
    }
    public String seeCatList() {
        String catList = this.volumeName.toUpperCase()+" CATEGORIES\n" +
                "=======================\n";
            ArrayList<String> catArray = new ArrayList<>();
        for (int pos = 0; pos < this.entries.size(); pos++) {
           String currentCat = this.entries.get(pos).getCategory();
               if(!catArray.contains(currentCat)){
                   catArray.add(currentCat);
               }
        }
            for(int pos=0;pos<catArray.size();pos++){
                catList+=catArray.get(pos)+"\n";
            }
        return catList;
    }
   /* public String getPswd() {
        return pswd;
    }*/
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    /*public void changePswd(String pswd) {
        this.pswd = pswd;
    }*/
    public String getVolumeName() {
        return volumeName;
    }
}
