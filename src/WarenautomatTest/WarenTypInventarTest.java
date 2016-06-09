package WarenautomatTest;

import java.util.*;
import java.text.ParseException;

import warenautomat.*;

public class WarenTypInventarTest 
{

  public static void main(String[] args) throws ParseException 
  {
    WarenInventar inventar = new WarenInventar();
    
    inventar.Hinzufuegen(new WarenTyp("Mars", 200));
    inventar.Hinzufuegen(new WarenTyp("Mars", 200));
    inventar.Hinzufuegen(new WarenTyp("Mars", 200));
    inventar.Hinzufuegen(new WarenTyp("Mars", 200));
    
    inventar.Hinzufuegen(new WarenTyp("Snickers", 200));
    inventar.Hinzufuegen(new WarenTyp("Snickers", 200));
    inventar.Hinzufuegen(new WarenTyp("Snickers", 200));
    
    inventar.FuegeBestellungsKonfigHinzu(new BestellungsKonfig("Mars", 3 , 10));
    
    inventar.pruefeUndMachBestellungFallsNoetig("Mars");
    
    inventar.FuegeBestellungsKonfigHinzu(new BestellungsKonfig("Mars", 4 , 10));
    
    inventar.pruefeUndMachBestellungFallsNoetig("Mars");
    
    
  }
}
