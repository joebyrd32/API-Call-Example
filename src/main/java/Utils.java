import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.CryptoCurrencies;
import model.DataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Utils {

    static void CleanDataSet(DataSet dataSet, String currenciesUri) {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(currenciesUri));
            while ((line = reader.readLine()) != null) {
                dataSet.data.rates.remove(line.split(",")[0]);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    static DataSet GetCurrencies(String USDExchangeRatesAddress, String iso4217) {
        try{
            URL url = new URL(USDExchangeRatesAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("currency", iso4217);
            connection.connect();
            BufferedReader br = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            connection.disconnect();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            return gson.fromJson(sb.toString(), DataSet.class);
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    static List<CryptoCurrencies> GetCurrencies(String binanceAddress) {
        try{
            URL url = new URL(binanceAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader br = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            connection.disconnect();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            return gson.fromJson(sb.toString(),  new TypeToken<List<CryptoCurrencies>>(){}.getType());
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
