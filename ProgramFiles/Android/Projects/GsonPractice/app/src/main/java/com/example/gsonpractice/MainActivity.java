package com.example.gsonpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();
        Response response = gson.fromJson(loadJSONFromAsset(), Response.class);
        List<String> childList = new ArrayList<>();
        for (int i = 0; i< 5 ; i++){
            childList.add(response.getPREFERENCES().getPreferences().get(i).getPreferenceType());
        }

        //String value =response.getPREFERENCES().getPreferences().get(0).getPreferenceType();
        //String value2 = response.getPreference().getData().getPrefDetails().get(0).getPreferenceType();;
        System.out.println(childList);


    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("preferences.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}