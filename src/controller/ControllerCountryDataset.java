/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;

import model.CountryDataset;

/**
 *
 * @author Bill
 */
public class ControllerCountryDataset extends Controller{
    
    public ControllerCountryDataset()
    {
        super();
    }
    public void addCountryDataset(CountryDataset cd){
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
    }
    @Override
    protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
