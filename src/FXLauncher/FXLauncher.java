package FXLauncher;

import geodetic.Coordinate;
import postalCode.PostalCode;
import postalCode.PostalCodeIndex;
import postalCode.QuadTree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXLauncher extends Application {

	private Text postalCodeTitle, searchTitle;
	private TextField latitudeTextField, longitudeTextField;
	private Button searchButton;
	private ListView<PostalCode> listPostalCode;
	public static final double HEIGHT = 800.0, WIDTH = 1400.0,LIST_SMALL_WIDTH = 300.0;
	
	private PostalCodeIndex postalCodeIndex = new PostalCodeIndex();
	
	@Override
	public void start(Stage primaryStage) throws Exception { // A Stage is really just a window. primaryStage holds a reference-to a Stage object that was prebuilt by the superclass called "Application". We are given a reference-to this prebuilt object
		
		// ListView is a JavaFX control. It is given a reference-to an ObservableList. The ObservableList object holds a collection of references-to PostalCode objects.
		// The ListView object will automatically probe the ObservableList to access values to be shown in the ListView.
		// The ListView object will use the "toString()" method to gather the text to be output for each record.
		listPostalCode = new ListView<>(postalCodeIndex.getPostalList()); listPostalCode.setMinHeight(800.0);
		listPostalCode.setMinWidth(600.0);
		listPostalCode.getStyleClass().add("paragraph");
		//listClosestPC = new  listPostalCode.setMinHeight(800.0);listPostalCode.setMinWidth(600.0); listPostalCode.setStyle("-fx-background-color: #6666FF;  -fx-padding: 19; -fx-spacing: 10;");
		
		postalCodeTitle = new Text("The Postal Code"); 
		postalCodeTitle.setFont(Font.font("Serif", FontWeight.BOLD, 24));
		postalCodeTitle.setFill(Color.RED);
		VBox vBox1 = new VBox(postalCodeTitle, listPostalCode);
		
			
		////////////////////////////////////////////////////////////////////////////////////////////////////
	  // Search title
		searchTitle = new Text("Closest postal codes based on the given latitude and longitude");
		searchTitle.setFont(Font.font("Serif", FontWeight.BOLD, 24));
		searchTitle.setFill(Color.RED);

		////////////////////////////////////////////////////////////////////////////////////////////////////

		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// Latitude Search box content
		Text latitudeSearchBoxTitle = new Text("Enter Latitude"); 
		latitudeSearchBoxTitle.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		latitudeSearchBoxTitle.setFill(Color.RED);
		// latitude content
		latitudeTextField = new TextField(); latitudeTextField.getText();
		latitudeTextField.setFont(Font.font("Serif", FontWeight.BOLD, 20));
  	//CSS LONGITUDETEXTFIELD
		latitudeTextField.setStyle("-fx-color: blue;" + "-fx-text-fill: blue;"); 
		VBox vBoxlatitudeSearchBox = new VBox(latitudeSearchBoxTitle, latitudeTextField);
		////////////////////////////////////////////////////////////////////////////////////////////////////

		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// longitude Search box content
		Text longitudeSearchBoxTitle = new Text("Enter Longitude");
		longitudeSearchBoxTitle.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		longitudeSearchBoxTitle.setFill(Color.RED);
		// longitude content 
		longitudeTextField = new TextField(); longitudeTextField.getText();//gets a text from the user 
		longitudeTextField.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		//CSS LONGITUDETEXTFIELD
		longitudeTextField.setStyle("-fx-color: blue;" + "-fx-text-fill: blue;");		
		VBox vBoxlongitudeSearchBox = new VBox(longitudeSearchBoxTitle ,longitudeTextField);
		////////////////////////////////////////////////////////////////////////////////////////////////////

		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// Search button 
		Text searchBoxTitle = new Text("Search");
		searchBoxTitle.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		searchBoxTitle.setFill(Color.RED);
		searchButton = new Button("Search");
		searchButton.setFont(Font.font("Serif", FontWeight.BOLD, 15));
		QuadTree tree = new QuadTree(null, null, 0);
		searchButton.setOnAction(e -> {//TODO here where we need to work on 
			listPostalCode.getSelectionModel().clearAndSelect(3); listPostalCode.scrollTo(null);
			
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////

		VBox searchBoxVBox = new VBox(searchBoxTitle, searchButton);

		HBox searchBoxHBox  =  new HBox(vBoxlatitudeSearchBox ,vBoxlongitudeSearchBox, searchBoxVBox );
		searchBoxHBox.getStyleClass().add("paragraph");
		VBox vBox2 = new VBox(searchTitle, searchBoxHBox); // Vbox of the title, the two bottons and the result  //TODO results are missing 
		
		HBox hBox = new HBox(vBox1 , vBox2); //TODO i need search value for 3rd vbox
		VBox vBox = new VBox(hBox);
	
		
		// The Scene contains the scenegraph, that is, a collection of Node objects. The argument to the Scene constructor is a reference-to the HBox that currently has 2 children, our ListView objects.
		Scene scene = new Scene(vBox, WIDTH, HEIGHT);
		scene.getStylesheets().add("Stylesheets.css"); // css content
		primaryStage.setTitle("Assignment 4 QuadTree By Warsame Egal");
		primaryStage.setScene(scene); // The Scene object is placed on the Stage 
		primaryStage.getIcons().add(new Image("http://img1.wikia.nocookie.net/__cb20111228065136/simpsons/images/1/11/Twitter_bird_icon.png")); // creates logo
		primaryStage.show(); // Display everything 
	}

	public static void main(String[] args) {
		launch(args); 
		} // with JavaFX programs, this is typically the size of main().
}