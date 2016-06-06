package WarenautomatTest;

import java.text.ParseException;

import warenautomat.Kasse;

public class KasseTest 
{
  
  public static void main(String[] args) throws ParseException 
  {
    Kasse kasse = new Kasse();
   
    // fülle 10 x .-50 = 5.-
    if (kasse.fuelleKasse(0.50, 10) == 10)
    {
      System.out.println("Habe 10 x .-50 eingefüllt!");
    }
    System.out.println("Kassenwert = " + kasse.gibKassenWert());
    
    // fülle 5 x 2.-
    if (kasse.fuelleKasse(2.00, 5) == 5)
    {
      System.out.println("Habe 5 x 2.- eingefüllt!");
    }
    System.out.println("Kassenwert = " + kasse.gibKassenWert());
      
    // überlauf prüfen
    kasse = new Kasse();
    int nDiff = 0;
    
    nDiff = kasse.fuelleKasse(0.10, 100);
    if (nDiff == 100)
    {
      System.out.println("OK konnte 100 Münzen hinzufüen");
    }
    nDiff = kasse.fuelleKasse(0.10, 20);
    if (nDiff == -20)
    {
      System.out.println("Versuche nochmals 20 einzufügen --> Darf nicht gehen (resultat -20) --> OK");
    }
    else
    {
      System.out.println("Falsche Differenz zurückgegeben: " + nDiff);
    }
    
    // Münzeinwurf testen
    kasse = new Kasse();
    kasse.einnehmen(0.50);
    kasse.einnehmen(1.00);
    kasse.einnehmen(0.20);
    kasse.einnehmen(0.10);
    
    System.out.println("Momentaner Betrag: " + kasse.EingeworfenerBetrag());
    kasse.gibWechselGeld();
    
    
  }

}
