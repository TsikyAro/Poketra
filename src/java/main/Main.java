package main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Connexion;
import model.Employe;

public class Main {
   public static void main(String[] args) throws Exception {
        Employe[] emp = new Employe().EmployeTaux(Connexion.connect());
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
