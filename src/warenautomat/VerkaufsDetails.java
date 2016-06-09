package warenautomat;

import java.util.ArrayList;

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
}
