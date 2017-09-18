package ch.teko.michael.wgapp.model;

import java.util.Date;

/**
 * Created by Michael on 11.09.2017.
 */

public class Purchase {

    public double price;
    public boolean payed;
    public int id;
    public String date;
    public int userId;


    public Purchase(String date) {
        this.date = date;
    }
}
