package WarenautomatTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.*;

public class DrehtellerTest 
{

  public static void main(String[] args) throws ParseException 
  {
    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    
    Drehteller drehteller = new Drehteller();
    
    // testen ob alle 16 Fächer des Drehtellers leer sind
    for (int i = 0; i < 16; i++)
    {
      if (drehteller.HoleFachVorDerTuere().IsEmpty())
      {
        System.out.println("Fach #" + i + " ist leer --> OK");
      }  
      else
      {
        System.out.println("Fach #" + i + " ist gefüllt, dürfte aber nicht sein!! --> NOK");
      }
      drehteller.Drehen();
    }    
    
    WarenTypSammlung warentypen = new WarenTypSammlung();
    warentypen.AddWarenTyp(new WarenTyp("Mars", 250));
    warentypen.AddWarenTyp(new WarenTyp("Snickers", 300));
    warentypen.AddWarenTyp(new WarenTyp("Knoppers", 150)); 
    
    drehteller.FuelleFach(new Ware(warentypen.SucheWarenTyp("Mars"), df.parse("01.10.2016")));
    drehteller.Drehen();
    drehteller.Drehen();
    
    drehteller.FuelleFach(new Ware(warentypen.SucheWarenTyp("Snickers"), df.parse("01.11.2016")));
    drehteller.Drehen();
    
    drehteller.FuelleFach(new Ware(warentypen.SucheWarenTyp("Knoppers"), df.parse("01.12.2016")));
    
    // test des Inhaltes
    // zuerst zurück an die Position 0 drehen
    while (drehteller.AktuellePosition() != 0)
    {
      drehteller.Drehen();
    }
    // ist an der ersten Position ein Mars?
    if (!drehteller.HoleFachVorDerTuere().GetWare().Name().equals("Mars"))
    {
      System.out.println("Falscher inhalt! Soll: Mars ist: " + drehteller.HoleFachVorDerTuere().GetWare().Name());
    }
    drehteller.Drehen();
    drehteller.Drehen();
    if (!drehteller.HoleFachVorDerTuere().GetWare().Name().equals("Snickers"))
    {
      System.out.println("Falscher inhalt! Soll: Snickers ist: " + drehteller.HoleFachVorDerTuere().GetWare().Name());
    }
    drehteller.Drehen();
    if (!drehteller.HoleFachVorDerTuere().GetWare().Name().equals("Knoppers"))
    {
      System.out.println("Falscher inhalt! Soll: Mars ist: " + drehteller.HoleFachVorDerTuere().GetWare().Name());
    }
    
    // zuerst zurück an die Position 0 drehen
    while (drehteller.AktuellePosition() != 0)
    {
      drehteller.Drehen();
    }
    
    // zuletzt alle Fächerinhalte anzeigen
    for (int i = 0; i < 16; i++)
    {
      if (drehteller.HoleFachVorDerTuere().IsEmpty())
      {
        System.out.println("Fach #" + i + " ist leer");
      }  
      else
      {
        System.out.println("Fach #" + i + " ist gefüllt mit einem " + drehteller.HoleFachVorDerTuere().GetWare().Name());
      }
      drehteller.Drehen();
    }    
  }  
}
