package warenautomat;

/**
 * Diese Klasse stellt eine Münzsäule der Kasse dar
 * @author rappic
 *
 */
public class MuenzSaeule 
{
  final static int MAXIMAL_ANZAHL_MUENZEN = 100;
  
  /**
   * Den Wert der Münzen in Rappen
   */
  private int m_nMuenzWert;
  
  /**
   * Hier wird der gesammtwert der Saeule nachgeführt
   */
  private int m_nSaeulenWert;
  
  /**
   * Der Füllstand der Münzsäule
   */
  private int m_nFuellstand;
  
  /**
   * Standardkonstruktor
   * @param i_nMuenzWert
   */
  public MuenzSaeule(int i_nMuenzWert) 
  {
    m_nMuenzWert = i_nMuenzWert;
  }
  
  /**
   * Fügt der Münzsäule eine Münze hinzu
   */
  public boolean addMuenze()
  {
    m_nFuellstand++;
    if (m_nFuellstand > MAXIMAL_ANZAHL_MUENZEN)
    {
      m_nFuellstand = MAXIMAL_ANZAHL_MUENZEN;
      return false;
    }
    m_nSaeulenWert += m_nMuenzWert;
    return true;
  }  
  
  /**
   * Entfernt eine Münze aus der Säule
   * @return
   */
  public boolean entferneMuenze()
  {
    if (m_nFuellstand > 0)
    {
      m_nFuellstand--;
      m_nSaeulenWert -= m_nMuenzWert;
      return true;
    }
    return false;
  }
  
  /**
   * Liefert den Füllstand der Münzsäule
   * @return
   */
  public int Fuellstand()
  {
    return m_nFuellstand;
  }
  
  /**
   * Liefert die Kapazität der MünzSäule
   * @return
   */
  public int Kapazitaet()
  {
    return MAXIMAL_ANZAHL_MUENZEN;
  }
  
  /**
   * Dieser Getter liefert den Säulenwert
   * @return
   */
  public int SaeulenWert()
  {
    return m_nSaeulenWert;
  }
}
