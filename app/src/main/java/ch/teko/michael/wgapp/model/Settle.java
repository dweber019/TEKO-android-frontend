package ch.teko.michael.wgapp.model;

import java.util.Date;

/**
 * Created by Michael on 17.09.2017.
 */

public class Settle {

    public int id;
    public String userOwns;
    public String userLent;
    public String date;
    public boolean payed;
    public double price;

    public Settle(int id, String userOwns, String userLent, String date, boolean payed, double price) {
        this.id = id;
        this.userOwns = userOwns;
        this.userLent = userLent;
        this.date = date;
        this.payed = payed;
        this.price = price;
    }
}
