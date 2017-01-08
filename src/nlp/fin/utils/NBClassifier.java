package nlp.fin.utils;

import nlp.fin.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class NBClassifier {
    private final String POSITIVE = "positive";
    private final String NEGATIVE = "negative";
    private final String NETRAL = "netral";

    public TrainingData training(String klas, ArrayList<Tweets> dataTraining, TrainingData td) {
        //TrainingData td = new TrainingData();
        HashMap<String, Likelihood> likelihoods = td.getLikelihoods();
        for (Tweets tweet: dataTraining) {
            for (String word: tweet.getText().split(" ")) {
                if (likelihoods.containsKey(word)) {
                    Likelihood likelihood = likelihoods.get(word);
                    likelihood.setCount(likelihood.getCount()+1);
                    likelihood.setcNet(klas.equals("0")?likelihood.getcNet()+1:likelihood.getcNet());
                    likelihood.setcPos(klas.equals("1")?likelihood.getcPos()+1:likelihood.getcPos());
                    likelihood.setcNeg(klas.equals("2")?likelihood.getcNeg()+1:likelihood.getcNeg());

                    likelihoods.put(word, likelihood);
                }
                else {
                    Likelihood likelihood = new Likelihood();
                    likelihood.setWord(word);
                    likelihood.setCount(1);
                    likelihood.setcNet(klas.equals("0")?1:0);
                    likelihood.setcPos(klas.equals("1")?1:0);
                    likelihood.setcNeg(klas.equals("2")?1:0);

                    likelihoods.put(word, likelihood);
                }
            }
        }

        td.setCount(td.getCount() + likelihoods.size());
        td.setcNet(klas.equals("0")?dataTraining.size():td.getcNet());
        td.setcPos(klas.equals("1")?dataTraining.size():td.getcPos());
        td.setcNeg(klas.equals("2")?dataTraining.size():td.getcNeg());

        return td;
    }

    public static void getProbability(TrainingData td) {
        int counts = td.getcNet()+td.getcNeg()+td.getcPos();
        td.setPriorNet(BigDecimal.valueOf((td.getcNet()*1.0)/(counts*1.0)));
        td.setPriorPos(BigDecimal.valueOf((td.getcPos()*1.0)/(counts*1.0)));
        td.setPriorNeg(BigDecimal.valueOf((td.getcNeg()*1.0)/(counts*1.0)));

        HashMap<String, Likelihood> likelihoods = td.getLikelihoods();
        for (Map.Entry<String, Likelihood> map : likelihoods.entrySet()) {
            Likelihood l = map.getValue();

            l.setpNet(BigDecimal.valueOf( ((l.getcNet()+1)*1.0) / ((td.getcNet()+td.getCount())*1.0) ) );
            l.setpPos(BigDecimal.valueOf( ((l.getcPos()+1)*1.0) / ((td.getcPos()+td.getCount())*1.0) ) );
            l.setpNeg(BigDecimal.valueOf( ((l.getcNeg()+1)*1.0) / ((td.getcNeg()+td.getCount())*1.0) ) );
        }
    }

    public TestingData classify(TrainingData td, Tweets tweet) {
        TestingData test = new TestingData();
        test.setDate(tweet.getDate());
        test.setText(tweet.getText());

        //calculate probability
        HashMap<String, Likelihood> likelihoods = td.getLikelihoods();
        String[] words = tweet.getText().split(" ");
        Double pLikelihoodNet = new Double(1);
        Double pLikelihoodPos = new Double(1);
        Double pLikelihoodNeg = new Double(1);
        for (String word: words) {
            Likelihood map = likelihoods.get(word);

            if (map != null) {
                pLikelihoodNet *= (Double.valueOf(map.getcNet() + 1) / Double.valueOf(td.getcNet() + td.getCount()));
                pLikelihoodPos *= (Double.valueOf(map.getcPos() + 1) / Double.valueOf(td.getcPos() + td.getCount()));
                pLikelihoodNeg *= (Double.valueOf(map.getcNeg() + 1) / Double.valueOf(td.getcNeg() + td.getCount()));
            }
            else {
                pLikelihoodNet *= (Double.valueOf(1) / Double.valueOf(td.getcNet() + td.getCount()));
                pLikelihoodPos *= (Double.valueOf(1) / Double.valueOf(td.getcPos() + td.getCount()));
                pLikelihoodNeg *= (Double.valueOf(1) / Double.valueOf(td.getcNeg() + td.getCount()));
            }
        }

        test.setPosteriorNet(td.getPriorNet().doubleValue()*pLikelihoodNet);
        test.setPosteriorPos(td.getPriorPos().doubleValue()*pLikelihoodPos);
        test.setPosteriorNeg(td.getPriorNeg().doubleValue()*pLikelihoodNeg);

        Double mx = Math.max(Math.max(test.getPosteriorNet(), test.getPosteriorPos()), test.getPosteriorNeg());
        if (mx.equals(test.getPosteriorPos()))
            test.setKlas(POSITIVE);
        else if (mx.equals(test.getPosteriorNeg()))
            test.setKlas(NEGATIVE);
        else
            test.setKlas(NETRAL);

        return test;
    }

    public FinalResult getResult(HashMap<Integer, TestingData> tests) {
        FinalResult res = new FinalResult();
        Integer pos = 0, neg = 0, net = 0;
        for (Map.Entry<Integer, TestingData> map : tests.entrySet()) {
            TestingData data = map.getValue();
            if (POSITIVE.equals(data.getKlas()))
                pos++;
            else if(NEGATIVE.equals(data.getKlas()))
                neg++;
            else
                net++;
        }
        res.setcPosTweets(pos);
        res.setcNegTweets(neg);
        res.setcNetTweets(net);
        res.setcTweets(pos + neg + net);
        return res;
    }
}
