package application;
///////////////////////////////////////////////////////////////////////////////
//Title:            CS400MileStone2
//
//Files:            Challenger.java
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
import java.util.Arrays;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.BackgroundFill;

/**
 * The BacketGUI class is responsible showing the graphical design of the
 * bracket of the tournament. It shows graphics to the user and allows users to
 * put scores and make the tournament working.
 */
public class Main extends Application {

	private static Tournament t1;
	private static Challenger[] c;
	private static int count1 = 0;
	private static int count2 = 0; 
	

	/**
	 * The main method is responsible for setting of the Tournament before the
	 * graphical part is showing up. It sends filePath to method in Tournament class
	 * to read and populates the challenger array.
	 * 
	 * @param String[]
	 *            args at arg[0], it contains the filename.
	 */
	public static void main(String[] args) {
		t1 = new Tournament();
		c = t1.readFile("names.txt");
		// check the length or null
		// c = t1.readFile(args[0]);

		launch(args);
	}

	/**
	 * The start method is performed after the setting for the tournament is
	 * finished. It shows the graphics of bracket and allows interaction from the
	 * user.
	 * 
	 * @param Stage
	 *            primaryStage the primary stage for GUI
	 */
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tournament Bracket");
		BorderPane bPane = new BorderPane();
		Scene scene = new Scene(bPane, 1300, 980);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		if (c.length == 0) {
			HBox message = new HBox();
			Text text = new Text("No challenger can be found: no challengers, no games, and no champion.");
			text.setFont(Font.font("Arial", 30));
			message.getChildren().add(text);
			message.setAlignment(Pos.CENTER);
			bPane.setCenter(message);
			primaryStage.setScene(scene);
			primaryStage.show();
			return;
		}
		
		Challenger[] losers = new Challenger[2];
		HBox[] challengers = new HBox[c.length * 2 - 2];
		VBox[] m2 = new VBox[c.length - 1];
		VBox[] round = new VBox[(int) (Math.log(c.length) / Math.log(2))];
		Label[] names = new Label[c.length * 2 - 1];
		TextField[] inputs = new TextField[c.length * 2 - 1];
		HBox total = new HBox(150);
		Button[] s1 = new Button[c.length - 1];
		VBox w = new VBox();

		// when there is no challenger
		

		// when there is only one challenger
		if (c.length == 1) {
			HBox message = new HBox();
			Text text = new Text("One challenger was found: " + c[0].getName() + " is the champion");
			text.setFont(Font.font("Arial", 30));
			message.getChildren().add(text);
			message.setAlignment(Pos.CENTER);
			bPane.setCenter(message);
			primaryStage.setScene(scene);
			primaryStage.show();
			return;
		}

		// when there are more than one challengers
		else {
			HBox container = new HBox();
			Label instructionText = new Label("Enter the score for each game and click the submit button");
			instructionText.setFont(Font.font("Arial", 20));
			container.getChildren().add(instructionText);
			container.setAlignment(Pos.CENTER);
			bPane.setTop(container);

			// stores each challenger name and input box into HBox
			for (int i = 0; i < challengers.length; i++) {
				challengers[i] = new HBox();
				names[i] = new Label();
				inputs[i] = new TextField();

				if (i < challengers.length / 2+1) {
					names[i].setAlignment(Pos.CENTER);
					names[i].setPrefSize(50, 5);
					names[i].setFont(Font.font("Verdana", 10));
					names[i].setText(c[i].getName());
					inputs[i].setPromptText("Enter Score");
					inputs[i].setPrefSize(100, 5);
					inputs[i].setFocusTraversable(false);
					challengers[i].getChildren().addAll(names[i], inputs[i]);
				} else {
					names[i].setAlignment(Pos.CENTER);
					names[i].setMaxHeight(15);
					names[i].setText("game" + (i + -7) + " winner");
					inputs[i].setMaxHeight(15);
					inputs[i].setMaxWidth(100);
					inputs[i].setPromptText("Enter Score");
					inputs[i].setVisible(false);
					challengers[i].getChildren().addAll(names[i], inputs[i]);
				}
			}

			// If there are only two challengers from the start,
			// it will only perform championship game
			if (challengers.length == 2) {
				
				m2 = new VBox[2];
				m2[0] = new VBox(10);
				m2[1] = new VBox(10);
				m2[0].getChildren().add(challengers[0]);
				m2[1].getChildren().add(challengers[1]);
				Text roundName = new Text("Championship");
				HBox r = new HBox();
				s1[0] = new Button();
				s1[0] = new Button();
				s1[0].setText("Submit");
				s1[0].setMaxWidth(80); // how to show everything
				s1[0].setPrefSize(80, 5);
				s1[0].setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if(Integer.parseInt(inputs[0].getText()) > Integer.parseInt(inputs[1].getText())) {
							Text t = new Text();
							t.setText("1st place: " + names[0].getText()+"  " + "2nd place: " + names[1].getText());
							w.getChildren().add(t);
						}
						else {
							Text t = new Text();
							t.setText("1st place: " + names[1].getText() + "  " + "2nd place: " + names[0].getText());
							w.getChildren().add(t);
						}
						
					}});
				r.getChildren().add(roundName);
				round[0] = new VBox(80);
				round[0].getChildren().addAll(m2[0],m2[1],s1[0],r);
				round[0].setAlignment(Pos.CENTER);
				total.getChildren().add(round[0]);
				bPane.setRight(w);
			} else {
				losers[0] = new Challenger("");
				losers[1] = new Challenger("");
				int i = 0;
				for (int j = 0; j < m2.length; j++) {
					s1[j] = new Button();
					s1[j].setText("Submit");
					s1[j].setMaxWidth(80); // how to show everything
					s1[j].setPrefSize(80, 5);
					final TextField input1 = inputs[j*2]; 
					final TextField input1_1 = inputs[j*2+1];
					final TextField input2 = inputs[j + c.length];
					final Label name1 = names[j + c.length];
					final Label name2 = names[j*2];
					final Label name3 = names[(j * 2 + 1)];
					final Button submitj = s1[j];
					submitj.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							try {
							Challenger[] c1 = new Challenger[4];
							if (input1.getText().trim().isEmpty()
									|| input1_1.getText().trim().isEmpty()) {
								Alert alert0 = new Alert(AlertType.ERROR, "Error: Missing Scores!");
								alert0.showAndWait().filter(response -> response == ButtonType.OK);
							} else if (Integer.parseInt(input1.getText()) == Integer
									.parseInt(input1_1.getText())) {
								Alert alert1 = new Alert(AlertType.ERROR, "Error: There must be a winner.");
								alert1.showAndWait().filter(response -> response == ButtonType.OK);
							} 
							else {
								if (count1 == 2) {
									count1 = 0; 
								}
								if(count2 == c.length-2) {
									submitj.setDisable(true);
									if(Integer.parseInt(input1.getText()) > Integer.parseInt(input1_1.getText())) {
										Text firstPlace = new Text();
										Text secondPlace = new Text();
										Text thirdPlace = new Text();
										firstPlace.setText("1st place: " + name2.getText());
										firstPlace.setFont(Font.font("Verdana", 40));
										secondPlace.setText(" 2nd place: " + name3.getText());
										secondPlace.setFont(Font.font("Verdana", 25));
										thirdPlace.setText(" 3rd place : " + t1.thirdPlace(losers));
										thirdPlace.setFont(Font.font("Verdana", 20));
										w.getChildren().addAll(firstPlace, secondPlace, thirdPlace);
										w.setAlignment(Pos.CENTER_LEFT);
										bPane.setRight(w);
										return;
									}
									else {
										Text firstPlace = new Text();
										Text secondPlace = new Text();
										Text thirdPlace = new Text();
										firstPlace.setText("1st place: " + name3.getText());
										firstPlace.setFont(Font.font("Verdana", 40));
										secondPlace.setText(" 2nd place: " + name2.getText());
										secondPlace.setFont(Font.font("Verdana", 25));
										thirdPlace.setText(" 3rd place : " + t1.thirdPlace(losers));
										thirdPlace.setFont(Font.font("Verdana", 20));
										w.getChildren().addAll(firstPlace, secondPlace, thirdPlace);
										w.setAlignment(Pos.CENTER_LEFT);
										bPane.setRight(w);
										return;
									}
								}
								
								if (Integer.parseInt(input1.getText()) > Integer
										.parseInt(input1_1.getText())) {
									name1.setText(name2.getText());
									losers[count1].setName(name3.getText());
									losers[count1].setScore(Integer.parseInt(input1_1.getText()));
								} else {
									name1.setText(name3.getText());
									losers[count1].setName(name2.getText());
									losers[count1].setScore(Integer.parseInt(input1.getText()));
								}
								input1.setText(input1.getText());
								input1.setEditable(false);
								input1_1.setText(input1_1.getText());
								input1_1.setEditable(false);
								if (!input1.isEditable() && !input1_1.isEditable()) {
									input2.setVisible(true);
									submitj.setDisable(true);
								}
								count1++;
								count2++;
							}
						}
						
						catch(NumberFormatException e) {
							Alert alert1 = new Alert(AlertType.ERROR, "Error: Invalid input value. Please, enter integer number");
							alert1.showAndWait().filter(response -> response == ButtonType.OK);
						}
						}});
					Text[] gameNum = new Text[m2.length];
					gameNum[j] = new Text("Game" + (j + 1));
					gameNum[j].setFont(Font.font("Arial", 10));
					m2[j] = new VBox(10);
					m2[j].getChildren().addAll(gameNum[j], challengers[i], challengers[i + 1], s1[j]);
					i += 2;
				}

				//when there are 16 challengers
				if (m2.length == 15) {
					Text roundName[] = new Text[4];
					HBox r[] = new HBox[4];
					for (int l = 0; l < roundName.length; l++) {
						roundName[l] = new Text();
						r[l] = new HBox();
					}
					roundName[0].setText("Round1");
					roundName[1].setText("Quarter-Final");
					roundName[2].setText("Semi-Final");
					roundName[3].setText("Championship");
					for (int l = 0; l < r.length; l++) {
						r[l].getChildren().add(roundName[l]);
					}
					round[0] = new VBox(20);
					round[1] = new VBox(40);
					round[2] = new VBox(80);
					round[3] = new VBox(160);
					for (int l = 0; l < 8; l++) {
						round[0].getChildren().add(m2[l]);
					}
					round[0].getChildren().add(r[0]);
					for (int l = 8; l < 12; l++) {
						round[1].getChildren().add(m2[l]);
					}
					round[1].getChildren().add(r[1]);
					for (int l = 12; l < 14; l++) {
						round[2].getChildren().add(m2[l]);
					}
					round[2].getChildren().add(r[2]);
					round[3].getChildren().addAll(m2[14], r[3]);
					round[0].setAlignment(Pos.CENTER);
					round[1].setAlignment(Pos.CENTER);
					round[2].setAlignment(Pos.CENTER);
					round[3].setAlignment(Pos.CENTER);
					total.getChildren().addAll(round[0], round[1], round[2], round[3]);
				}
				//when there are 8 challengers
				else if (m2.length == 7) {
					Text roundName[] = new Text[3];
					HBox r[] = new HBox[3];
					for (int l = 0; l < roundName.length; l++) {
						roundName[l] = new Text();
						r[l] = new HBox();
					}
					roundName[0].setText("Quarter-Final");
					roundName[1].setText("Semi-Final");
					roundName[2].setText("Championship");
					for (int l = 0; l < r.length; l++) {
						r[l].getChildren().add(roundName[l]);
					}
					round[0] = new VBox(40);
					round[1] = new VBox(80);
					round[2] = new VBox(160);
					for (int l = 0; l < 4; l++) {
						round[0].getChildren().add(m2[l]);
					}
					round[0].getChildren().add(r[0]);
					for (int l = 4; l < 6; l++) {
						round[1].getChildren().add(m2[l]);
					}
					round[1].getChildren().add(r[1]);
					round[2].getChildren().addAll(m2[6], r[2]);
					round[0].setAlignment(Pos.CENTER);
					round[1].setAlignment(Pos.CENTER);
					round[2].setAlignment(Pos.CENTER);
					total.getChildren().addAll(round[0], round[1], round[2]);
				}
				
				//when there are 4 challengers
				else {
					Text roundName[] = new Text[2];
					HBox r[] = new HBox[2];
					for (int l = 0; l < roundName.length; l++) {
						roundName[l] = new Text();
						r[l] = new HBox();
					}
					roundName[0].setText("Semi-Final");
					roundName[1].setText("Championship");
					for (int l = 0; l < r.length; l++) {
						r[l].getChildren().add(roundName[l]);
					}
					round[0] = new VBox(80);
					round[1] = new VBox(160);
					for (int l = 0; l < 2; l++) {
						round[0].getChildren().add(m2[l]);
					}
					round[0].getChildren().add(r[0]);
					round[1].getChildren().addAll(m2[2], r[1]);
					round[0].setAlignment(Pos.CENTER_RIGHT);
					round[1].setAlignment(Pos.CENTER);
					total.getChildren().addAll(round[0], round[1]);
				}
			}

		}

		bPane.setLeft(total);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
