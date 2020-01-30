/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Country;

/**
 *
 * @author Bill
 */
public class ControllerCountry {
    
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
    
}
