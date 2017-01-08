package nlp.fin.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class TrainingData {
    //count
    private int count;
    private int cPos;
    private int cNeg;
    private int cNet;
    //prior
    private BigDecimal priorPos;
    private BigDecimal priorNeg;
    private BigDecimal priorNet;
    //likelihood
    private HashMap<String, Likelihood> likelihoods;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getcPos() {
        return cPos;
    }

    public void setcPos(int cPos) {
        this.cPos = cPos;
    }

    public int getcNeg() {
        return cNeg;
    }

    public void setcNeg(int cNeg) {
        this.cNeg = cNeg;
    }

    public int getcNet() {
        return cNet;
    }

    public void setcNet(int cNet) {
        this.cNet = cNet;
    }

    public BigDecimal getPriorPos() {
        return priorPos;
    }

    public void setPriorPos(BigDecimal priorPos) {
        this.priorPos = priorPos;
    }

    public BigDecimal getPriorNeg() {
        return priorNeg;
    }

    public void setPriorNeg(BigDecimal priorNeg) {
        this.priorNeg = priorNeg;
    }

    public BigDecimal getPriorNet() {
        return priorNet;
    }

    public void setPriorNet(BigDecimal priorNet) {
        this.priorNet = priorNet;
    }

    public HashMap<String, Likelihood> getLikelihoods() {
        return likelihoods;
    }

    public void setLikelihoods(HashMap<String, Likelihood> likelihoods) {
        this.likelihoods = likelihoods;
    }
}
