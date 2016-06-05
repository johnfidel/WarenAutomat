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
   * Dies ist ein Array welches alle Münzsäulen enthält
   */
  private MuenzSaeule[] m_oMuenzSaeulen;
  
  /**
   * Standard-Konstruktor. <br>
   * Führt die nötigen Initialisierungen durch.
   */
  public Kasse() 
  {
    
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
    
    switch (nMuenzBetrag)
    {
      case MUENZWERT_10RAPPEN: nSelektierteSaeule = POSITION_MUENZWERT_10RAPPEN; break;
      case MUENZWERT_20RAPPEN: nSelektierteSaeule = POSITION_MUENZWERT_20RAPPEN; break;
      case MUENZWERT_50RAPPEN: nSelektierteSaeule = POSITION_MUENZWERT_50RAPPEN; break;
      case MUENZWERT_100RAPPEN: nSelektierteSaeule = POSITION_MUENZWERT_100RAPPEN; break;
      case MUENZWERT_200RAPPEN: nSelektierteSaeule = POSITION_MUENZWERT_200RAPPEN; break;
      
      default:
      {
        // fehlercode wenn eine Münze nicht unterstützt wird!
        return -200;
      }
    }  
    
    // prüfen ob noch genügend Platz in der Säule vorhanden ist
    nPlatz = pruefePlatzInSaeule(nSelektierteSaeule);
    if (nPlatz > pAnzahl)
    {
      // wenn genügend Platz vorhanden die gewünschte Anzahl Münzen hinzufügen.
      for (int i = 0; i < pAnzahl; i++)
      {
        m_oMuenzSaeulen[nSelektierteSaeule].addMuenze();
      }
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
  public boolean einnehmen(double pMuenzenBetrag) {
    
    return false; // TODO
    
  }

  /**
   * Bewirkt den Auswurf des Restbetrages.
   */
  public void gibWechselGeld() {
    
    // TODO
    
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

}
