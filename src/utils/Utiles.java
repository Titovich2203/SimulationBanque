package utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


public class Utiles {


    public static ZoneId defaultZoneId = ZoneId.systemDefault();
    private static FXMLLoader fl;
    private static Alert alert;

    private static SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");

    public static void showMessage(String title, String header, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }

    public static boolean confirmMessage(String title, String header, String content) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        Optional<ButtonType> result = a.showAndWait();
        return (result.get() == ButtonType.OK);
    }

    public static void showPopup(String title, String header, String popup) throws IOException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(header);
        DialogPane content = (DialogPane) FXMLLoader.load(LoadPopup.class.getResource("/view/popup/" +popup));
        a.getDialogPane().setContent(content);
        a.showAndWait();
    }

    public static FXMLLoader getLoader(String title, String header, String popup) throws IOException {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        fl = new FXMLLoader(LoadPopup.class.getResource("/view/popup/" +popup));
        alert.getDialogPane().setContent(fl.load());
        return fl;
    }

    public static void showAlert()
    {
        alert.showAndWait();
    }

    public static DialogPane getDialogPane(String popup) throws IOException {
        DialogPane content = (DialogPane) FXMLLoader.load(LoadPopup.class.getResource("/view/popup/" +popup));
        return content;
    }

    public static String crypterString(String s)
    {
        String crypt = "";
        for(int i=0; i<s.length(); i++)
        {
            crypt += "*";
        }
        return crypt;
    }

    public static int convertToInt(String s)
    {
        int x = Integer.MIN_VALUE;
        try {
            x = Integer.parseInt(s);
            if(x<0)
                x = Integer.MIN_VALUE;
        }
        catch (Exception e)
        {

        }
        return x;
    }
    public static Double convertToDouble(String s)
    {
        Double x = Double.MIN_VALUE;
        try {
            x = Double.parseDouble(s);
        }
        catch (Exception e)
        {

        }
        return x;
    }

    public static long convertToLong(String s)
    {
        long x = Long.MIN_VALUE;
        try {
            x = Long.parseLong(s);
        }
        catch (Exception e)
        {

        }
        return x;
    }

    public static String convertDateToString(Date date)
    {
        try {
            return formatter.format(date);
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public static Date convertStringToDate(String date)
    {
       // return formatter.format(date);
        try
        {
            Date d = formatter.parse(date);
            return d;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
