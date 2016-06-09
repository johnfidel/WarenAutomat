
package warenautomat;

import java.util.Date;

/*!
 * Diese Klasse stellt eine Ware dar
 */
public class Ware 
{
  /*!
   * Hier wird der Warentyp gespeichert
   */
  private WarenTyp m_oWarenTyp;
  
  /*!
   * Hier wird das Ablaufdatum der Ware gespeicher
   */
  private Date m_oAblaufDatum;
  
  public Ware(WarenTyp i_oWarenTyp, Date i_oAblaufDatum)
  {
    m_oWarenTyp = i_oWarenTyp;
    m_oAblaufDatum = i_oAblaufDatum;
  }
  
  /*!
   * Getter für den Namen
   */
  public String Name()
  {
    return m_oWarenTyp.Name();
  }
  
  /*!
   * Getter für den Preis
   */
  public int Preis()
  {
    return m_oWarenTyp.Preis();
  }
  
  /*!
   * Getter für das Ablaufdatum
   */
  public Date AblaufDatum()
  {
    return m_oAblaufDatum;
  }
}
