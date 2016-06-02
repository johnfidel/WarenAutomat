package WarenautomatTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.*;

public class WareTest 
{

  public static void main(String[] args) throws ParseException 
  {
    
    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    
    WarenTyp Mars = new WarenTyp("Mars", 250);
    Ware einMars = new Ware(Mars, df.parse("08.10.2016"));
    
    System.out.println("Erstellte Ware: " +
                        einMars.Name() + " " +
                        einMars.Preis() + " " +
                        einMars.AblaufDatum());
    
  }
}
