package warenautomat;

import warenautomat.WarenTyp;
import java.util.*;

public class WarenTypSammlung
{

  /**
   * In dieser Liste werden alle bekannten Warentypen gespeichert
   */
  private ArrayList<WarenTyp> m_oWarenTypListe;
  
  /**
   * Dies ist der Standardkonstruktor für die Warentypsammlung
   */
  public WarenTypSammlung()
  {
    m_oWarenTypListe = new ArrayList<WarenTyp>();
  }
  
  /**
   * Mit dieser Funktion wird ein Warentyp zu der Liste hinzugefügt
   * @param i_oTyp
   */
  public void AddWarenTyp(WarenTyp i_oTyp)
  {
    m_oWarenTypListe.add(i_oTyp);
  }
  
  /**
   * Mit dieser Suchfunktion kann die Liste der Warentypen durchsucht werden
   * @param name
   * @return
   */
  public WarenTyp SucheWarenTyp(String i_strName)
  {
    for (WarenTyp ware: m_oWarenTypListe)
    {
      if (ware.Name().equals(i_strName))
      {
        return ware;
      }
    }
    return null;
  }
  
}
