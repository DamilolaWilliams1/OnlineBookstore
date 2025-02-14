package lab;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Name: 
 * Username: 
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ListView<String> ABlist;

	@FXML
	private ListView<String> SClist;

	@FXML
	private Label ab;

	@FXML
	private Label name;

	@FXML
	private GridPane pane;

	@FXML
	private Label sc;

	@FXML
	private MenuItem dark;

	@FXML
	private MenuItem light;

	@FXML
	private MenuItem rand;

	@FXML
	private Button addo;

	@FXML
	private Button remove;

	Book book;

	String themen;
	
	ArrayList<Book> books = new ArrayList<Book>();

	double p;
	@FXML
	void initialize() {
		assert ABlist != null;
		assert SClist != null;

		ABlist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		SClist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		String theme = getClass().getResource("/style.css").toExternalForm();

		pane.getStylesheets().add(theme);

	}

	@FXML
	void Load(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		File projectDirectory = new File(System.getProperty("user.dir"));

		// Show the file chooser dialog and get the selected file
		File selectedFile;
		fileChooser.setInitialDirectory(projectDirectory);
		selectedFile = fileChooser.showOpenDialog(null);


		try {
			DataInputStream input = new DataInputStream(new FileInputStream(selectedFile));
			while (true) {
				String title = null;
				title = input.readUTF();
				double price = input.readDouble();

				//Make a book
				book = new Book(title, price);
				books.add(book);

				//Put the book in the GUI
				ABlist.getItems().addAll(title);
			}
		} catch (IOException ex) {

		}
	}

	String select;

	@FXML
	void Exit(ActionEvent event) {
		System.exit(0);	
	}

	@FXML
	void Clear() {

		p = 0;
		SClist.getItems().clear();
	}

	@FXML
	void Add(ActionEvent event) {

		ObservableList<String> selected = ABlist.getSelectionModel().getSelectedItems();
		select = ABlist.getSelectionModel().getSelectedItem();
		SClist.getItems().addAll(selected);

	}
	@FXML
	void Remove(ActionEvent event) {

		p = 0;
		ObservableList<String> selected = SClist.getSelectionModel().getSelectedItems();
		select = SClist.getSelectionModel().getSelectedItem();
		SClist.getItems().removeAll(selected);
	}

	@FXML
	void checkOut(ActionEvent event) {

		ObservableList<String> selected = SClist.getItems();
		for (Book x: books) {
			for(int i = 0; i < selected.size(); i++) {
				if(selected.get(i).equals(x.title)) {
					p += x.price;
				}
			}
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Checkout");
		alert.setHeaderText("Checkout Details");

		double tax = 0.07 * p;
		double tot = p + tax;

		System.out.println(p);
		alert.setContentText(String.format(
				"%-15s$%-8.2f\n%-15s$%-8.2f\n%-15s$%-8.2f",
				"Subtotal:", p,
				"Tax:", tax,
				"Total:", tot));
		alert.showAndWait();

	}

	@FXML
	void setTheme(ActionEvent event) {

		//Find out which MenuItem was clicked
		if (event.getSource() == dark) {

			themen = "dark";
			pane.getStyleClass().set(1, "grid-pane-" + themen);
			SClist.getStyleClass().set(1, "list-view-" + themen);
			ABlist.getStyleClass().set(1, "list-view-" + themen);
			name.getStyleClass().set(1, "label-" + themen);
			remove.getStyleClass().set(1, "button-" + themen);
			addo.getStyleClass().set(1, "button-" + themen);
			ab.getStyleClass().set(1, "label-" + themen);
			sc.getStyleClass().set(1, "label-" + themen);

		}
		else if (event.getSource() == light) {

			themen = "light";
			pane.getStyleClass().set(1, "grid-pane-" + themen);
			SClist.getStyleClass().set(1, "list-view-" + themen);
			ABlist.getStyleClass().set(1, "list-view-" + themen);
			name.getStyleClass().set(1, "label-" + themen);
			remove.getStyleClass().set(1, "button-" + themen);
			addo.getStyleClass().set(1, "button-" + themen);
			ab.getStyleClass().set(1, "label-" + themen);
			sc.getStyleClass().set(1, "label-" + themen);


		}
		else if (event.getSource() == rand) {

			themen = "rand";
			pane.getStyleClass().set(1, "grid-pane-" + themen);
			SClist.getStyleClass().set(1, "list-view-" + themen);
			ABlist.getStyleClass().set(1, "list-view-" + themen);
			name.getStyleClass().set(1, "label-" + themen);
			remove.getStyleClass().set(1, "button-" + themen);
			addo.getStyleClass().set(1, "button-" + themen);
			ab.getStyleClass().set(1, "label-" + themen);
			sc.getStyleClass().set(1, "label-" + themen);

		}
	}


}























