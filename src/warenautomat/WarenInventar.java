package warenautomat;

import java.util.*;

/**
 * Diese Klasse kümmert sich um die Inventarisierung der Ware
 * 
 * @author rappic
 *
 */
public class WarenInventar {

  /**
   * Hilfsklasse für die Inventarisierung
   * 
   * @author rappic
   *
   */
  public class WarenInfo {
    public int m_nAnzahl;
    public WarenTyp m_nWarenTyp;
  }

  /**
   * Liste aller Bestellungen
   */
  private ArrayList<BestellungsKonfig> m_oBestellungsKonfigListe;

  /**
   * Liste mit der Anzahl Warentypen
   */
  private ArrayList<WarenInfo> m_oWarenListe;

  /**
   * löst eine Bestellung aus, falls nötig
   * 
   * @param i_oWare
   */
  public void pruefeUndMachBestellungFallsNoetig(String i_oWarenName) {
    WarenInfo info = null;
    BestellungsKonfig konfig = null;

    // zuerst die Info der Ware im Inventar suchen
    for (WarenInfo i : m_oWarenListe) {
      if (i.m_nWarenTyp.Name().equals(i_oWarenName)) {
        info = i;
        break;
      }
    }

    // danach eine entsprechende Konfig suchen
    for (BestellungsKonfig k : m_oBestellungsKonfigListe) {
      if (k.Name().equals(i_oWarenName)) {
        konfig = k;
        break;
      }
    }

    // prüfen ob die Untergrenze erreicht ist
    if ((info != null) && (konfig != null)) {
      if (info.m_nAnzahl <= konfig.Grenze()) {
        // bestellung auslösen
        SystemSoftware.bestellen(konfig.Name(), konfig.BestellAnzahl());
      }
    }
  }

  /**
   * Fügt eine Menge des entsprechenden Warentyp hinzu
   * 
   * @param i_oTyp
   */
  public void Hinzufuegen(WarenTyp i_oTyp) {
    boolean found = false;

    for (WarenInfo info : m_oWarenListe) {
      if (info.m_nWarenTyp.Name().equals(i_oTyp.Name())) {
        found = true;
        info.m_nAnzahl++;
      }
    }

    if (!found) {
      WarenInfo newInfo = new WarenInfo();
      newInfo.m_nAnzahl = 1;
      newInfo.m_nWarenTyp = i_oTyp;
      m_oWarenListe.add(newInfo);
    }
  }

  /**
   * Entfernt eine Menge des entsprechenden Warentypes
   * 
   * @param i_oTyp
   */
  public void Entfernen(WarenTyp i_oTyp) {

    for (WarenInfo info : m_oWarenListe) {
      if (info.m_nWarenTyp.Name().equals(i_oTyp.Name())) {
        info.m_nAnzahl--;
      }
    }
  }

  /**
   * ctor
   */
  public WarenInventar()
  {
    m_oWarenListe = new ArrayList<WarenInfo>();
    m_oBestellungsKonfigListe = new ArrayList<BestellungsKonfig>();
  }

  /**
   * Getter für die Bestellungsliste
   * @return
   */
  public void FuegeBestellungsKonfigHinzu(BestellungsKonfig i_oKonfig)
  {
    boolean found = false;
    
    // suchen ob es in der Liste bereits eine Bestellung für die ware gibt --> dann diese überschreiben
    for (BestellungsKonfig konfig: m_oBestellungsKonfigListe)
    {
      if (konfig.Name().equals(i_oKonfig.Name()))
      {
        found = true;
        konfig.Grenze(i_oKonfig.Grenze());
        konfig.BestellAnzahl(i_oKonfig.BestellAnzahl());
      }
    }
    
    if (!found)
    {
      m_oBestellungsKonfigListe.add(i_oKonfig);  
    }
    
  }
}
