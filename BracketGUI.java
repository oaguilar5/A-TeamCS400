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
	private static int count1 = 0;
	private static int count2 = 0;
	private static int count3 = 0;
	private static int count4 = 0;
	private static int count5 = 0;
	
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
		Scene scene = new Scene(bPane, 2000, 1300, Color.DARKGRAY);
		

		HBox[] winners = new HBox[c.length-2];
		HBox[] challengers = new HBox[c.length];
		VBox[] m2 = new VBox[c.length / 2];
		VBox[] m3 = new VBox[m2.length-1];
		VBox[] round = new VBox[(int) (Math.log(c.length) / Math.log(2))];
		Label[] names = new Label[c.length];
		Label[] winnerNames = new Label[winners.length];
		TextField[] inputs = new TextField[c.length];
		TextField[] inputs2 = new TextField[winners.length];
		HBox total = new HBox(100);
		Button[] s1 = new Button[m2.length];
		Button[] s2 = new Button[m3.length];
		
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
				inputs[i].setMaxHeight(15);
				inputs[i].setMaxWidth(100);
				inputs[i].setPromptText("Enter Score");
				inputs[i].setFocusTraversable(false);
				challengers[i].getChildren().addAll(names[i], inputs[i]);
			}
			//winner of each game; 
			for(int ii = 0; ii < winners.length; ii++) {
				winners[ii] = new HBox();
				winnerNames[ii] = new Label();
				inputs2[ii] = new TextField();
				winnerNames[ii].setAlignment(Pos.CENTER);
				winnerNames[ii].setMinHeight(25);
				winnerNames[ii].setText("game"+ (ii+1)+" winner");
				inputs2[ii].setMaxHeight(20);
				inputs2[ii].setMaxWidth(100);
				inputs2[ii].setPromptText("Enter Score");
				inputs2[ii].setVisible(false);
				winners[ii].getChildren().addAll(winnerNames[ii], inputs2[ii]);
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
				for (int j = 0; j < m2.length; j++) {
					s1[j] = new Button();
					s1[j].setText("Submit");
					s1[j].setMaxWidth(10); // how to show everything
					s1[j].setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							
							c[count1].setScore(Integer.parseInt(inputs[count1].getText()));
							c[count1+1].setScore(Integer.parseInt(inputs[count1+1].getText()));

							if(Integer.parseInt(inputs[count1].getText()) > Integer.parseInt(inputs[count1+1].getText())) {
								winnerNames[count2].setText(names[count1].getText());
							}
							else {
								winnerNames[(count2)].setText(names[(count1+1)].getText());
							}
							inputs[count1].setText(inputs[count1].getText());
							inputs[count1].setEditable(false);
							inputs[count1+1].setText(inputs[count1+1].getText());
							inputs[count1+1].setEditable(false);
							count1+=2; 
							count2++;
							if(count2 %2 == 0) {
								inputs2[count2-2].setVisible(true);
								inputs2[count2-1].setVisible(true);
								s2[count3].setVisible(true);
								count3++;
							}
							if(count3 == c.length/2) {
					
							
								c = t1.matchMaking(c);
								
							}
	
						}
					});
					Text[] gameNum = new Text[m2.length];
					gameNum[j] = new Text("Game"+ (j+1));
					gameNum[j].setFont(Font.font("Arial", 15));
					m2[j] = new VBox(10);
					m2[j].getChildren().addAll(gameNum[j], challengers[i], challengers[i+1],s1[j]);
					i+=2;
				}
				i = 0; 
//				count1 = 0;
//				count3 = 0;
				for(int jj = 0; jj< m3.length; jj++) {
					s2[jj] = new Button();
					s2[jj].setText("Submit");
					s2[jj].setVisible(false);
					s2[jj].setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
//							if(count5 == challengers.length/2-1) {
//								HBox w = new HBox();
//								if(Integer.parseInt(inputs2[count4].getText()) > Integer.parseInt(inputs2[count4+1].getText())) {
//									Text t = new Text("1st place: " + winnerNames[count2-1] +
//													" 2nd place:" + winnerNames[count4-3]);
//									w.getChildren().add(w);
//									w.setAlignment(Pos.CENTER);
//									bPane.setRight(w);
//								}
//								else {
//									Text t = new Text("1st place: " + winnerNames[count2-2] +
//													" 2nd place:" + winnerNames[count4-2]);
//									w.getChildren().add(w);
//									w.setAlignment(Pos.CENTER);
//									bPane.setRight(w);
//								}
								
//							}
							c[count4].setScore(Integer.parseInt(inputs2[count4].getText()));
							c[count4+1].setScore(Integer.parseInt(inputs2[count4+1].getText()));

							if(Integer.parseInt(inputs2[count4].getText()) > Integer.parseInt(inputs2[count4+1].getText())) {
								winnerNames[count2].setText(winnerNames[count4].getText());
							}
							else {
								winnerNames[(count2)].setText(winnerNames[(count4+1)].getText());
							}
							inputs2[count4].setText(inputs2[count4].getText());
							inputs2[count4].setEditable(false);
							inputs2[count4+1].setText(inputs2[count4+1].getText());
							inputs2[count4+1].setEditable(false);
							
							if(count2 %2 == 0) {
								inputs2[count2-2].setVisible(true);
								inputs2[count2-1].setVisible(true);
								s2[count3].setVisible(true);
								count3++;
								count5++;
							}
							
							count4+=2; 
							count2++;
							//display first place, 2nd place and 3rd place 
//							if(count3 == c.length/2) {
//								c = t1.matchMaking(c);
//							}
	
						}
					});
					Text[] gameNum = new Text[m3.length];
					gameNum[jj] = new Text("Game" + (jj+m2.length+1));
					gameNum[jj].setFont(Font.font("Arial", 15));
					m3[jj] = new VBox(10);
					m3[jj].getChildren().addAll(gameNum[jj], winners[i], winners[i+1], s2[jj]);
					i+=2;
				}
				
			}
			
			//Put first half of challengers on the left side
			//and other half on the right side
			round[0] = new VBox(10);

			for (int l = 0; l < m2.length; l++) {
				round[0].getChildren().add(m2[l]);

			}
				if(m3.length == 7) {
					round[1] = new VBox(40);
					round[2] = new VBox(80);
					round[3] = new VBox(160);
					
					round[1].getChildren().addAll(m3[0], m3[1], m3[2],m3[3]);
					round[1].setAlignment(Pos.CENTER_RIGHT);
					round[2].getChildren().addAll(m3[4],m3[5]);
					round[2].setAlignment(Pos.CENTER);
					round[3].getChildren().addAll(m3[6]);
					round[3].setAlignment(Pos.CENTER);
					total.getChildren().addAll(round[0],round[1], round[2], round[3]);
					
				}
				else if(m3.length ==5) {
					round[1].getChildren().addAll(m3[0], m3[1], m3[2],m3[3]);
					round[2].getChildren().addAll(m3[4],m3[5]);
				}
				else if(m3.length == 3) {
					round[1] = new VBox(40);
					round[2] = new VBox(80);
					round[1].getChildren().addAll(m3[0], m3[1]);
					round[1].setAlignment(Pos.CENTER_RIGHT);
					round[2].getChildren().add(m3[2]);
					round[2].setAlignment(Pos.CENTER);
					total.getChildren().addAll(round[0],round[1], round[2]);
				}
				else {
					round[1].getChildren().addAll(m3[0]);
					round[1].setAlignment(Pos.CENTER_RIGHT);
					total.getChildren().add(round[1]);
				}
			
//			//submit button at the bottom-center
//			HBox h1 = new HBox();
//			Button submitButton = new Button();
//			submitButton.setText("Submit");
//			h1.getChildren().add(submitButton);
//			h1.setAlignment(Pos.CENTER);
			
//			submitButton.setOnAction(new EventHandler<ActionEvent>() {
//			
//				@Override
//				public void handle(ActionEvent event) {
//					for(int i = 0; i< inputs.length; i++) {
//						c[i].setScore(Integer.parseInt(inputs[i].getText()));
//						inputs[i].setVisible(false);
//						//replace input with text and disable the input 
//					}
//					for(int j = 0; j < challengers.length/2; j++) {
//						inputs2[j].setVisible(true);
//					}
//					//get the winner and present the bracket with the winner 
//					
//				}
//			});

			
//			bPane.setBottom(h1);
			
		}
		
		bPane.setLeft(total);

		

		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
