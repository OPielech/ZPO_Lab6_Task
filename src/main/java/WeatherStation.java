import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class WeatherStation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String city;
        String nameOfCity;
        Double temperature;
        Double pressure;
        Double humidity;
        Double maxTemperature;
        Double minTemperature;


        System.out.println("Please enter the name of city");
        city = scanner.nextLine();

        Gson gson = new Gson();
        StringBuffer response = new StringBuffer();
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=7c738d8fcb3d09197280291bccdd812d";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            String inputLine;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map map = gson.fromJson(String.valueOf(response), Map.class);
        Map map1 = gson.fromJson(String.valueOf(map.get("main")), Map.class);

        nameOfCity = map.get("name").toString();
        temperature = Double.valueOf(map1.get("temp").toString());
        pressure = Double.valueOf(map1.get("pressure").toString());
        humidity = Double.valueOf(map1.get("humidity").toString());
        minTemperature = Double.valueOf(map1.get("temp_min").toString());
        maxTemperature = Double.valueOf(map1.get("temp_max").toString());

        System.out.println("Name of city: " + nameOfCity);
        System.out.printf("Temperature: %.1f\n", (temperature - 273));
        System.out.println("Pressure: " + pressure);
        System.out.println("Humidity: " + humidity);
        System.out.printf("Minimum temperature: %.1f\n", (minTemperature - 273));
        System.out.printf("Maximum temperature: %.1f\n", (maxTemperature - 273));

    }//end of main
}//end of class
