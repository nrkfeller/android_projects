package com.efelnic.weatherapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText cityName;
    TextView weatherText;

    public void findWeather(View view) {
        //Log.i("cityname", cityName.getText().toString());

        //hide when button tapped
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(cityName.getWindowToken(), 0);

        try {
            String encodedCityName = URLEncoder.encode(cityName.getText().toString(), "UTF-8");

            DownloadTask task = new DownloadTask();

            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + cityName.getText().toString() + "&appid=44db6a862fba0b067b1930da0d769e98");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = (EditText)findViewById(R.id.cityName);
        weatherText = (TextView)findViewById(R.id.weatherText);

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while ( data != -1 ){
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Could not find weather for this city", Toast.LENGTH_LONG);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            String message = "";

            try {
                JSONObject jsonObject = new JSONObject(result);

                String weatherForecast = jsonObject.getString("weather");

                JSONArray arr = new JSONArray(weatherForecast);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = "";
                    String descrition = "";

                    main = jsonPart.getString("main");
                    descrition = jsonPart.getString("description");

                    if ( main != "" && descrition !="" ) {
                        message += main + ": " + descrition + "\r\n";
                    }
                }

                if ( message != "" ) {
                    weatherText.setText(message);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
