package com.javiermoreno.billiapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

public class RequestHandler {
    public String sendPostRequest(String requestURL, HashMap<String, String> postDataParams) {
        StringBuilder sb = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(requestURL).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                while (true) {
                    String readLine = br.readLine();
                    String response = readLine;
                    if (readLine == null) {
                        break;
                    }
                    sb.append(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
