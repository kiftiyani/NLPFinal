package nlp.fin.model;

import java.util.Date;

/**
 * Created by kiftiyani on 1/8/2017.
 */
public class Tweets {
    private String text;
    private Date date;

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
