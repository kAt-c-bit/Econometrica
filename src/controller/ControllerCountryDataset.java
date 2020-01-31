/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;

/**
 *
 * @author Bill
 */
public class ControllerCountryDataset extends Controller{
    
    public ControllerCountryDataset()
    {
        super();
    }
    
    @Override
    protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
