/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Country;


public class ControllerCountry extends Controller{

    public ControllerCountry()
    {
        super();
    }
    
    public void addCountry(Country c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    
    
    
    public void initializeData(){
        List<List<String>> records = new ArrayList<>();
        List<Country> ct = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/resources/iso-countries.csv")); 
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }
        catch (Exception e){
            System.out.println(e);        
        }
        
        for (List<String> e :records){
            Country tmp = new Country(e.get(3),e.get(0));
            ct.add(tmp);
        }
        for(Country c : ct){
            System.out.println(c.getName() + " " + c.getIsoCode());
        }
    }
    
    @Override
    protected void clearTable() {
        clearTbl("City.deleteAll");
    }
    
}
