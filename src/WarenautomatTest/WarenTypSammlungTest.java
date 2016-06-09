package WarenautomatTest;

import java.text.ParseException;

import warenautomat.*;

public class WarenTypSammlungTest 
{
  
  public static void main(String[] args) throws ParseException 
  {
  
    WarenTypSammlung sammlung = new WarenTypSammlung();
    
    sammlung.AddWarenTyp(new WarenTyp("Mars", 250));
    sammlung.AddWarenTyp(new WarenTyp("Snickers", 300));
    
    WarenTyp snickers = sammlung.SucheWarenTyp("Snickers");
    WarenTyp mars = sammlung.SucheWarenTyp("Mars");
    
    if (snickers != null)
    {
      System.out.println("Es wurde ein Snickers zum Preis von: " + snickers.Preis() + " gefunden");
      System.out.println("Es wurde ein Mars zum Preis von: " + mars.Preis() + " gefunden");
    }
    
  }

}
