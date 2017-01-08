package nlp.fin.model;

import java.math.BigDecimal;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class Likelihood {
    private String word;
    //count
    private int count;
    private int cPos;
    private int cNeg;
    private int cNet;
    //likelihood
    private BigDecimal pPos;
    private BigDecimal pNeg;
    private BigDecimal pNet;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

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

    public BigDecimal getpPos() {
        return pPos;
    }

    public void setpPos(BigDecimal pPos) {
        this.pPos = pPos;
    }

    public BigDecimal getpNeg() {
        return pNeg;
    }

    public void setpNeg(BigDecimal pNeg) {
        this.pNeg = pNeg;
    }

    public BigDecimal getpNet() {
        return pNet;
    }

    public void setpNet(BigDecimal pNet) {
        this.pNet = pNet;
    }
}
