package nlp.fin.utils;

import nlp.fin.model.Tweets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class ReadCSV {
    public ArrayList<Tweets> readCSV (String fileName, int mode) {
        ArrayList<Tweets> results = new ArrayList<Tweets>();
        String delimiter = ";";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            if (mode == 0) line = br.readLine();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(delimiter);

                Tweets tweet = new Tweets();
                if (mode == 0) {
                    tweet.setDate(df.parse(data[1]));
                    tweet.setText(data[4]);
                }
                else {
                    tweet.setText(data[0]);
                }

                results.add(tweet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return results;
    }
}
