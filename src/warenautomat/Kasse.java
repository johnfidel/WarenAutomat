package warenautomat;


import warenautomat.SystemSoftware;

/**
 * Die Kasse verwaltet das eingenommene Geld sowie das Wechselgeld. <br>
 * Die Kasse hat fünf Münz-Säulen für: <br>
 * - 10 Rappen <br>
 * - 20 Rappen <br>
 * - 50 Rappen <br>
 * - 1 Franken <br>
 * - 2 Franken <br>
 */
public class Kasse 
{
  private static final int ANZAHL_SAEULEN = 5;
  
  private static final int POSITION_MUENZWERT_10RAPPEN = 0;
  private static final int POSITION_MUENZWERT_20RAPPEN = 1;
  private static final int POSITION_MUENZWERT_50RAPPEN = 2;
  private static final int POSITION_MUENZWERT_100RAPPEN = 3;
  private static final int POSITION_MUENZWERT_200RAPPEN = 4;
  
  private static final int MUENZWERT_10RAPPEN = 10;
  private static final int MUENZWERT_20RAPPEN = 20;
  private static final int MUENZWERT_50RAPPEN = 50;
  private static final int MUENZWERT_100RAPPEN = 100;
  private static final int MUENZWERT_200RAPPEN = 200;
 
  /**
   * Diese Variable speichert den Verfügabren eingeworfenen Betrag
   */
  private int m_nEingeworfenerBetrag;
  
  /**
   * Diese Funktion liefert den Platz welcher in einer Säule verlbeibt.
   * @param i_nMuenzSaulenIdx: der Index der Säule
   *        0: -.10
   *        1: -.20
   *        2: -.50
   *        3: -.100
   *        4: -.200
   * @return Der verbleibende Platz in der Münzsäule
   */
  private int pruefePlatzInSaeule(int i_nMuenzSaulenIdx)
  {
    return (m_oMuenzSaeulen[i_nMuenzSaulenIdx].Kapazitaet() - m_oMuenzSaeulen[i_nMuenzSaulenIdx].Fuellstand());
  }
  
  /**
   * Diese Funktion liefert den Münzsäulenindex anhand des Münzbetrages
   * @param i_nBetrag
   * @return
   */
  private int gibMuenzSaeulenIndexAusBetrag(int i_nBetrag)
  {
    switch (i_nBetrag)
    {
      case MUENZWERT_10RAPPEN: return POSITION_MUENZWERT_10RAPPEN;
      case MUENZWERT_20RAPPEN: return POSITION_MUENZWERT_20RAPPEN;
      case MUENZWERT_50RAPPEN: return POSITION_MUENZWERT_50RAPPEN;
      case MUENZWERT_100RAPPEN: return POSITION_MUENZWERT_100RAPPEN;
      case MUENZWERT_200RAPPEN: return POSITION_MUENZWERT_200RAPPEN;
    
      default:
      {
        return -1;
      }
    }
  }
  
  /**
   * Dies ist ein Array welches alle Münzsäulen enthält
   */
  private MuenzSaeule[] m_oMuenzSaeulen;
  
  /**
   * Standard-Konstruktor. <br>
   * Führt die nötigen Initialisierungen durch.
   */
  public Kasse() 
  {
    
    m_oMuenzSaeulen = new MuenzSaeule[ANZAHL_SAEULEN];
    
    // eine Säule für jeden Münzwert erstellen
    m_oMuenzSaeulen[POSITION_MUENZWERT_10RAPPEN] = new MuenzSaeule(MUENZWERT_10RAPPEN);
    m_oMuenzSaeulen[POSITION_MUENZWERT_20RAPPEN] = new MuenzSaeule(MUENZWERT_20RAPPEN);
    m_oMuenzSaeulen[POSITION_MUENZWERT_50RAPPEN] = new MuenzSaeule(MUENZWERT_50RAPPEN);
    m_oMuenzSaeulen[POSITION_MUENZWERT_100RAPPEN] = new MuenzSaeule(MUENZWERT_100RAPPEN);
    m_oMuenzSaeulen[POSITION_MUENZWERT_200RAPPEN] = new MuenzSaeule(MUENZWERT_200RAPPEN);
    
  }

  /**
   * Diese Methode wird aufgerufen nachdem das Personal beim Geldauffüllen die
   * Münzart und die Anzahl der Münzen über die Tastatur eingegeben hat (siehe
   * Use-Case "Kasse auffüllen").
   * 
   * @param pMuenzenBetrag Betrag der Münzart in Franken.
   * @param pAnzahl Anzahl der neu eingelegten Münzen.
   * @return Wenn es genügend Platz in der Münzsäule hat: die Anzahl Münzen
   *         welche eingelegt werden (d.h. pAnzahl). <br>
   *         Wenn es nicht genügend Platz hat: die Anzahl Münzen welche nicht
   *         Platz haben werden, als negative Zahl (z.B. -20). <br>
   *         Wenn ein nicht unterstützter Münzbetrag übergeben wurde: -200
   */
  public int fuelleKasse(double pMuenzenBetrag, int pAnzahl) 
  {
    int nMuenzBetrag = (int)(Math.round(pMuenzenBetrag * 100.0));
    int nPlatz;
    int nSelektierteSaeule;
    
    nSelektierteSaeule = gibMuenzSaeulenIndexAusBetrag(nMuenzBetrag);  
    if (nSelektierteSaeule == -1)
    {
      return -200;
    }
    
    // prüfen ob noch genügend Platz in der Säule vorhanden ist
    nPlatz = pruefePlatzInSaeule(nSelektierteSaeule);
    if (nPlatz >= pAnzahl)
    {
      // wenn genügend Platz vorhanden die gewünschte Anzahl Münzen hinzufügen.
      for (int i = 0; i < pAnzahl; i++)
      {
        m_oMuenzSaeulen[nSelektierteSaeule].addMuenze();
      }
      return pAnzahl;
    }
    else
    {
      // wenn nicht genügend Platz vorhanden ist --> die Differenz angeben
      return (nPlatz - pAnzahl);
    }
  }

  /**
   * Diese Methode wird aufgerufen nachdem das Personal beim Geldauffüllen den
   * Knopf "Bestätigen" gedrückt hat. (siehe Use-Case "Kasse auffüllen"). <br>
   * Verbucht die Münzen gemäss dem vorangegangenen Aufruf der Methode <code> fuelleKasse() </code>.
   */
  public void fuelleKasseBestaetigung() {
    
    // TODO
    
  }

  /**
   * Diese Methode wird aufgerufen wenn ein Kunde eine Münze eingeworfen hat. <br>
   * Führt den eingenommenen Betrag entsprechend nach. <br>
   * Stellt den nach dem Einwerfen vorhandenen Betrag im Kassen-Display dar. <br>
   * Eingenommenes Geld steht sofort als Wechselgeld zur Verfügung. <br>
   * Die Münzen werden von der Hardware-Kasse auf Falschgeld, Fremdwährung und
   * nicht unterstützte Münzarten geprüft, d.h. diese Methode wird nur
   * aufgerufen wenn ein Münzeinwurf soweit erfolgreich war. <br>
   * Ist die Münzsäule voll (d.h. 100 Münzen waren vor dem Einwurf bereits darin
   * enthalten), so wird mittels
   * <code> SystemSoftware.auswerfenWechselGeld() </code> unmittelbar ein
   * entsprechender Münz-Auswurf ausgeführt. <br>
   * Hinweis: eine Hardware-Münzsäule hat jeweils effektiv Platz für 101 Münzen.
   * 
   * @param pMuenzenBetrag Der Betrag der neu eingeworfenen Münze in Franken.
   * @return <code> true </code>, wenn er Einwurf erfolgreich war. <br>
   *         <code> false </code>, wenn Münzsäule bereits voll war.
   */
  public boolean einnehmen(double pMuenzenBetrag) 
  {
    
    int nBetrag = (int)(Math.round(pMuenzenBetrag * 100.0));
    int nSaeulenIdx;
    
    // münzsäule eruieren
    nSaeulenIdx = gibMuenzSaeulenIndexAusBetrag(nBetrag);
    
    // hat es platz in der Kasse
    if (pruefePlatzInSaeule(nSaeulenIdx) > 0)
    {
      // münze in die entsprechende Münzäule einlegen
      m_oMuenzSaeulen[nSaeulenIdx].addMuenze();     
      m_nEingeworfenerBetrag += nBetrag;
      SystemSoftware.zeigeBetragAn(nBetrag / 100.0);
      
      return true;
    }
    else
    {
      // kein Platz mehr in der Münzsäule --> münze wieder auswerfen
      SystemSoftware.auswerfenWechselGeld(pMuenzenBetrag);
      
      return false;
    }    
  }

  /**
   * Liefert den aktuell eingeworfenen Betrag
   * @return
   */
  public int EingeworfenerBetrag()
  {
    return m_nEingeworfenerBetrag;
  }
  
  /**
   * Bewirkt den Auswurf des Restbetrages.
   */
  public void gibWechselGeld() 
  {
    boolean zuWenigWechselGeld = false;
    int n10er = 0;
    int n20er = 0;
    int n50er = 0;
    int n100er = 0;
    int n200er = 0;
    int nFuellstand200er = m_oMuenzSaeulen[POSITION_MUENZWERT_200RAPPEN].Fuellstand();
    int nFuellstand100er = m_oMuenzSaeulen[POSITION_MUENZWERT_100RAPPEN].Fuellstand();
    int nFuellstand50er = m_oMuenzSaeulen[POSITION_MUENZWERT_50RAPPEN].Fuellstand();
    int nFuellstand20er = m_oMuenzSaeulen[POSITION_MUENZWERT_20RAPPEN].Fuellstand();
    int nFuellstand10er = m_oMuenzSaeulen[POSITION_MUENZWERT_10RAPPEN].Fuellstand();
    int nRestgeld = m_nEingeworfenerBetrag;
    
    // von der Grössten zur kleinsten Münze ausgeben
    while ((nRestgeld >= 200) && (nFuellstand200er > 0)) { n200er++; nFuellstand200er--; nRestgeld -= 200; }
    while ((nRestgeld >= 100) && (nFuellstand100er > 0)) { n100er++; nFuellstand100er--; nRestgeld -= 100; } 
    while ((nRestgeld >= 50) && (nFuellstand50er > 0)) { n50er++; nFuellstand50er--; nRestgeld -= 50; }
    while ((nRestgeld >= 20) && (nFuellstand20er > 0)) { n20er++; nFuellstand20er--; nRestgeld -= 20; }
    while ((nRestgeld >= 10) && (nFuellstand10er > 0)) { n10er++; nFuellstand10er--; nRestgeld -= 10; }
    
    if (nRestgeld > 0)
    {
      // betrag ungenügend!
      zuWenigWechselGeld = true;
      SystemSoftware.zeigeZuWenigWechselGeldAn();
    }  
    
    // 
    if (!zuWenigWechselGeld)
    {
      // geld effektiv ausgeben
      while (n200er > 0){ SystemSoftware.auswerfenWechselGeld(2.00); n200er++; }
      while (n200er > 0){ SystemSoftware.auswerfenWechselGeld(1.00); n100er++; }
      while (n200er > 0){ SystemSoftware.auswerfenWechselGeld(0.50); n50er++; }
      while (n200er > 0){ SystemSoftware.auswerfenWechselGeld(0.20); n20er++; }
      while (n200er > 0){ SystemSoftware.auswerfenWechselGeld(0.10); n10er++; }
      
      // gesammtbetrag abzählen
      m_nEingeworfenerBetrag = 0;
      // anzeige aktualisieren
      SystemSoftware.zeigeBetragAn(m_nEingeworfenerBetrag / 100.0);
    }    
  }

  /**
   * Gibt den Gesamtbetrag der bisher verkauften Waren zurück. <br>
   * Analyse: Abgeleitetes Attribut.
   * 
   * @return Gesamtbetrag der bisher verkauften Waren.
   */
  public double gibBetragVerkaufteWaren() {
    
    return 0.0; // TODO
    
  }
  
  /**
   * Liefert den aktuellen Kassenwert
   * @return
   */
  public int gibKassenWert()
  {
    int nWert = 0;
    
    for (int i = 0; i < m_oMuenzSaeulen.length; i++)
    {
      nWert += m_oMuenzSaeulen[i].SaeulenWert();
    }
    return nWert;
  }

}
