package nlp.fin;

import nlp.fin.model.*;
import nlp.fin.utils.NBClassifier;
import nlp.fin.utils.PreProcessing;
import nlp.fin.utils.ReadCSV;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        //Training
        TrainingData td = new TrainingData();
        td.setLikelihoods(new HashMap<String, Likelihood>());

        //negative
        td = doTraining("negative.csv", "2", td);

        //positive
        td = doTraining("positive.csv", "1", td);

        //netral
        td = doTraining("netral.csv", "0", td);

        NBClassifier.getProbability(td);

        //Testing
        FinalResult sadiq = doTesting("sadiq_.csv", td);
        sadiq.setName("Sadiq");

        FinalResult zac = doTesting("zac.csv", td);
        zac.setName("Zac");

        System.out.println("Sadiq: Positif= " + Double.valueOf(sadiq.getcPosTweets())/Double.valueOf(sadiq.getcTweets())
                + "; Negatif= " + Double.valueOf(sadiq.getcNegTweets())/Double.valueOf(sadiq.getcTweets())
                + "; Netral= " + Double.valueOf(sadiq.getcNetTweets())/Double.valueOf(sadiq.getcTweets()));
        System.out.println("Zac: Positif= " + Double.valueOf(zac.getcPosTweets())/Double.valueOf(zac.getcTweets())
                + "; Negatif= " + Double.valueOf(zac.getcNegTweets())/Double.valueOf(zac.getcTweets())
                + "; Netral= " + Double.valueOf(zac.getcNetTweets())/Double.valueOf(zac.getcTweets()));
    }

    private static TrainingData doTraining(String fileName, String mode, TrainingData td) {
        NBClassifier nb = new NBClassifier();
        String file = "src/nlp/fin/data/" + fileName;
        ArrayList<Tweets> tweets = new ArrayList<Tweets>();

        ReadCSV reader = new ReadCSV();
        tweets = reader.readCSV(file, 1);

        PreProcessing pre = new PreProcessing(tweets);

        td = nb.training(mode, pre.getData(), td);

        return td;
    }

    private static FinalResult doTesting(String fileName, TrainingData td) {
        NBClassifier nb = new NBClassifier();
        String file = "src/nlp/fin/data/" + fileName;
        ArrayList<Tweets> tweets = new ArrayList<Tweets>();

        ReadCSV reader = new ReadCSV();
        tweets = reader.readCSV(file, 0);

        PreProcessing pre = new PreProcessing(tweets);

        HashMap<Integer, TestingData> tests = new HashMap<Integer, TestingData>();
        int i = 0;
        for (Tweets tweet: tweets) {
            TestingData tmp = nb.classify(td, tweet);
            tests.put(i, tmp);
            i++;
        }
        return nb.getResult(tests);
    }
}
