package com.music.view.controller;

import java.io.IOException;
import java.util.HashMap;

import com.music.view.ControlledScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageController extends StackPane {

	private HashMap<String, Stage> stages = new HashMap<>();
	private Pane loadeStage;
	private ControlledScreen myScreenController;
	private Scene tempScene;
	public ControlledScreen getMyScreenController() {
		return myScreenController;
	}

	public Pane getLoadeStage() {
		return loadeStage;
	}

	/**
	 * This method will allow to add stage to map.
	 * @param name stage name
	 * @param stage Stage Object
	 */
	public void addStage(String name, Stage stage) {
		stages.put(name, stage);
	}

	/**
	 * This method can load Stage according resource path and name. And it will add stage.
	 * @param name stage name that should show
	 * @param resources FXML file path
	 * @param styles stage style
	 * @return
	 */
	public boolean loadStage(String name, String resources, StageStyle... styles) {
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resources));
			loadeStage = (Pane) myLoader.load();
			myScreenController = (ControlledScreen) myLoader.getController();
			myScreenController.setScreenParent(this);
			// Construct stage
			tempScene = new Scene(loadeStage);
			Stage tempStage = new Stage();
			tempStage.setScene(tempScene);
			for (StageStyle style : styles) {
				tempStage.initStyle(style);
			}
			addStage(name, tempStage);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public Scene getTempScene() {
		return tempScene;
	}

	/**
	 * Only display stage
	 * @param name show stage
	 * @return
	 */
	public boolean setStage(String name) {
		// screen load
		if (stages.get(name) != null) {
			stages.get(name).show();
		}
			return true;
		
	}
	/**
	 * Display show stage and close needless stage.
	 * @param show show stage
	 * @param close close stage
	 * @return 
	 */
	public boolean setStage(String show, String close){
		if((stages.get(show) != null && stages.get(close) != null) ){
			stages.get(close).close();
			setStage(show);
		}
		return true;
	}
	
	/**
	 * Remove the stage
	 * @param name stage name
	 * @return
	 */
	public boolean unloadeStage(String name){
		if(stages.get(name)!= null){
			stages.remove(name);
		}
		return true;
	}
	/**
	 * Save main stage 
	 * @param primaryStagename
	 * @param primaryStage
	 */
	public void setPrimaryStage(String primaryStagename, Stage primaryStage){
		addStage(primaryStagename, primaryStage);
	}
	
	public Stage getStage(String name){
		return stages.get(name);
	}
	
}
