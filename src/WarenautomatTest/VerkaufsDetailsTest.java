package WarenautomatTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.Transaktion;
import warenautomat.VerkaufsDetails;
import warenautomat.Ware;
import warenautomat.WarenTyp;

public class VerkaufsDetailsTest 
{
  public static void main(String[] args) throws ParseException 
  {
    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    
    WarenTyp Mars = new WarenTyp("Mars", 250);
    WarenTyp Snickers = new WarenTyp("Snickers", 300);
    
    Ware einMars = new Ware(Mars, df.parse("08.10.2016"));
    Ware ein2esMars = new Ware(Mars, df.parse("09.10.2016"));
    Ware ein3esMars = new Ware(Mars, df.parse("10.10.2016"));
    Ware einSnickers = new Ware(Snickers, df.parse("2.11.2016"));
    
    Transaktion trans1 = new Transaktion(einMars, df.parse("06.06.2016"));
    Transaktion trans2 = new Transaktion(ein2esMars, df.parse("07.06.2016"));
    Transaktion trans3 = new Transaktion(ein3esMars, df.parse("08.06.2016"));
    Transaktion trans4 = new Transaktion(einSnickers, df.parse("09.06.2016"));
    
    VerkaufsDetails details = new VerkaufsDetails();
    details.AddTransaktion(trans1);
    details.AddTransaktion(trans2);
    details.AddTransaktion(trans3);
    details.AddTransaktion(trans4);
    
    // transaktionsinformationena ausgeben
    System.out.println("Verkaufsstatistik");
    System.out.println(" Gesammtbetrag: " + details.GesammtVerkaufswert());
    System.out.println(" Anzahl verkaufte Mars zwischen dem 7.6 bis heute: " + details.gibAnzahlVerkaufte("Mars", df.parse("07.06.2016")));
    System.out.println(" Anzahl verkaufte Snickers zwischen dem 7.6 bis heute: " + details.gibAnzahlVerkaufte("Snickers", df.parse("07.06.2016")));
  
  }

}
