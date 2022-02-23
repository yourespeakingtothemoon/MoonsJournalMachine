package edu.neumont.oop.controller;

import edu.neumont.oop.DAL.JournalDAL;
import edu.neumont.oop.model.Entry;
import edu.neumont.oop.model.Journal;
import edu.neumont.oop.view.UI;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GeneralCont {
    //initalize view class
    private UI ux = new UI();
    //launch method
    public void launch() throws IOException, ParseException {
        for (boolean menuExit = false; !menuExit; ) {
            switch (ux.optSel("Hello! Welcome to the moon's java journal maker!\n" +
                    "Would you like to: \n" +
                    "a) create a new journal?\n" +
                    "b) load a journal?\n" +
                    "q) exit?")) {
                case 'a':
                    newJournal();
                    break;
                case 'b':
                    Journal current = JournalDAL.readFile(ux.strEnt("whats the file name of your journal? (Exclude the .json)"));
                    if (current == null) {
                        ux.invalidMsg("filename");
                    } else {
                        inJournal(current);
                       /* if (ux.strEnt("whats the password?").equals(current.getPswd())) {
                            inJournal(current);
                        } else {
                            ux.invalidMsg("password");
                        }*/
                    }
                    break;
                case 'q':
                    ux.byeBye();
                    menuExit = true;
                    break;
                default:
                    ux.invalidMsg("selection");
                    break;
            }
        }
    }
    //entry and journal creation
    public Entry newEntry(String title) throws IOException {

        return new Entry(ux.strEnt("Write the entry!"), dateSel(), title, ux.strEnt("give your entry a category!"));

    }
    public void newJournal() throws IOException {
        String title = ux.strEnt("First lets give your journal a name, and make your first entry.");
        Entry first = new Entry(ux.strEnt("Write your first entry!"), dateSel(), ux.strEnt("please enter a title for your first entry!"), ux.strEnt("give your entry a category!"));
        Journal journalCurrent = new Journal(first, title);
        journalCurrent.setFilename(ux.strEnt("give this journal a filename!"));
        JournalDAL.writeFile(journalCurrent, journalCurrent.getFilename());
    }
    //set entry Title loop
    public String setEntryTitle(Journal journal) throws IOException {
        String title = null;
        Boolean unique = false;
        while (!unique) {
            title = ux.strEnt("Please Input a unique title for your entry!");
            for (int pos = 0; pos < journal.getEntries().size(); pos++) {
                if (title.equals(journal.getEntries().get(pos).getTitle())) {
                    ux.invalidMsg("entry title");
                    pos = journal.getEntries().size();
                } else {
                    unique = true;
                }
            }
        }
        return title;
    }
    //date collection method
    public LocalDate dateSel() throws IOException {
        boolean validInput = false;
        LocalDate date = LocalDate.now();
        while (!validInput) {

            switch (ux.optSel("Would you like to\n" +
                    "a) Use the local date now?\n" +
                    "b) Enter a custom date?")) {
                case 'a':
                    date = LocalDate.now();
                    validInput = true;
                    break;
                case 'b':
                    date = ux.dateGet();
                    validInput = true;
                    break;
                default:
                    ux.invalidMsg("valid option");
                    break;
            }
        }
        return date;
    }
    //Journal menus
    public void inJournal(Journal journal) throws IOException {
        for (boolean outOf = false; !outOf; ) {

            switch (ux.optSel("Welcome to " + journal.getVolumeName() + "!\n" +
                    "Would you like to\n" +
                    "a) add a new entry?\n" +
                    "b) look at a list of entries?\n" +
                    "c) look at a list of categories?\n" +
                    "d) open an entry by title?\n" +
                    "e) search for entries (by date, keyword, or category)\n" +
                    "q) exit journal?")) {
                case 'a':
                    String title = setEntryTitle(journal);
                    journal.addEntry(newEntry(title));
                    break;
                case 'b':
                    ux.showThing(journal.seeEntryList());
                    break;
                case 'c':
                    ux.showThing(journal.seeCatList());
                    break;
                case 'd':
                    openEntry(journal.getEntries());
                    break;
                case 'e':
                    inSearch(journal);
                    break;
                case 'q':
                    outOf = true;
                    break;

            }
            JournalDAL.overwriteFile(journal, journal.getFilename());
        }
    }
    public void inSearch(Journal journal) throws IOException {

        switch (ux.optSel(
                "How would you like to search for an entry?\n" +
                        "a) by date?\n" +
                        "b) by keywords in title and entry body?\n" +
                        "c) by category?")) {
            case 'a':
                ux.dateSearchMenu();
                ux.showThing(dateSearch(journal.getEntries(), ux.dateGet()));
                break;
            case 'b':
                ux.showThing(keywordSearch(journal.getEntries(), ux.strEnt("Input a term or phrase to find!").toLowerCase()));
                break;
            case 'c':
                ux.showThing(catSearch(journal.getEntries(), ux.strEnt("Input a category to find!").toUpperCase()));
                break;

        }
    }
        //open entry
    public void openEntry(ArrayList<Entry> entries) throws IOException {
        ux.search();
        String search = ux.strEnt("enter the title of the entry you want to see!");
        Entry get = null;
        for (int pos = 0; pos < entries.size(); pos++) {
            if (search.equals(entries.get(pos).getTitle())) {
                get = entries.get(pos);
            }
        }
        if (get == null) {
            System.out.println("This entry could not be found!");
        } else {
            ux.showThing(get.toString());
        }
    }
    //searches
    public String catSearch(ArrayList<Entry> entries, String category) {
        String catSearchView = category + "\n=======================\n";
        for (int pos = 0; pos < entries.size(); pos++) {
            if (entries.get(pos).getCategory().equals(category)) {
                catSearchView += entries.get(pos).getDate() + " || " + entries.get(pos).getTitle() + "\n";
            }
        }
        return catSearchView;
    }
    public String keywordSearch(ArrayList<Entry> entries, String term) {
        String termSearchView = "Results for " + term + "\n=======================\n";
        for (int pos = 0; pos < entries.size(); pos++) {
            if (entries.get(pos).getBody().toLowerCase().contains(term) || entries.get(pos).getTitle().toLowerCase().contains(term)) {
                termSearchView += entries.get(pos).getDate() + " || " + entries.get(pos).getTitle() + "\n";
            }
        }
        return termSearchView;
    }
    public String dateSearch(ArrayList<Entry> entries, LocalDate date) {
        String dateSearchView = "Entries made on " + date + "\n=======================\n";
        for (int pos = 0; pos < entries.size(); pos++) {
            if (entries.get(pos).getDate() == date) {
                dateSearchView += entries.get(pos).getTitle() + "\n";
            }
        }
        return dateSearchView;
    }

}

