package com.music.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.music.entity.Music;
import com.music.utils.GuiUtils;
import com.music.utils.SingleMediaPlayer;
import com.music.view.ControlledScreen;
import com.music.view.global.Global;

import application.MainApp;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class RootController implements Initializable, ControlledScreen {

	private static class Delta {
		double x, y;
	}

	final Delta dragDelta = new Delta();
	private StageController myController;

	public void setScreenParent(StageController screenpage) {
		this.myController = screenpage;
	}

	public void initialize(URL location, ResourceBundle resources) {
		Image playButtonImage = new Image("/com/music/resource/playButton1.png");
		ImageView imageViewPlay = new ImageView(playButtonImage);
		play_music.setGraphic(imageViewPlay);
		displayTableView();
		setBindTableView();
		setBindTopHbox();
		setBindBottomHbox();
	}

	public void goToMain() {
		myController.setStage(Global.rootViewID);
	}

	private MainApp mainapp;

	public void setMain(MainApp mainapp) {
		this.mainapp = mainapp;
		musicListTable.setItems(mainapp.getMusicData());
	}
/*
 * Music List TableView Component
 */
	@FXML
	private TableView<Music> musicListTable;
	@FXML
	private TableColumn<Music, String> titleColumn;
	@FXML
	private TableColumn<Music, String> specialColumn;
	@FXML
	private TableColumn<Music, String> singerColumn;
	@FXML
	private TableColumn<Music, String> timeColumn;
	@FXML
	private TableColumn<Music, String> sizeColumn;
/*
 * Music List TableView Component
 */
/*
 * Top windows control component
 */
	@FXML
	private HBox winManage;
	@FXML
	private Button btn_close;
	@FXML
	private Button btn_min;
	@FXML
	private Button btn_max;
/*
 * Top windows control component
 */
/*
 * Bottom play manage control component
 */
	@FXML
	private HBox playManage;
	@FXML
	private Button play_music;
	@FXML
	private Button pre_music;
	@FXML
	private Button next_music;
	@FXML
	private Label startTime;
	@FXML
	private Slider play_process;
	@FXML
	private Label endTime;
	@FXML
	private ToggleButton barrage;
	@FXML
	private Slider audio_balance;
/*
 * Bottom play manage control component
 */
	/**
	 * Handle with root stage function. Contain close, zoom in, zoom out, and
	 * drag stage.
	 */
	@FXML
	private void handleClose() {
		btn_close.setOnMouseClicked((event) -> Platform.exit());
	}

	@FXML
	private void handleWinMin() {
		btn_min.setOnMouseClicked((event) -> getThisStage().setIconified(true));
	}

	@FXML
	private void handleWinMax() {
		btn_max.setOnMouseClicked((event) -> 
		{
			if(!getThisStage().isFullScreen()){
				getThisStage().setFullScreen(true);
				
			}else{
				
				getThisStage().setFullScreen(false);;
			}
			getThisStage().setFullScreenExitHint("");
		});
	}
	
	private Stage getThisStage() {
		return myController.getStage(Global.rootViewID);
	}

	public void setWinDrag(Pane root, Stage primarystage) {
		root.setOnMousePressed((MouseEvent event) -> {
			event.consume();
			dragDelta.x = primarystage.getX() - event.getScreenX();
			dragDelta.y = primarystage.getY() - event.getScreenY();
		});
		root.setOnMouseDragged((MouseEvent event) -> {
			event.consume();
			primarystage.setX(event.getScreenX() + dragDelta.x);
			primarystage.setY(event.getScreenY() + dragDelta.y);
			primarystage.getWidth();
			primarystage.getHeight();
			primarystage.getX();
			primarystage.getY();
		});
	}
	public void getPaneScaleListener(Pane pane, Scene scene) {
		musicListTable.prefWidthProperty().bind(pane.widthProperty());
		playManage.prefWidthProperty().bind(pane.widthProperty());
	}
	private void setBindTopHbox(){
		winManage.setAlignment(Pos.BOTTOM_RIGHT);
		HBox.setHgrow(btn_close, Priority.ALWAYS);	
		HBox.setHgrow(btn_max, Priority.ALWAYS);	
		HBox.setHgrow(btn_min, Priority.ALWAYS);	
	}
	private void setBindBottomHbox(){
		playManage.setAlignment(Pos.BOTTOM_CENTER);
		HBox.setHgrow(pre_music, Priority.ALWAYS);
		HBox.setHgrow(play_music, Priority.ALWAYS);
		HBox.setHgrow(next_music, Priority.ALWAYS);
		HBox.setHgrow(play_process, Priority.ALWAYS);
		HBox.setHgrow(barrage, Priority.ALWAYS);
		HBox.setHgrow(audio_balance, Priority.ALWAYS);
	}
	/**
	 * Handle with MediaPlayer. Include play button, next music button, previous
	 * music button, time slider.
	 */
	private MediaPlayer mediaplayer;

	@FXML
	private void handlePlayMusic() {
		SingleMediaPlayer singleMediaPlayer = new SingleMediaPlayer();
		Image playButtonImage = new Image("/com/music/resource/playButton1.png");
		ImageView imageViewPlay = new ImageView(playButtonImage);
		Image pauseButtonImage = new Image("/com/music/resource/pauseButton.png");
		ImageView imageViewPause = new ImageView(pauseButtonImage);
		Music music = selectedMusic();
		mediaplayer = singleMediaPlayer.getCurrentplayer();
		Status status = mediaplayer.getStatus();
		Task<Void> playtask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED) {
					mediaplayer.play();
					play_music.setGraphic(imageViewPause);
				} else {
					mediaplayer.pause();
					play_music.setGraphic(imageViewPlay);
				}
				return null;
			}

		};
		play_music.setOnAction((ActionEvent) -> {
			playtask.run();
		});
	}

	/**
	 * Display the music message in the tableview.
	 */
	private void displayTableView() {
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		specialColumn.setCellValueFactory(cellData -> cellData.getValue().specialProperty());
		singerColumn.setCellValueFactory(cellData -> cellData.getValue().singerProperty());
		timeColumn.setCellValueFactory(new PropertyValueFactory<Music, String>("timeString"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Music, String>("sizeString"));
		musicListTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> changeMusic(newValue));
	}
	private void setBindTableView(){
		titleColumn.prefWidthProperty().bind(musicListTable.prefWidthProperty().multiply(0.2));
		timeColumn.prefWidthProperty().bind(musicListTable.prefWidthProperty().multiply(0.1));
		specialColumn.prefWidthProperty().bind(musicListTable.prefWidthProperty().multiply(0.2));
		singerColumn.prefWidthProperty().bind(musicListTable.prefWidthProperty().multiply(0.2));
		sizeColumn.prefWidthProperty().bind(musicListTable.prefWidthProperty().multiply(0.2));

	}
	/**
	 * new PropertyValueFactory<Music, String>("timeString")
	 * 这方法实现挑选歌曲后的更改，用来设计歌曲详情页。
	 * 
	 * @param newValue
	 */
	public void changeMusic(Music newValue) {

	}

	/**
	 * 
	 */
	public Music selectedMusic() {
		return musicListTable.getSelectionModel().getSelectedItem();
	}
}
