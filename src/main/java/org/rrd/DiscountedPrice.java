package org.rrd;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscountedPrice {
    public static int getDiscountedPrice(String barcode) {
        String apiURL = "https://jsonmock.hackerrank.com/api/inventory?barcode=" + barcode;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JSONArray dataArray = getDataArray(connection);
                if (dataArray.size() == 1) {
                    JSONObject item = (JSONObject) dataArray.get(0);
                    int price = (int) item.get("price");
                    int discount = (int) item.get("discount");
                    return Math.round(price - ((float) (discount / 100) * price));
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONArray getDataArray(HttpURLConnection connection) throws IOException, ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();
        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());
        return (JSONArray) jsonResponse.get("data");
    }
}
