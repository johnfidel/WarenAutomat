package warenautomat;

import java.util.*;
import java.util.Date;
import warenautomat.*;

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
    mDrehteller[pDrehtellerNr].FuelleFach(new Ware(typ, pVerfallsDatum));
    
  }

  /**
   * Gibt die Objekt-Referenz auf die <em> Kasse </em> zurück.
   */
  public Kasse gibKasse() {
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
    double dblPreis;
    
    // alle Teller drehen
    for (int i = 0; i < mDrehteller.length; i++)
    {
      // aktueller Dreteller drehen
      mDrehteller[i].Drehen();
      
      // preisanzeige aktualisieren
      Ware aktuelleWare = mDrehteller[i].HoleFachVorDerTuere().GetWare();
      if (aktuelleWare != null)
      {
        dblPreis = (double)(aktuelleWare.Preis() / 100);
        SystemSoftware.zeigeWarenPreisAn(i + 1, dblPreis);
      
        // verfallsdatum prüfen und Anzeige aktualisieren
        if (SystemSoftware.gibAktuellesDatum().before(aktuelleWare.AblaufDatum()))
        {
          SystemSoftware.zeigeVerfallsDatum(i + 1, VERFALLSDATUMSANZEIGE_GRUEN);
        }
        else
        {
          SystemSoftware.zeigeVerfallsDatum(i + 1, VERFALLSDATUMSANZEIGE_ROT);
        }
      }
      else
      {
        // Verfallsdatumanzeige ausschalten und Preis auf 0 setzen
        SystemSoftware.zeigeWarenPreisAn(i + 1, 0.0);
        SystemSoftware.zeigeVerfallsDatum(i + 1, VERFALLSDATUMSANZEIGE_AUS);
      }
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
  public boolean oeffnen(int pDrehtellerNr) {
    
    return false;  // TODO
    
  }

  /**
   * Gibt den aktuellen Wert aller im Automaten enthaltenen Waren in Franken
   * zurück. <br>
   * Analyse: <br>
   * Abgeleitetes Attribut. <br>
   * 
   * @return Der totale Warenwert des Automaten.
   */
  public double gibTotalenWarenWert() {
    
    return 0.0; // TODO
    
  }

  /**
   * Gibt die Anzahl der verkauften Ware <em> pName </em> seit (>=)
   * <em> pDatum </em> Zahl zurück.
   * 
   * @param pName Der Name der Ware nach welcher gesucht werden soll.
   * @param pDatum Das Datum seit welchem gesucht werden soll.
   * @return Anzahl verkaufter Waren.
   */
  public int gibVerkaufsStatistik(String pName, Date pDatum) {
    
    return 0; // TODO
    
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
   * Gibt das Fach der entsprechenden Drehtellernummer welches vor der Tür ist zurück
   * @param i_nDrehtellerNr
   * @return
   */
  public Fach gibFachVorDerTuer(int i_nDrehtellerNr)
  {
    return mDrehteller[i_nDrehtellerNr].HoleFachVorDerTuere();
  }
}
