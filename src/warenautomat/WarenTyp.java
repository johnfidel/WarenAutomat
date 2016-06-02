
package warenautomat;

import java.util.Date;

/**
 * Diese Klasse stellt eine Ware dar
 */
public class WarenTyp 
{
  
  /**
   * Dies ist der Name der Ware
   */
  private String m_strName;
  
  /**
   * Der Preis der Ware wird in Rappen gespeichert
   */
  private int m_nPreis;
  
  /**
   * Dies ist der Konstruktor für den Warentyp
   * @param i_strName
   * @param i_nPreis
   */
  public WarenTyp(String i_strName, int i_nPreis)
  {
    m_strName = i_strName;
    m_nPreis = i_nPreis;
  }
  
  /**
   * Getter für den Namen
   * @return
   */
  public String Name()
  {
    return m_strName;
  }
  
  /**
   * Getter für den Preis
   * @return
   */
  public int Preis()
  {
    return m_nPreis;
  }
  
}
