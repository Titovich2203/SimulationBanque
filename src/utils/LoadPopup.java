package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadPopup {

    private static Parent root;
    private static int type;

    private static BorderPane rootBorder;

    private static Scene scene;
    private static Stage stage;
    private static String title;
    private static String name;

    private static void initStatge() {
        if (stage == null) {
            stage = new Stage();
        }
    }


    public static void showView(String titre, String nom, int letype) {
        title = titre;
        name = nom;
        type = letype;
        initStatge();
        Load(type);
    }


    private static void LoadType(int type) throws IOException {
        if (type == 1) {
            System.out.println(LoadPopup.class.getResource("/vichshop/view/popup/" + name));
            root = FXMLLoader.load(LoadPopup.class.getResource("/vichshop/view/popup/" + name)); //load a simple view
            scene = new Scene(root);
        } else if (type == 2) // load borderpane
        {

            rootBorder = FXMLLoader.load(LoadPopup.class.getResource("/vichshop/view/popup/" + name));
            scene = new Scene(rootBorder);
        }
        stage.setScene(scene);
        stage.setTitle(title);
        stage.centerOnScreen();
        showed();
    }

    private static void Load(int type) {
        try {
            if (type == 1) {
                LoadType(1);
            } else if (type == 2) // load borderpane
            {
                LoadType(2);
            } else { // set center of border pane
                root = FXMLLoader.load(LoadPopup.class.getResource("/vichshop/view/popup/" + name));
                if (rootBorder != null) {
                    rootBorder.setCenter(root);
                } else {
                    LoadType(1);
                }

            }

            //stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void showed() {
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void close() {
        stage.close();
    }


}
