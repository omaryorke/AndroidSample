package com.tests.omar.androidsample.business;


import com.tests.omar.androidsample.entities.Info;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class WSAccess {


    String json;


    public static String makeCall(String url) {

        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(replyString);

        return replyString.trim();
    }

    public ArrayList parseQuakeParse(final String response) {

        ArrayList temp = new ArrayList();
        try {

            JSONObject jsonObject = new JSONObject(response);

            if (jsonObject.has("features")) {

                JSONArray jsonArray = jsonObject.getJSONArray("features");



                for (int i = 0; i < jsonArray.length(); i++) {
                    temp.add(new Info(
                            jsonArray.getJSONObject(i).getJSONObject("properties").getString("mag"),
                            jsonArray.getJSONObject(i).getJSONObject("properties").getString("place"),
                            jsonArray.getJSONObject(i).getJSONObject("properties").getString("time"),
                            jsonArray.getJSONObject(i)
                                    .getJSONObject("geometry").getString("coordinates")
                    ));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }

}
