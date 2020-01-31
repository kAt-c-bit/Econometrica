/** 
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΟΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package econometrica;

import controller.ControllerCountry;
import remote.JsonManager;

public class Econometrica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       JsonManager jm = new JsonManager();
        jm.fetchGDP();
    
// TODO code application logic here
    }
    
}
