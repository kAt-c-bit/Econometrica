package controller;
/*γενική κλάση διαχείρησης των entities*/

import javax.persistence.EntityManager;
import javax.persistence.Query;
import remote.DbConnector;

/** 
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΟΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */

public abstract class Controller
{
    //χρησιμοποιούμε static για να έχουμε τον ίδιο entity manager καθ' όλη τη διάρκεια της εκτέλεσης
    protected static EntityManager em;
    
    public Controller()
    {
        if (em == null)
        {
            /*Σύνδεση με τη βάση, δημιουργία entity factory και entity manager*/
            DbConnector.connect();
            em = DbConnector.getEm();
        }
    }
    
    //μέθοδος διαγραφής πίνακα μέσω ενός έτοιμου namedQuery.
    protected void clearTbl(String namedQuery)
    {
        try 
        { 
            em.getTransaction().begin();
            Query query1 = em.createNamedQuery(namedQuery);
            query1.executeUpdate();
            em.getTransaction().commit();
        } 
        catch (Exception e) 
        { 
            em.getTransaction().rollback();
        }  
    }
    
    protected abstract void clearTable();
}


