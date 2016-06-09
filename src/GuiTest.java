//==============================================================================
// Project   : Master of Advanced Studies in Software-Engineering 2016
// Modul     : Projektarbeit OO Softwareentwicklung "Warenautomat"
//             Teil: Design&Implementation
// Title     : GUI-Test-Applikation
// Author    : Thomas Letsch
// Tab-Width : 2
/*///===========================================================================
* Description: Demo-Programm zur Anwendung des GUI's des Warenautomaten. 
$Revision    : 1.09 $  $Date: 2016/06/01 15:52:15 $ 
/*///===========================================================================
//       1         2         3         4         5         6         7         8
//345678901234567890123456789012345678901234567890123456789012345678901234567890
//==============================================================================

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.*;

public class GuiTest {
  
  static public void main(String pArgs[]) throws ParseException {

    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);

    Automat automat = new Automat();
    Kasse kasse = automat.gibKasse();
    
    SystemSoftware.erzeugeGUI(automat);
    
    automat.fuelleFach(1, "Mars", 2.00, df.parse("01.01.2009"));
    
    for(int i = 0; i < 16; i++) {
      String day = String.format("%02d", i+1);
      automat.fuelleFach(1, "Mars"+(i+1), i+1, df.parse(day+".01.2100"));
      automat.drehen();
    }
    
  }

  
}


