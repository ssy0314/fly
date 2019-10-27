package org.neuedu.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        String format = sim.format(date);
        System.out.println(format);
    }
}
