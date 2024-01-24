package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
   public static void main(String[] args) {
        String dateStr1 = "2022-01-01";
        String dateStr2 = "2024-12-19";
        Date date1 = parseDate(dateStr1);
        Date date2 = parseDate(dateStr2);
        int differenceAnnees = differenceEnAnnees(date1, date2);

        System.out.println("La différence entre les deux dates est de " + differenceAnnees + " années.");
    }

    // Fonction pour parser une chaîne de caractères en objet Date
    private static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Fonction pour calculer la différence en années entre deux dates
    private static int differenceEnAnnees(Date date1, Date date2) {
        long differenceEnMillis = date2.getTime() - date1.getTime();
        long differenceEnJours = differenceEnMillis / (24 * 60 * 60 * 1000);
        return (int) (differenceEnJours / 365);
    }
    
}
