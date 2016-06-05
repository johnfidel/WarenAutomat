package WarenautomatTest;

import java.text.ParseException;

import warenautomat.MuenzSaeule;

public class MuenzsaeuleTest 
{
  public static void main(String[] args) throws ParseException 
  {
 
    MuenzSaeule saeule = new MuenzSaeule(50);
    
    System.out.println("neue Säule erstellt für -.50");
    System.out.println("Gesammtwert nach der Initialisierung: " + saeule.SaeulenWert());
    
    saeule.addMuenze();
    System.out.println("1 Münze hinzu --> Wert : " + saeule.SaeulenWert());
    
    saeule.addMuenze();
    saeule.addMuenze();
    saeule.addMuenze();
    saeule.addMuenze();
    System.out.println("4 Münzen hinzu (gesammt 5) --> Wert : " + saeule.SaeulenWert());
    
    saeule.entferneMuenze();
    System.out.println("1 Münzen weg (gesammt 4) --> Wert : " + saeule.SaeulenWert());
    
    saeule.entferneMuenze();
    saeule.entferneMuenze();
    saeule.entferneMuenze();
    saeule.entferneMuenze();
    System.out.println("4 Münzen weg (leer) --> Wert : " + saeule.SaeulenWert());
    
    // überlauf testen
    int anzahlHinzu = 0;
    while (saeule.addMuenze())
    {
      anzahlHinzu++;
    }
    System.out.println("Es konnten " + anzahlHinzu + " Münzen bis die Säule voll ist hinzugefügt werden.");
    System.out.println("Säulenwert : " + saeule.SaeulenWert());
    
    // leeren testen
    int anzahlWeg = 0;
    while (saeule.entferneMuenze())
    {
      anzahlWeg++;
    }
    System.out.println("Es konnten " + anzahlWeg + " Münzen bis die Säule leer ist entfernt werden.");
    System.out.println("Säulenwert : " + saeule.SaeulenWert());
    
    
  }

}
