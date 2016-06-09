package WarenautomatTest;

import warenautomat.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

public class FachTest 
{
  
  public static void main(String[] args) throws ParseException {

    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    { 
 
      Fach fach = new Fach();
      
      if (fach.IsEmpty())
      {
        System.out.println("Fach sollte leer sein und ist leer --> ok");
      }
      else
      {
        System.out.println("Fach sollte leer sein ist aber voll --> nok");
      }
  
      WarenTyp mars = new WarenTyp("Mars", 250);
      Ware einMars = new Ware(mars, df.parse("05.09.2016"));
      fach.SetWare(mars);
      
      System.out.println("Ware im Fach ist: " + fach.GetWare().Name() + " " +
                                                fach.GetWare().Preis() + " " +
                                                fach.GetWare().AblaufDatum());
    }   
  }

}
