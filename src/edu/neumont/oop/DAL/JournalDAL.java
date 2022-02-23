package edu.neumont.oop.DAL;
import edu.neumont.oop.model.Entry;
import edu.neumont.oop.model.Journal;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class JournalDAL {
    //write/read
    //switching to a folder system.
    public static void writeFile(Journal journalToSave, String filename) {

        try {
            FileWriter file = new FileWriter(filename+".json",false);
            file.write(toJSON(journalToSave).toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("pop");
    }
    public static JSONObject toJSON(Journal journalToSave){
    //JsonObject for fileoutput
    JSONObject json = new JSONObject();
    json.put("volumeName", journalToSave.getVolumeName());
    //json.put("pass", journalToSave.getPswd());
    json.put("filename", journalToSave.getFilename());
    JSONArray entryList = new JSONArray();
    for(int pos = 0; pos < journalToSave.getEntries().size(); pos++){
        JSONObject entry = new JSONObject();
        entry.put("date",journalToSave.getEntries().get(pos).getDate().toString());
        entry.put("title",journalToSave.getEntries().get(pos).getTitle());
        entry.put("body",journalToSave.getEntries().get(pos).getBody());
        entry.put("category",journalToSave.getEntries().get(pos).getCategory());
        entryList.add(entry);
        //System.out.println(entryList.toString());
    }
    json.put("entries",entryList);
    return json;
}
    public static Journal readFile(String filename) throws ParseException {
       try{
        //JSONParser pars = new JSONParser();
        FileReader file = new FileReader(filename+".json");
        JSONObject obj = (JSONObject) new JSONParser().parse(file);
        //JSONObject firstEntry = (JSONObject) pars.parse(new FileReader(filename+"\\"+filename +"0.json"));
        Journal load = new Journal(loadEntries(obj),(String)obj.get("volumeName"));
        load.setFilename((String) obj.get("filename"));

        /* for(int pos=0; pos < load.getSize();pos++){
            JSONObject entry = (JSONObject) pars.parse(new FileReader(filename+"\\"+filename +pos +".json"));
            load.addEntry(new Entry((String) entry.get("body"), (LocalDate) entry.get("date"), (String) entry.get("title")));
            System.out.println("pop");
        }*/
        return load;}
       catch(Exception FileNotFound){
           System.out.println("file not found!");
       return null;
       }

    }
    //entry load
    public static ArrayList<Entry> loadEntries(JSONObject obj) {
        JSONArray jsonEntries = (JSONArray) obj.get("entries");

        ArrayList<Entry> returnList = new ArrayList<>();
        for (int pos = 0; pos < jsonEntries.size(); pos++) {
            JSONObject entry = (JSONObject) jsonEntries.get(pos);
            String body = (String) entry.get("body");
            String title = (String) entry.get("title");
            LocalDate date = LocalDate.parse((String) entry.get("date"));
            String category = (String) entry.get("category");
            returnList.add(new Entry(body, date, title,category));
        }
        return returnList;
    }
    //file save overwrite
    public static void overwriteFile(Journal journalToSave,String filename) throws IOException {
        File overwrite = new File(filename+".json");
        FileWriter file = new FileWriter(overwrite,false);
        file.write(toJSON(journalToSave).toString());
        file.close();
    }
    //Entry to text file
}
