package nlp.fin.model;

import java.math.BigDecimal;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class TestingData extends Tweets{
    private Double posteriorNet;
    private Double posteriorPos;
    private Double posteriorNeg;
    private String klas;

    public Double getPosteriorNet() {
        return posteriorNet;
    }

    public void setPosteriorNet(Double posteriorNet) {
        this.posteriorNet = posteriorNet;
    }

    public Double getPosteriorPos() {
        return posteriorPos;
    }

    public void setPosteriorPos(Double posteriorPos) {
        this.posteriorPos = posteriorPos;
    }

    public Double getPosteriorNeg() {
        return posteriorNeg;
    }

    public void setPosteriorNeg(Double posteriorNeg) {
        this.posteriorNeg = posteriorNeg;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }
}
