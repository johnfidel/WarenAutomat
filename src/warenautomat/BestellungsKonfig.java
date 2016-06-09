package warenautomat;

public class BestellungsKonfig 
{

  /**
   * Name der Konfigurierten Ware
   */
  private String m_strName;
  
  /**
   * Untergrenze der Bestellung
   */
  private int m_nGrenze;
  
  /**
   * Anzahl der Bestellung
   */
  private int m_nBestellAnzahl;
  
  /**
   * Getter für den Namen
   * @return
   */
  public String Name()
  {
    return m_strName;
  }
  
  /**
   * Getter für die Grenze
   * @return
   */
  public int Grenze()
  {
    return m_nGrenze;
  }
  
  /**
   * Setter für die Grenze
   * @param i_nGrenze
   */
  public void Grenze(int i_nGrenze)
  {
    m_nGrenze = i_nGrenze;
  }
  
  /**
   * Getter für die Bestellanzahl
   */
  public int BestellAnzahl()
  {
    return m_nBestellAnzahl;
  }
  
  /**
   * Setter für die Bestellanzahle
   * @param i_nBestellAnzahl
   */
  public void BestellAnzahl(int i_nBestellAnzahl)
  {
    m_nBestellAnzahl = i_nBestellAnzahl;
  }
  
  /**
   * ctor
   * @param i_strName
   * @param i_nGrenze
   * @param i_nAnzahl
   */
  public BestellungsKonfig(String i_strName, int i_nGrenze, int i_nAnzahl)
  {
    m_nBestellAnzahl = i_nAnzahl;
    m_nGrenze = i_nGrenze;
    m_strName = i_strName;
  }
}
