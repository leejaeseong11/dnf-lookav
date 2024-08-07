package com.dnf.lookav.common;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@Component
public class DnfApi {
    @Value("${dnf.api.key}")
    private String apiKey;

    private static final String dnfApiUrl = "https://api.neople.co.kr/df";

    private String encodeURIComponent(String component) {
        String result = null;

        try {
            result = URLEncoder.encode(component, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            result = component;
        }
        return result;
    }

    public JSONObject getCharacterId(String serverId, String characterName) {
        try {
            String addUrl =
                    "/servers/"
                            + serverId
                            + "/characters?characterName="
                            + encodeURIComponent(characterName)
                            + "&apikey="
                            + apiKey;
            URL url = new URL(dnfApiUrl + addUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            return new JSONObject(sb.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
