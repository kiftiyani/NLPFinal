package nlp.fin.utils;

import nlp.fin.model.Tweets;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class PreProcessing {
    private String URLs = "(https?:// ?\\S+\\s?)|(pic.twitter.com/\\S+\\s?)";
    private String noises = "(RT)|(@\\S+\\s?)|(#\\S+\\s?)|([0-9])";
    private String others = "[^A-Za-z,\'. ]";
    private String negation = "((not)|(don't)|(dont)|(no))";

    private ArrayList<Tweets> data = new ArrayList<Tweets>();

    public ArrayList<Tweets> getData() {
        return data;
    }

    public PreProcessing(ArrayList<Tweets> tweets) {
        data = preprocess(tweets);
    }

    private ArrayList<Tweets> preprocess(ArrayList<Tweets> tweets) {
        ArrayList<Tweets> ret = new ArrayList<Tweets>();
        for (Tweets tweet: tweets) {
            Tweets retTweet = new Tweets();

            String temp = tweet.getText();
            temp = temp.toLowerCase();

            //remove URLs
            temp = temp.replaceAll(URLs, "");

            //remove noises
            String[] words = temp.split(" ");
            temp = "";
            for (String word: words) {
                temp = temp + word.replaceAll(noises, "") + " ";
            }

            //remove other noises
            temp = temp.replaceAll(others, "");

            //handle negation
            String[] sentences = temp.split(",|\\.");
            temp = "";
            for (String sentence: sentences) {
                String[] wordss = sentence.split(" ");
                sentence = "";
                boolean neg = false;
                for (String word: wordss) {
                    word = word.replaceAll(" ", "");
                    if (word.isEmpty()) continue;

                    if (!word.matches(negation) && neg)
                        sentence = sentence + "NOT_" + word + " ";
                    else {
                        sentence = sentence + word + " ";
                        if (word.matches(negation))
                            neg = true;
                    }
                }
                temp = temp + sentence + " ";
            }
            retTweet.setText(temp);
            retTweet.setDate(tweet.getDate());

            ret.add(retTweet);
        }
        return ret;
    }
}
