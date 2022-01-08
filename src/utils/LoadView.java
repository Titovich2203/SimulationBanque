package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadView
{

	private static Parent root;
	private static int type;

	private static BorderPane rootBorder;

	private static Scene scene;
	private static Stage stage;
	private static String title;
	private static String name;

	private static FXMLLoader fl = new FXMLLoader();

	private static void initStatge()
	{
		if(stage==null){
			stage=new Stage();
		}
	}


	public static void showView(String titre, String nom, int letype)
	{
		title=titre;
		name=nom;
		type = letype;
		initStatge();
		Load(type);
	}


	private static void LoadType(int type) throws IOException
	{
		if(type == 1){
			System.out.println(LoadView.class.getResource("/view/"+name));
			root= fl.load(LoadView.class.getResource("/view/"+name)); //load a simple view
			scene=new Scene(root);
		}
		else if(type == 2) // load borderpane
		{

			rootBorder= fl.load(LoadView.class.getResource("/view/" +name));
			scene=new Scene(rootBorder);
		}
		stage.setScene(scene);
		stage.setTitle(title);
		stage.centerOnScreen();
		showed();
	}

	private static void Load(int type)
	{
		try
		{
			if(type == 1){
				LoadType(1);
			}
			else if(type == 2) // load borderpane
			{
				LoadType(2);
			}
			else{ // set center of border pane
				root= fl.load(LoadView.class.getResource("/view/" +name));
				if(rootBorder != null){
					rootBorder.setCenter(root);
				}
				else{
					LoadType(1);
				}

			}

			//stage.setResizable(false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}



	private static void showed()
	{
		stage.show();
	}
	/*public static void hide() {
        stage.close();
    }
*/
	public static Stage getStage()
	{
		return stage;
	}
/*
	public static Object getController(String nom) throws IOException {
	    if(!name.equals(""))
        {
            System.out.println(nom);

            AnchorPane userPane = (AnchorPane) fl.load(LoadView.class.getResource("/view/"+nom).openStream());
            Object controller = fl.getController();
            return controller;
        }
		//System.out.println(scene.getProperties().get("fx:controller"));
        return null;
	}
*/

}
