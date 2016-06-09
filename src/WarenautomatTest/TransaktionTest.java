package WarenautomatTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.*;

public class TransaktionTest 
{

  public static void main(String[] args) throws ParseException 
  {
    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    
    WarenTyp Mars = new WarenTyp("Mars", 250);
    Ware einMars = new Ware(Mars, df.parse("08.10.2016"));
    
    Transaktion trans = new Transaktion(einMars, df.parse("06.06.2016"));
    
    // transaktionsinformationena ausgeben
    System.out.println("Ware: " + trans.getWare().Name() + " wurde am " + trans.getVerkaufsdatum() + " verkauft.");
    
  }  
}
