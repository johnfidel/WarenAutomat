package WarenautomatTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.*;

public class AutomatTest 
{
  public static void main(String[] args) throws ParseException {

    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    {
      SystemSoftware.output(false);
      
      Automat automat = new Automat();
      
      automat.fuelleFach(1, "Mars", 2.50, df.parse("01.01.2017"));
      automat.drehen();
      
      automat.fuelleFach(3, "Snickers", 3.50, df.parse("02.02.2017"));
      
      // Die Drehtellerposition auf den Anfang setzten
      while (!(automat.gibAktuelleDrehtellerPosition() == 1))
      {
        automat.drehen();
      }
      
      // alle 16 fächer iterieren
      for (int fachNr = 0; fachNr < 16; fachNr++)
      {
        System.out.println("Fach #" + fachNr);
      
        for (int drehtellerNr = 0; drehtellerNr < 7; drehtellerNr++)
        {
          Fach aktuellesFach = automat.gibFachVorDerTuer(drehtellerNr);
         
          Ware aktuelleWare = aktuellesFach.GetWare();
         
          if (aktuelleWare != null)
          {
            System.out.println("Drehteller #" + drehtellerNr + " " + aktuelleWare.Name() + " " +
                                                                    aktuelleWare.Preis() + " " + 
                                                                    aktuelleWare.AblaufDatum());
          }
          else
          {
            System.out.println("Drehteller #" + drehtellerNr + " ist leer.");            
          }
        }
        
        automat.drehen();
      }
    }
  
  }

}
