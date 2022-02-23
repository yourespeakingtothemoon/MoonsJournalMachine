package edu.neumont.oop;

import edu.neumont.oop.controller.GeneralCont;
import edu.neumont.oop.model.Entry;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDateTime;

public class L7Main {
    public static void main(String[] args) throws IOException, ParseException {
        GeneralCont app = new GeneralCont();
        app.launch();
    }
}
//test code library
   /* Entry entry = new Entry("Let’s say, for the sake of argument, hypothetically. I’m a quirked up white boy. \n" +
                "And if I was a quirked up white boy, then theoretically that would also mean that I’m busting it down (sexual style). \n" +
                "Now. We understand now that I’m a quirked up white boy, (who’s busting it down) ((sexual style.)), Does that in turn, \n" +
                "automatically mean that I’m now goated with the sauce? It’s true! They don’t want you to know that, but it’s true.\n",
                LocalDateTime.now(),"Sexual Style");
        entry.setCategory("goated");
        System.out.println(entry);*/
