package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import com.music.dao.MusicDao;
import com.music.dao.Impl.MusicDaoImpl;
import com.music.entity.Music;
import com.music.thread.InitMediaPlayerService;
import com.music.utils.SingleMediaPlayer;
import com.music.view.controller.RootController;
import com.music.view.controller.StageController;
import com.music.view.global.Global;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
	private MusicDaoImpl md = new MusicDaoImpl();
	private StageController stageController;
	private MediaPlayer mediaPlayer;

	public void start(Stage primaryStage) throws Exception {

//		 Music music1 = addMusic();
		// musicData.add(music1);
		
		stageController = new StageController();
		stageController.setPrimaryStage("primaryStage", primaryStage);
		stageController.loadStage(Global.rootViewID, Global.rootViewFX, StageStyle.TRANSPARENT);
		stageController.setStage(Global.rootViewID);
		BorderPane root = (BorderPane) stageController.getLoadeStage();
		Stage stage = stageController.getStage(Global.rootViewID);
		Scene scene = stageController.getScene();
		RootController rc = (RootController) stageController.getMyScreenController();
		rc.setWinDrag(root, stage);
		rc.getPaneScaleListener(root, scene);
		rc.setMain(this);
		addMusic();
//		MediaPlayer mediaPlayer = initMediaPlayer(musicData.get(1));
		// mediaPlayer = SingleMediaPlayer.getInstance().getMediaPlayer(null);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public MediaPlayer getmediaPlayer() {
		return mediaPlayer;
	}

	private ObservableList<Music> musicData = FXCollections.observableArrayList();

	public ObservableList<Music> getMusicData() {
		return musicData;
	}

	/**
	 * 这方法为了实现从tableview 挑选music的功能，现在为了测试直接传入file.
	 * 
	 * @return music
	 */
	public void addMusic() {
		SingleMediaPlayer singleMediaPlayer = new SingleMediaPlayer();
		Task<Void> addMusicThread = new Task<Void>() {
			String dir = "D:\\English";

			@Override
			protected Void call() throws Exception {
				md.addDirMusic(dir);
				List<Music> musiclist = md.getMusices();
				musicData.addAll(musiclist);
				singleMediaPlayer.initMediaPlayer(new File(dir));
				return null;
			}
		};
		addMusicThread.run();

	}
//	protected MediaPlayer initMediaPlayer(Music music){
//		InitMediaPlayerService iMediaPlayer = new InitMediaPlayerService();
//		iMediaPlayer.setFileName(music.getUrl());
//		iMediaPlayer.start();
//		MediaPlayer mediaPlayer = iMediaPlayer.getMediaPlayer();
//		return mediaPlayer;
//	}
}