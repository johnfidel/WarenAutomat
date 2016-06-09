package warenautomat;

import java.util.Date;

/**
 * Der Automat besteht aus 7 Drehtellern welche wiederum je aus 16 Fächer
 * bestehen. <br>
 * Der erste Drehteller und das jeweils erste Fach haben jeweils die Nummer 1
 * (nicht 0!). <br>
 * Im Weitern hat der Automat eine Kasse. Diese wird vom Automaten instanziert.
 */
public class Automat {
  
  private static final int NR_DREHTELLER = 7;
  
  private static final int VERFALLSDATUMSANZEIGE_AUS = 0;
  private static final int VERFALLSDATUMSANZEIGE_GRUEN = 1;
  private static final int VERFALLSDATUMSANZEIGE_ROT = 2;
  
  
  private Drehteller[] mDrehteller;
  private Kasse mKasse;
  
  /**
   * Hier werden alle Warentypen gespeichert.
   */
  private WarenTypSammlung m_oAlleWarentypen;
  
  /**
   * In diesem Objekt wird die Inventarisierung der Objekte durchgeführt
   */
  private WarenInventar m_oInventar;
      
  /**
   * Mit dieser Funktion werden alle Anzeigen über alle
   * Drehteller aktalisiert
   */
  private void aktualisiereFachAnzeigen(int i_nDrehteller)
  {
    Ware actWare = mDrehteller[i_nDrehteller].HoleFachVorDerTuere().GetWare();
    
    if (actWare != null)
    {
      
      double dblPreis = (double)(actWare.Preis()) / 100.0;
      SystemSoftware.zeigeWarenPreisAn(i_nDrehteller + 1, dblPreis);
      SystemSoftware.zeigeWareInGui(i_nDrehteller + 1, actWare.Name(), actWare.AblaufDatum());
        
      // verfallsdatum prüfen und Anzeige aktualisieren
      if (actWare.AblaufDatum().after(SystemSoftware.gibAktuellesDatum()))
      {
        SystemSoftware.zeigeVerfallsDatum(i_nDrehteller + 1, VERFALLSDATUMSANZEIGE_GRUEN);
      }
      else
      {
        SystemSoftware.zeigeVerfallsDatum(i_nDrehteller + 1, VERFALLSDATUMSANZEIGE_ROT);
      }
    }
    else
    {
      // Verfallsdatumanzeige ausschalten und Preis auf 0 setzen
      SystemSoftware.zeigeWarenPreisAn(i_nDrehteller + 1, 0.0);
      SystemSoftware.zeigeVerfallsDatum(i_nDrehteller + 1, VERFALLSDATUMSANZEIGE_AUS);
      SystemSoftware.zeigeWareInGui(i_nDrehteller + 1, "", null);
    }
  }
  
  /**
   * Der Standard-Konstruktor. <br>
   * Führt die nötigen Initialisierungen durch (u.a. wird darin die Kasse
   * instanziert).
   */
  public Automat() {
    
    // Drehteller initialisieren
    mDrehteller = new Drehteller[NR_DREHTELLER];
    // alle Drehtellepositionen mit einem Drehteller initialisieren
    for (int i = 0; i < mDrehteller.length; i++)
    {
      mDrehteller[i] = new Drehteller();
    }
    
    // warentypen objekt ertsellen
    m_oAlleWarentypen = new WarenTypSammlung();
        
    mKasse = new Kasse();
    
    m_oInventar = new WarenInventar();
       
  }

  /**
   * Füllt ein Fach mit Ware. <br>
   * Wenn das Service-Personal den Automaten füllt, wird mit einem
   * Bar-Code-Leser zuerst die Ware gescannt. <br>
   * Daraufhin wird die Schiebe-Tür geöffnet. <br>
   * Das Service-Personal legt die neue Ware ins Fach und schliesst das Fach. <br>
   * Die Hardware resp. System-Software ruft die Methode
   * <code> Automat.fuelleFach() </code> auf.
   * 
   * @param pDrehtellerNr Der Drehteller bei welchem das Fach hinter der
   *          Schiebe-Türe gefüllt wird. <br>
   *          Nummerierung beginnt mit 1 (nicht 0)!
   * @param pWarenName Der Name der neuen Ware.
   * @param pPreis Der Preis der neuen Ware.
   * @param pVerfallsDatum Das Verfallsdatum der neuen Ware.
   */
  public void fuelleFach(int pDrehtellerNr, String pWarenName, double pPreis,
      Date pVerfallsDatum) 
  {
    
    int nInterneDrehtellerNr = pDrehtellerNr - 1;
    // preis zuerst umwandeln
    int nPreis = (int)Math.round(pPreis * 100.0);
      
    // prüfen ob es diesen Warentypen bereits in der Sammlung gibt.
    WarenTyp typ = m_oAlleWarentypen.SucheWarenTyp(pWarenName);
    if (typ != null)
    {
      // es wurde ein bereits angelegter Warentyp gefunden.
      typ.Preis(nPreis);
    }
    else
    {
      // es gibt noch keinen solchen warentyp. neuen erzeugen
      typ = new WarenTyp(pWarenName, nPreis);
      m_oAlleWarentypen.AddWarenTyp(typ);
    }
       
    // das aktuelle Fach füllen
    Ware neueWare = new Ware(typ, pVerfallsDatum);
    mDrehteller[nInterneDrehtellerNr].FuelleFach(neueWare);
    
    // Inventarisierung nachführen
    m_oInventar.Hinzufuegen(typ);
    
    // ware auch im gui anzeigen
    SystemSoftware.zeigeWareInGui(pDrehtellerNr, pWarenName, pVerfallsDatum);
    
    aktualisiereFachAnzeigen(nInterneDrehtellerNr);
  }

  /**
   * Gibt die Objekt-Referenz auf die <em> Kasse </em> zurück.
   */
  public Kasse gibKasse() 
  {
    return mKasse;
  }

  /**
   * Wird von der System-Software jedesmal aufgerufen wenn der gelbe Dreh-Knopf
   * gedrückt wird. <br>
   * Die Applikations-Software führt die Drehteller-Anzeigen nach (Warenpreis,
   * Verfallsdatum). <br>
   * Das Ansteuern des Drehteller-Motors übernimmt die System-Software (muss
   * nicht von der Applikations-Software gesteuert werden.). <br>
   * Die System-Software stellt sicher, dass <em> drehen </em> nicht durchgeführt wird
   * wenn ein Fach offen ist.
   */
  public void drehen() 
  {
    // auch im GUI drehen
    SystemSoftware.dreheWarenInGui();

    // alle Teller drehen
    for (int i = 0; i < mDrehteller.length; i++)
    {
      // aktueller Dreteller drehen
      mDrehteller[i].Drehen();
        
      // anzeige für den aktuellen Drehteller aktualisieren
      aktualisiereFachAnzeigen(i);
    } 
  }

  /**
   * Beim Versuch eine Schiebetüre zu öffnen ruft die System-Software die
   * Methode <code> oeffnen() </code> der Klasse <em> Automat </em> mit der
   * Drehteller-Nummer als Parameter auf. <br>
   * Es wird überprüft ob alles o.k. ist: <br>
   * - Fach nicht leer <br>
   * - Verfallsdatum noch nicht erreicht <br>
   * - genug Geld eingeworfen <br>
   * - genug Wechselgeld vorhanden <br>
   * Wenn nicht genug Geld eingeworfen wurde, wird dies mit
   * <code> SystemSoftware.zeigeZuWenigGeldAn() </code> signalisiert. <br>
   * Wenn nicht genug Wechselgeld vorhanden ist wird dies mit
   * <code> SystemSoftware.zeigeZuWenigWechselGeldAn() </code> signalisiert. <br>
   * Wenn o.k. wird entriegelt (<code> SystemSoftware.entriegeln() </code>) und
   * positives Resultat zurückgegeben, sonst negatives Resultat. <br>
   * Es wird von der System-Software sichergestellt, dass zu einem bestimmten
   * Zeitpunkt nur eine Schiebetüre offen sein kann.
   * 
   * @param pDrehtellerNr Der Drehteller bei welchem versucht wird die
   *          Schiebe-Türe zu öffnen. <br>
   *          Nummerierung beginnt mit 1 (nicht 0)!
   * @return Wenn alles o.k. <code> true </code>, sonst <code> false </code>.
   */
  public boolean oeffnen(int pDrehtellerNr) 
  {
  
    int nInterneDrehtellerNr = pDrehtellerNr - 1;
    
    Fach actFach = mDrehteller[nInterneDrehtellerNr].HoleFachVorDerTuere();
    
    // wenn das Fach nicht leer ist, weiter gehen
    if (!actFach.IsEmpty())
    {
      Ware actWare = actFach.GetWare();
      
      // verfallsdatum prüfen
      if (actWare.AblaufDatum().after(SystemSoftware.gibAktuellesDatum()))
      {
        // prüfen ob genügend Geld eingeworfen wurde
        if (gibKasse().EingeworfenerBetrag() >= actWare.Preis())
        {   
          // prüfen ob wir wechselgeld ausgeben können
          int nRestBetrag = gibKasse().EingeworfenerBetrag() - actWare.Preis();
          
          if (gibKasse().testeWechselgeld(nRestBetrag))
          {
            // alle checks waren erfolgreich!
            // fach entriegeln
            SystemSoftware.entriegeln(pDrehtellerNr);
            
            // ware verbuchen
            gibKasse().WareVerbuchen(actWare);
            
            // fach leeren
            actFach.Fachleeren();
            
            // inventar nachführen
            m_oInventar.Entfernen(actWare.gibWarenTyp());
            
            // prüfen ob eine Bestellung ausgeführt werden muss
            m_oInventar.pruefeUndMachBestellungFallsNoetig(actWare.Name());
            
            aktualisiereFachAnzeigen(nInterneDrehtellerNr);
            
            return true;
          }
          else
          {
            // zu wenig wechselgeld
            SystemSoftware.zeigeZuWenigWechselGeldAn();
          }
        }
        else
        {
          // zu wenig geld
          SystemSoftware.zeigeZuWenigGeldAn();
        }
      }
    }
    else
    {
      System.out.println("Automat::oeffnen() --> Fach leer!");
    }
    
    return false;  
    
  }

  /**
   * Gibt den aktuellen Wert aller im Automaten enthaltenen Waren in Franken
   * zurück. <br>
   * Analyse: <br>
   * Abgeleitetes Attribut. <br>
   * 
   * @return Der totale Warenwert des Automaten.
   */
  public double gibTotalenWarenWert() 
  {
    int nWarenWert = 0;
    Date aktuellesDatum = SystemSoftware.gibAktuellesDatum();
    
    // hier wird über alle Waren iteriert. Weil nach Ablaufdatum nur noch 10% des
    // Warenwertes gerechnet wird, wird hier immer aktiv gerechnet um Redundanzen
    // zu vermeiden
    
    // alle Drehteller iterieren
    for (int drehtellerNr = 0; drehtellerNr < mDrehteller.length; drehtellerNr++)
    {
      Drehteller actTeller = mDrehteller[drehtellerNr];
      
      for (int fachNr = 0; fachNr < actTeller.AnzahlFaecher(); fachNr++)
      {
        Fach actFach = actTeller.HoleSpezifischesFach(fachNr);
        Ware actWare = actFach.GetWare();
        
        if (actWare != null)
        {
          // prüfen ob das Ablaufdatum bereits erreicht ist!
          if (aktuellesDatum.after(actWare.AblaufDatum()))
          {
            // Ablaufdatum überschritten --> 10% des Warenwertes rechnen
            nWarenWert += (actWare.Preis() / 10);
          }
          else
          {
            nWarenWert += actWare.Preis(); 
          }
        }
      }
    }
    
    // weil die Systemsoftware mit double rechnet --> betrag umrechnen
    return ((double)nWarenWert / 100.0);
  }

  /**
   * Gibt die Anzahl der verkauften Ware <em> pName </em> seit (>=)
   * <em> pDatum </em> Zahl zurück.
   * 
   * @param pName Der Name der Ware nach welcher gesucht werden soll.
   * @param pDatum Das Datum seit welchem gesucht werden soll.
   * @return Anzahl verkaufter Waren.
   */
  public int gibVerkaufsStatistik(String pName, Date pDatum) 
  {
    
    int nAnzahlVerkaufte = gibKasse().gibVerkaufsDetails().gibAnzahlVerkaufte(pName, pDatum);
    
    //TODO anzeige im GUI???
    return nAnzahlVerkaufte;
    
  }
  
  /**
   * Hier wird das Fach hinter der Tür geliefert
   * @param i_nDrehtellerNr
   */
  public Fach gibFachVorDerTuer(int i_nDrehtellerNr)
  {
    return mDrehteller[i_nDrehtellerNr].HoleFachVorDerTuere();
  }
  
  /**
   * Liefert die Aktuelle Position der Drehteller
   * @return
   */
  public int gibAktuelleDrehtellerPosition()
  {
    // da alle Drehteller miteinander gedreht werden, geht man davon
    // aus dass alle dieselbe Position besitzen --> also Fix erster Drehteller abfragen.
    
    // nach aussen werden die Drehteller von 1 - 16 nummeriert. Intern von 0 - 15 
    return mDrehteller[0].AktuellePosition() + 1;
  }
  
  /**
   * Konfiguration einer automatichen Bestellung.
   * Der Automat setzt automatisch Bestellungen ab mittels SystemSoftware.bestellen.
   * @param pWarenName
   * @param pGrenze
   * @param pBestellAnzhal
   */
  public void konfiguriereBestellung(String pWarenName, int pGrenze, int pBestellAnzhal)
  {
    BestellungsKonfig konfig = new BestellungsKonfig(pWarenName, pGrenze, pBestellAnzhal);
    
    m_oInventar.FuegeBestellungsKonfigHinzu(konfig);
  }
}
