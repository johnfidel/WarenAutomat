package warenautomat;

import java.util.ArrayList;
import java.util.Date;

/**
 * In dieser Privaten KLasse werden alle getätigten
 * Verkäufe gespeichert
 * @author rappic
 *
 */
public class VerkaufsDetails
{
  /**
   * Hier wird die Summe der getätigten Verkäufe gespeichert
   */
  private int m_nGesammtVerkaufswert;
  
  /**
   * Hier werden alle Transaktionsdetails der getätigten Verkäufe gespeichert
   */
  private ArrayList<Transaktion> m_oVerkaufteWare;

  /**
   * Getter für das Attribut m_nGesammtVerkaufswert
   * @return
   */
  public int GesammtVerkaufswert()
  {
    return m_nGesammtVerkaufswert;
  }
  
  /**
   * Getter für die Liste der Transaktionen
   * @return
   */
  public ArrayList<Transaktion> VerkaufteWare()
  {
    return m_oVerkaufteWare;
  }
  
  /**
   * Fügt eine Transaktion der Liste hinzu
   * @param i_oTransaktion
   */
  public void AddTransaktion(Transaktion i_oTransaktion)
  {
    m_oVerkaufteWare.add(i_oTransaktion);
    m_nGesammtVerkaufswert += i_oTransaktion.getWare().Preis();
  }
    
  /**
   * Standard ctor
   */
  public VerkaufsDetails()
  {
    m_oVerkaufteWare = new ArrayList<Transaktion>();
  }
  
  /**
   * Diese Funktion zählt die anzahl verkauften Waren
   * im Filterdatumbereich
   * @param pName warenname
   * @param pDatum anfangsdatum bis heute
   * @return
   */
  public int gibAnzahlVerkaufte(String pName, Date pStartDatum) 
  {
    int nZaehler = 0;
    
    for (Transaktion trans: VerkaufteWare())
    {
      // namen der Ware überprüfen
      if (trans.getWare().Name().equals(pName))
      {
        // prüfen ob das Datum innerhalb des gewünschten Bereiches liegt
        if (trans.getVerkaufsdatum().after(pStartDatum))
        {
          nZaehler++;
        }
      }
    }
    return nZaehler;
    
  }
}
