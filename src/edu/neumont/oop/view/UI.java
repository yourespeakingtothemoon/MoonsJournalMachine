package edu.neumont.oop.view;

import edu.neumont.oop.model.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class UI {
    private BufferedReader bread = new BufferedReader(new InputStreamReader(System.in));
    //Output Methods
    public void search(){System.out.println("Please enter the date of the entry you're trying to find!");}
    public void dateSearchMenu(){System.out.println("Enter a date to search!");}
    public void invalidMsg(String parameter) {
        System.out.println("Please input a valid " + parameter + "!");
    }
    public void byeBye(){System.out.println("Bye-Bye :)");}
    public void showThing(String sout){System.out.println(sout);}
    //Input Methods
    public char optSel(String prompt) throws IOException {
        boolean valid=false;
        char sel = 0;
        while(!valid){
            System.out.println(prompt);
        String select = bread.readLine();
        if(select.isEmpty()){
            invalidMsg("option");
        }else {
            sel = select.toLowerCase().toCharArray()[0];
            valid=true;
        }
    }
    return sel;
    }
    public String strEnt(String prompt) throws IOException {
        boolean valid=false;
        String str = null;
        while(!valid){
        System.out.println(prompt);
       str = bread.readLine();
        if(str.isEmpty()){
            invalidMsg("string");
            
        }else{
            valid=true;
        }
        
        }
        return str;
    }
    public int intEnt(String prompt) {
        System.out.println(prompt);
        int input = -1;
        try {
            input = Integer.parseInt(bread.readLine());
        } catch (Exception ioe) {
            System.out.println("Not a number!");
        }
        return input;
    }
        //date get menu
    public LocalDate dateGet(){
        int year = intEnt("Enter a four digit year!");
        while (year > 9999 || year<0) {
            invalidMsg("year");
            year = intEnt("Enter a four digit year!");
        }
        int month = intEnt("Enter a month in two digits!");
        while (month > 12 || month<0) {
            invalidMsg("month");
            month = intEnt("Enter a month in two digits!");
        }
        int day = intEnt("Enter a day in two digits!");
        while (day > 31 || day<0) {
            invalidMsg("day");
            day = intEnt("Enter a day in two digits!");
        }
        return LocalDate.of(year,month,day);
    }

}
