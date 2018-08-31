package kevindarren.whattemp;

import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here

                // Create URL
                try{
                    URL githubEndpoint = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
                    HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");


                    if (myConnection.getResponseCode() == 200) {
                        // Success
                        // Further processing here

                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                        JsonReader jsonReader = new JsonReader(responseBodyReader);

                        jsonReader.beginObject(); // Start processing the JSON object
                        System.out.print(jsonReader.toString());
                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName(); // Fetch the next key
                            if (key.equals("code")) { // Check if desired key
                                // Fetch the value as a String
                                String value = jsonReader.nextString();

                                System.out.print(value);

                                break; // Break out of the loop
                            } else {
                            }
                        }
                    } else {
                        // Error handling code goes here
                        int b = 5;
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Create connection
            }
        });

    }
}
