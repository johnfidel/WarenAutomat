package warenautomat;

/*!
 * Diese Klasse stellt ein Fach dar, welches genau eine Ware oder keine
 * enthalten kann
 */
public class Fach
{
  /*!
   * In diesem Feld befindet sich die Ware
   */
  private Ware m_oWare;
  
  /*!
   * Standardkonstruktor
   */
  public Fach() {}
  
  public Fach(Ware i_oWare)
  {
    m_oWare = i_oWare;
  }
  
  /*!
   * Dies ist der Getter für die im Fach befindliche Ware
   */
  public Ware GetWare()
  {
    return m_oWare;
  }
  
  /*!
   * Dies ist der Setter für die Ware
   */
  public void SetWare(Ware i_oWare)
  {
    m_oWare = i_oWare;
  }
  
  /*!
   * Diese Methode liefert True wenn sich eine Ware im Fach befindet
   */
  public boolean IsEmpty()
  {
    if (m_oWare != null)
    {
      return false;
    }
    return true;
  }
}
