package dk.sdu.mmmi.cbse.scoresystem.APIHandler;

import dk.sdu.mmmi.cbse.common.services.IScoreService;

import java.io.IOException;
import java.lang.constant.ModuleDesc;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScoreHandler implements IScoreService {


    String baseUrl = "http://localhost:8080/";

    @Override
    public int getScore() {
        return getIntegerAPICall("score");
    }


    @Override
    public int getHighScore() {
        return getIntegerAPICall("highscore");
    }

    @Override
    public void incrementScore() {
        postIntegerAPICall(1, "increment");
    }

    @Override
    public void setHighScore() {
        postIntegerAPICall(0, "sethighscore");
    }

    @Override
    public void setScore(int score) {
        postIntegerAPICall(score, "setscore");
    }


    public int getIntegerAPICall(String endpoint){
        int retries = 5;
        while(retries-- > 0){
            try {
                String url = baseUrl + endpoint;
                URL finalURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) finalURL.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(2000);
                connection.setReadTimeout(2000);


                int status = connection.getResponseCode();

                if (status == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return Integer.parseInt(response.toString());
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return 0;
    }
    public int postIntegerAPICall(int value, String endpoint){
        try {
            String url = baseUrl + endpoint;
            URL finalURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) finalURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(String.valueOf(value).getBytes());
            int status = connection.getResponseCode();

            return status;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
