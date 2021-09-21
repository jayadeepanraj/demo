package com.example.demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class isTheSiteUp {
    private final String SITE_IS_UP = "Site is Up!";
    private final String SITE_IS_DOWN = "Site is Down";
    private final String INCORRECT_URL = "URL is Incorrect";

    @GetMapping("/check")
    public String getURLStatusMessage(@RequestParam String url){
        String returnMessage = "";
        
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCat = conn.getResponseCode()/100;
            if(responseCodeCat != 2 && responseCodeCat != 3){
                returnMessage=SITE_IS_DOWN;
            }else{
                returnMessage=SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage=INCORRECT_URL;
        } catch (IOException e) {
            returnMessage=SITE_IS_DOWN;
        }
        
        return returnMessage;
    }

}
