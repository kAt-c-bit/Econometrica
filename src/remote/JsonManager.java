package remote;
/*Κλάση κλήσης API του openweathermap και διαχείρησης δομής δεδομένων JSON*/

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.ControllerCountry;
import controller.ControllerCountryDataset;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Country;
import model.CountryDataset;


/**
 *   @ Νικήτογλου Ελευθέριος - std100152@ac.eap.gr
 *   @ Ρούσσου Άννα - std114276@ac.eap.gr   
 *   @ Σιδηρόπουλος Κωνσταντίνος - std114284@ac.eap.gr
 *   @ Τμήμα ΘΕΣ-2 2017-18
 */

public class JsonManager
{
    public static final String baseURL = "https://www.quandl.com/api/v3/datasets/WWDI/GRC_NY_GDP_MKTP_CN.json?api_key=f3uK5mxjS8rPxPFyJ8fy";
    
    /*Το είδος του ερωτήματος API*/
                
    private static String apiKey;
    private static String units;
    
    public JsonManager()
    {
    }
    
    /*Επιστρέφει τον καιρό τώρα για μια πόλη*/
    public CountryDataset fetchGDP()
    {
        CountryDataset cd = new CountryDataset();
        ControllerCountryDataset ctCountryDataset = new ControllerCountryDataset();
        Country ct = new Country();
        ControllerCountry ctController = new ControllerCountry();
       
        
        try
        {
            /*κατασκευή ενός URL για το ερώτημα JSON weather now*/
            URL url = new URL(createUrlString());
            
            /*Ξεκινάει τη σύνδεση με τον server και αποθηκεύει τα δεδομένα στη ροή δεδομένων "is".*/          
            InputStream is = url.openStream(); 
            
            /*Διαβάζει τη ροή και μετατρέπει τα εισερχόμενα bytes σε χαρακτήρες.*/
            InputStreamReader isr = new InputStreamReader(is);                              
            
            /*Αναλύει την αρχική δομή του json που βρίσκεται στο isr, και επιστρέφει ένα JsonElement το οποίο μπορεί να είναι
            ένα  JsonObject, JsonArray, JsonPrimitive ή ένα JsonNull.*/
            JsonElement jElement = new JsonParser().parse(isr);
            
            /*εμείς γνωρίζουμε οτι είναι ένα JsonObject οπότε το αποθηκεύουμε σε μια αναφορά mainJsonObject*/   
            JsonObject mainJsonObject = jElement.getAsJsonObject(); 
            
            /*Κρατάμε και ένα προσωρινό αντικείμενο για JsonObject για να γίνονται λιγότερες κλήσεις με λιγότερο κώδικα*/
            JsonObject jsonObject;
            
            ct.setIsoCode("300");
            ct.setName("Greece");
            //ctController.addCountry(ct);
            
             mainJsonObject = mainJsonObject.get("dataset").getAsJsonObject();
             //Parse the name of the dataset
             cd.setName(mainJsonObject.get("name").getAsString());
             //Parse the start date of the dataset
             cd.setStartYear(mainJsonObject.get("start_date").getAsString().substring(0, 4));
             //Parse the end date of the dataset
             cd.setEndYear(mainJsonObject.get("end_date").getAsString().substring(0, 4));
             //Set the foreign key 
             cd.setCountryCode(ct);
             // Parse the Description
             cd.setDescription(mainJsonObject.get("description").getAsString());
             
             
             for (JsonElement e : mainJsonObject.get("data").getAsJsonArray()){
                 System.out.println(e.getAsJsonArray().get(0).getAsString().substring(0, 4));
                 System.out.println(e.getAsJsonArray().get(1).getAsDouble());
             }
             
             ctCountryDataset.addCountryDataset(cd);
                          
             System.out.println(cd.getName());
             System.out.println(cd.getCountryCode().getIsoCode());
             System.out.println(cd.getDatasetId());
             
             
            
            } 
        catch (MalformedURLException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            
//            //parse dt
//            wn.getWeatherNowPK().setDt(new Date(mainJsonObject.get("dt").getAsLong()*1000));
//            
//            //parse id
//            wn.getWeatherNowPK().setCityId(mainJsonObject.get("id").getAsInt());
//            wn.setCity(new ControllerCity().findCityByCityId(mainJsonObject.get("id").getAsInt()));
//            
//            //parse {main}->temp
//            jsonObject = mainJsonObject.getAsJsonObject("main");
//            wn.setMaintemp(jsonObject.get("temp").getAsDouble());
//
//            //parse [weather]->{0}->id , [weather]->{0}->icon
//            jsonObject = mainJsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();          
//            /*ερώτημα στον πίνακα WeatherDesc ώστε να φέρει στον Entity Manager την περιγραφή του καιρού στα ελληνικά*/
//            wn.setWeatherDescId(new ControllerWeatherDesc().findWeatherDescById(jsonObject.get("id").getAsInt()));
//            wn.setIcon(jsonObject.get("icon").getAsString());
//            
//            //parse {wind}->speed
//            jsonObject = mainJsonObject.getAsJsonObject("wind");
//            wn.setWindspeed(jsonObject.get("speed").getAsDouble());
//            
//            //parse {clouds}->all
//            jsonObject = mainJsonObject.getAsJsonObject("clouds");
//            wn.setCloudsall(jsonObject.get("all").getAsDouble());
//            
//            //parse {rain}->3h
//            jsonObject = mainJsonObject.getAsJsonObject("rain");
//            if (jsonObject != null) //έλεγχος σε περίπτωση που δεν υπάρχει βροχή
//            {
//                if (jsonObject.get("3h") != null)
//                    wn.setRain(jsonObject.get("3h").getAsDouble());
//            }
//            
//            //parse {snow}->3h
//            jsonObject = mainJsonObject.getAsJsonObject("snow");
//            if (jsonObject != null) //έλεγχος σε περίπτωση που δεν υπάρχει χιόνι
//            {
//                if (jsonObject.get("3h") != null)
//                    wn.setSnow(jsonObject.get("3h").getAsDouble());
//            }
//        
//        
//        return wn;
        
        return cd;
    }
    
    /*Επιστρέφει λίστα με 40 προβλέψεις για 5 ημέρες μιας πόλης*/
//    public ArrayList<Forecast> fetchForecasts(int cityId)
//    {
//        ArrayList<Forecast> forecasts = new ArrayList<Forecast>();
//        
//        try
//        {
//            URL url = new URL(createUrlString(cityId,REQUEST_TYPE.FORECAST));
//            InputStream is = url.openStream();                    
//            InputStreamReader isr = new InputStreamReader(is); 
//            
//            JsonElement jElement = new JsonParser().parse(isr);
//            JsonObject mainJsonObject = jElement.getAsJsonObject();
//                                                                  
//            JsonObject jsonObject;
//            JsonObject jsonObjectListItem; 
//            
//            int cnt = mainJsonObject.get("cnt").getAsInt();
//                    
//            for(int i=0; i<cnt; i++)
//            {
//                Forecast forecast = new Forecast();
//                forecast.setForecastPK(new ForecastPK());
//                jsonObjectListItem = mainJsonObject.getAsJsonArray("list").get(i).getAsJsonObject();
//                //parse temp
//                
//                jsonObject = jsonObjectListItem.getAsJsonObject("main");
//                forecast.setMaintemp(jsonObject.get("temp").getAsDouble());
//                
//                //parse descid and parse icon
//                jsonObject = jsonObjectListItem.getAsJsonArray("weather").get(0).getAsJsonObject();
//                forecast.setWeatherDescId(new ControllerWeatherDesc().findWeatherDescById(jsonObject.get("id").getAsInt()));
//                forecast.setIcon(jsonObject.get("icon").getAsString());
//                
//                //parse clouds
//                jsonObject = jsonObjectListItem.getAsJsonObject("clouds");
//                forecast.setCloudsall(jsonObject.get("all").getAsDouble());
//                
//                 //parse windspeed
//                jsonObject = jsonObjectListItem.getAsJsonObject("wind");
//                forecast.setWindspeed(jsonObject.get("speed").getAsDouble());
//                
//                //parse dt
//                forecast.getForecastPK().setDt(new Date(jsonObjectListItem.get("dt").getAsLong()*1000));
//                
//                //parse cityid
//                jsonObject = mainJsonObject.getAsJsonObject("city");
//                forecast.getForecastPK().setCityId(jsonObject.get("id").getAsInt());
//                forecast.setCity(new ControllerCity().findCityByCityId(jsonObject.get("id").getAsInt()));
//                
//                //parse rain
//                jsonObject = jsonObjectListItem.getAsJsonObject("rain");
//                if (jsonObject != null) //έλεγχος σε περίπτωση που δεν υπάρχει βροχή
//                {
//                    if (jsonObject.get("3h") != null)
//                        forecast.setRain(jsonObject.get("3h").getAsDouble());
//                }
//                 
//                 //parse snow
//                jsonObject = jsonObjectListItem.getAsJsonObject("snow");
//                if (jsonObject != null) //έλεγχος σε περίπτωση που δεν υπάρχει χιόνι
//                {
//                    if (jsonObject.get("3h") != null)
//                        forecast.setSnow(jsonObject.get("3h").getAsDouble());
//                }
//                forecasts.add(forecast);
//            }
//        } 
//        catch (MalformedURLException ex)
//        {
//            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        catch (IOException ex)
//        {
//            Logger.getLogger(JsonManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return forecasts;
//    }
    
    /*Κατασκευή κατάλληλου URL String για το API Request*/
    private String createUrlString()
    {
        String requestParam = null;
        
        return (baseURL);
    }
    
    public static void setApiKey(String _apiKey)
    {
        apiKey = _apiKey;
    }
    
    public static String getApiKey()
    {
        return apiKey;
    }
    
    public static void setUnits(String _units)
    {
        units = _units;
    }
    
    public static String getUnits()
    {
        return units;
    }
}
