package application;
	
import java.io.File;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;



public class DrumMachine extends Application {
	
	//background image
	private final String BACKGROUND_IMAGE = "image/drums01.jpg";
	private String musicFile; 
	private DropShadow ds;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			AnchorPane anchor = new AnchorPane();
			anchor.setPadding(new Insets(0,10,0,0));
			
			ds = new DropShadow();
			ds.setColor(Color.ORANGERED);
			
			
			Button A = new Button("A");
			Button S = new Button("S");
			Button D = new Button("D");
			Button F = new Button("F");
			Button G = new Button("G");
			
			HBox buttons = new HBox();
			buttons.getChildren().addAll(A,S,D,F,G);
			buttons.setSpacing(15);
			
			anchor.getChildren().add(buttons);
			AnchorPane.setBottomAnchor(buttons, 30.0);
			AnchorPane.setLeftAnchor(buttons, 70.0);
			
			anchor.setOnKeyPressed(a -> {

				switch (a.getCode()) {
				case A:
					System.out.println("Pressed A");
					A.setEffect(ds);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					musicFile = "src/audio/tom.mp3";
					buttonTransition(A);
					A.setEffect(null);
					break;
				case S:
					System.out.println("Pressed S");
					S.setEffect(ds);
					musicFile = "src/audio/clap.mp3";
					buttonTransition(S);
					break;
				case D:
					System.out.println("Pressed D");
					musicFile = "src/audio/hihat.mp3";
					buttonTransition(D);
					break;
				case F:
					System.out.println("Pressed F");
					musicFile = "src/audio/kick.mp3";
					buttonTransition(F);
					break;
				case G:
					System.out.println("Pressed G");
					musicFile = "src/audio/openhat.mp3";
					buttonTransition(G);
					break;
				default:
					System.out.println("Different key");
				}

				// ensure only valid musicFile played
				if (musicFile != "") {
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
					musicFile = "";
				}

			});
			
			//background image
			BackgroundImage background = new BackgroundImage(new Image(BACKGROUND_IMAGE,800,600,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
																BackgroundPosition.DEFAULT,null);
			
			anchor.setBackground(new Background(background));
			Scene scene = new Scene(anchor,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to transition Button to larger size and back to original size again
	 * @param b Button to animate
	 */
	public void buttonTransition(Button b) {
		ScaleTransition st = new ScaleTransition(Duration.millis(.50), b);
        st.setByX(.3f);
        st.setByY(.3f);

        st.play();
        st.setOnFinished(e -> {
        	
        	ScaleTransition st2 = new ScaleTransition(Duration.seconds(0.5), b);
			st2.setToX(1);
			st2.setToY(1);
			st2.play();
			
        });
        
	}
	
	public void addDropShadow(Button b) {
		b.setEffect(ds);
	}
	
	public void removeDropShadow(Button b) {
		b.setEffect(null);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
