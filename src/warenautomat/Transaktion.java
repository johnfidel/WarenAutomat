package warenautomat;

import java.util.*;
import warenautomat.*;

/**
 * Diese Klasse bildet eine Transaktion ab. Resp. den Verkauf einer
 * Ware. Wird für die Statistikfunktion verwendet.
 * @author rappic
 *
 */
public class Transaktion 
{
  /**
   * In diesem Feld wird das Verkaufsdatum gespeichert
   */
  private Date m_oVerkaufsdatum;
  
  /**
   * In diesem Feld wird die Verkaufte Ware festgehalten
   */ 
  private Ware m_oWare;
  
  /**
   * Konstruktor einer Transaktion
   * @param i_oWare
   * @param i_oVerkaufsDatum
   */
  public Transaktion(Ware i_oWare, Date i_oVerkaufsdatum)
  {
    m_oVerkaufsdatum = i_oVerkaufsdatum;
    m_oWare = i_oWare;
  }
  
  /**
   * Liefert die Ware
   * @return
   */
  public Ware getWare()
  {
    return m_oWare;
  }
  
  /**
   * Liefert das Verkaufsdatum
   * @return
   */
  public Date getVerkaufsdatum()
  {
    return m_oVerkaufsdatum;
  }
  
  
}
