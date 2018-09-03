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
					A.setEffect(ds);
					musicFile = "src/audio/tom.wav";
					buttonTransition(A);
					//A.setEffect(null);
					break;
				case S:
					S.setEffect(ds);
					musicFile = "src/audio/clap.wav";
					buttonTransition(S);
					break;
				case D:
					musicFile = "src/audio/hihat.wav";
					buttonTransition(D);
					break;
				case F:
					musicFile = "src/audio/kick.wav";
					buttonTransition(F);
					break;
				case G:
					musicFile = "src/audio/openhat.wav";
					buttonTransition(G);
					break;
				default:
					//TODO add Alert
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
