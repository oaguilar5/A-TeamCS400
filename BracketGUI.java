///////////////////////////////////////////////////////////////////////////////
//Title:            CS400MileStone2
//
//Files:            Challenger.java
//					Match.java
//					Tournament.java
//					BracketGUI.java
//
//Semester:         CS400 Spring 2018
//
//Author:           Jinhyung Ahn, Oscar Aguilar, Zachary Wille
//Email:            jahn36@wisc.edu, aguilarruval@wisc.edu, 
//					zwille@wisc.edu
//
//Lecturer's Name:  Deb Deppeler
//
//Bugs:				no known bugs. 
////////////////////////////80 columns wide //////////////////////////////////
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * The BacketGUI class is responsible showing the graphical design of the 
 * bracket of the tournament. It shows graphics to the user and allows users
 * to put scores and make the tournament working. 
 */
public class BracketGUI extends Application {

	private static Tournament t1;
	private static Challenger[] c;
	
	/**
	 * The main method is responsible for setting of the Tournament before 
	 * the graphical part is showing up. It sends filePath to method in
	 * Tournament class to read and populates the challenger array. 
	 * 
	 * @param String[] args  
	 * 						at arg[0], it contains the filename. 
	 */
	public static void main(String[] args) {
		t1 = new Tournament();
		c = t1.readFile("names.txt");
//		c = t1.readFile(args[0]);
		launch(args);
	}
	
	/**
	 * The start method is performed after the setting for the tournament is
	 * finished. It shows the graphics of bracket and allows interaction from
	 * the user. 
	 * 
	 * @param Stage primaryStage 
	 * 						the primary stage for GUI
	 */
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tournament Bracket");
		BorderPane bPane = new BorderPane();
		Scene scene = new Scene(bPane, 1000, 600, Color.DARKGRAY);

		HBox[] challengers = new HBox[c.length];
		VBox[] m2 = new VBox[c.length / 2];
		VBox[] round = new VBox[2];
		Label[] names = new Label[c.length];
		TextField[] inputs = new TextField[c.length];
		
		//when there is no challenger
		if (c.length == 0) {
			HBox message = new HBox();
			Text t1 = new Text("No challenger can be found: no challengers, no games, and no champion.");
			t1.setFont(Font.font("Arial", 30));
			message.getChildren().add(t1);
			message.setAlignment(Pos.CENTER);
			bPane.setCenter(message);
			primaryStage.setScene(scene);
			primaryStage.show();
			return;
		}
		
		//when there is only one challenger
		if (c.length == 1) {
			HBox message = new HBox();
			Text t1 = new Text("One challenger was found: " +c[0].getName()+ " is the champion");
			t1.setFont(Font.font("Arial", 30));
			message.getChildren().add(t1);
			message.setAlignment(Pos.CENTER);
			bPane.setCenter(message);
			primaryStage.setScene(scene);
			primaryStage.show();
			return;
		} 
		
		//when there are more than one challengers
		else {
			Text t = new Text("Enter the score for each game and click the submit button");
			t.setFont(Font.font("Arial", 20));
			t.setFill(Color.BLACK);
			bPane.setTop(t);
			
			//stores each challenger name and input box into HBox
			for (int i = 0; i < c.length; i++) {
				challengers[i] = new HBox();
				names[i] = new Label();
				inputs[i] = new TextField();
				names[i].setAlignment(Pos.CENTER);
				names[i].setMinHeight(25);
				names[i].setText(c[i].getName());
				inputs[i].setMaxHeight(20);
				inputs[i].setMaxWidth(100);
				inputs[i].setPromptText("Enter Score");
				inputs[i].setFocusTraversable(false);
				challengers[i].getChildren().addAll(names[i], inputs[i]);
			}
			
			//If there are only two challengers from the start, 
			//it will only perform championship game
			if (challengers.length == 2) {
				HBox roundName = new HBox();
				m2 = new VBox[2];
				m2[0] = new VBox(10);
				m2[1] = new VBox(10);
				m2[0].getChildren().add(challengers[0]);
				m2[1].getChildren().add(challengers[1]);
				Text t1 = new Text("CHAMPIONSHIP GAME");
				t1.setFont(Font.font("Arial", 30));
				roundName.getChildren().add(t1);
				roundName.setAlignment(Pos.CENTER);
				bPane.setCenter(roundName);

			} else {
				
				//If there are four challengers from the beginning, it will
				//start from the semi final
				if(challengers.length == 4) {
					HBox roundName = new HBox();
					Text t1 = new Text("SEMI-FINALS");
					t1.setFont(Font.font("Arial", 30));
					roundName.getChildren().add(t1);
					roundName.setAlignment(Pos.CENTER);
					bPane.setCenter(roundName);
				}
				
				//If there are eight challengers from the beginning, it will
				//start from the quarter final
				else if(challengers.length == 8) {
					HBox roundName = new HBox();
					Text t1 = new Text("Quarter-FINALS");
					t1.setFont(Font.font("Arial", 30));
					roundName.getChildren().add(t1);
					roundName.setAlignment(Pos.CENTER);
					bPane.setCenter(roundName);
				}
				//If there are eight challengers from the beginning, it will
				//start from the 1st round
				else {
					HBox roundName = new HBox();
					Text t1 = new Text("1st ROUND");
					t1.setFont(Font.font("Arial", 30));
					roundName.getChildren().add(t1);
					roundName.setAlignment(Pos.CENTER);
					bPane.setCenter(roundName);
				}
				
				//stores match by grouping first two challengers
				int i = 0;
				int k = challengers.length-1;
				for (int j = 0; j < m2.length; j++) {
					Text[] gameNum = new Text[m2.length];
					gameNum[j] = new Text("Game"+ (j+1));
					gameNum[j].setFont(Font.font("Arial", 15));
					m2[j] = new VBox(10);
					m2[j].getChildren().addAll(gameNum[j], challengers[i], challengers[k]);
					i++;
					k--;
				}
			}
			
			//Put first half of challengers on the left side
			//and other half on the right side
			round[0] = new VBox(40);
			round[1] = new VBox(40);
			for (int l = 0; l < m2.length; l++) {
				if (l < m2.length / 2) {
					round[0].getChildren().add(m2[l]);
				} else {
					round[1].getChildren().add(m2[l]);
				}

			}
			
			//submit button at the bottom-center
			HBox h1 = new HBox();
			Button submitButton = new Button();
			submitButton.setText("Submit");
			h1.getChildren().add(submitButton);
			h1.setAlignment(Pos.CENTER);

			bPane.setLeft(round[0]);
			bPane.setRight(round[1]);
			bPane.setBottom(h1);
		}

		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
