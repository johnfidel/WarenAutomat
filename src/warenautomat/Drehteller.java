
package warenautomat;

import warenautomat.Fach;

/**
 * @author rappic
 * Diese Klasse stellt einen Drehteller mit 16 Fächer dar.
 *
 */
public class Drehteller
{
  
  private static final int ANZAHL_FAECHER = 16;
  
  /**
   * In dieser Liste werden alle Fächer gespeichert.
   */
  private Fach[] m_oFaecher;
  
  /**
   * In dieser Variable wird die aktuelle Position der Ware gespeichert.
   */
  private int m_nActFach;
  
  /**
   * Standardkonstruktor
   */
  public Drehteller()
  {
    m_oFaecher = new Fach[ANZAHL_FAECHER];
    m_nActFach = 0;
    
    for (int i = 0; i < m_oFaecher.length; i++)
    {
      m_oFaecher[i] = new Fach();
    }
  }
  
  /**
   * Mit dieser Funktion wird der Drehteller gedreht.
   */
  public void Drehen()
  {
    m_nActFach++;
    if (m_nActFach >= ANZAHL_FAECHER)
    {
      m_nActFach = 0;
    }
  }
  
  /**
   * Mit dieser Funktion wird ein Produkt in das aktuelle Fach gefüllt, falls
   * dieses Fach nicht bereits belegt ist.
   * @param i_oProdukt
   * @return
   */
  public boolean FuelleFach(Ware i_oProdukt)
  {
    int nFachNr = m_nActFach + 1;
    if (nFachNr >= ANZAHL_FAECHER) { nFachNr = 0; }
    
    // wenn das Fach noch leer ist, darf die Ware eingelegt werden.
    if (m_oFaecher[nFachNr].IsEmpty())
    {
      m_oFaecher[nFachNr].SetWare(i_oProdukt);
      
      return true;
    }
    
    return false;
  }
  
  /**
   * Liefert ein spezifisches Fach
   * @param i_nFachNr
   * @return
   */
  public Fach HoleSpezifischesFach(int i_nFachNr)
  {
    return m_oFaecher[i_nFachNr];
  }
  
  /**
   * Diese Funktion liefert die Ware, welche vor der Türe steht
   * @return
   */
  public Fach HoleFachVorDerTuere()
  {
    int nFachNr = m_nActFach + 1;
    if (nFachNr >= ANZAHL_FAECHER) { nFachNr = 0; }
        
    return m_oFaecher[nFachNr];
  }
  
  /**
   * Dieser Getter zeigt welches Fach vor der Tuere steht
   * @return
   */
  public int AktuellePosition()
  {
    return m_nActFach;
  }
  
  /**
   * Liefert die Anzahl Fächer des Drehtellers
   * @return
   */
  public int AnzahlFaecher()
  {
    return ANZAHL_FAECHER;
  }
}
