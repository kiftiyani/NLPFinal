package nlp.fin.model;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class FinalResult {
    private String name;
    private Integer cPosTweets;
    private Integer cNegTweets;
    private Integer cNetTweets;
    private Integer cTweets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getcPosTweets() {
        return cPosTweets;
    }

    public void setcPosTweets(Integer cPosTweets) {
        this.cPosTweets = cPosTweets;
    }

    public Integer getcNegTweets() {
        return cNegTweets;
    }

    public void setcNegTweets(Integer cNegTweets) {
        this.cNegTweets = cNegTweets;
    }

    public Integer getcNetTweets() {
        return cNetTweets;
    }

    public void setcNetTweets(Integer cNetTweets) {
        this.cNetTweets = cNetTweets;
    }

    public Integer getcTweets() {
        return cTweets;
    }

    public void setcTweets(Integer cTweets) {
        this.cTweets = cTweets;
    }
}
