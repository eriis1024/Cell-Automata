package cellsociety_team05;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Factory extends UserInterface{

	public static Button setButton(int x, int y, int size_x, int size_y, String text) {
		Button button = new Button();
		button.setText(text);
		button.setWrapText(true);
		button.setTranslateX(x);
		button.setTranslateY(y);
		button.setPrefWidth(size_x);
		button.setPrefHeight(size_y);
		return button;
	}

	public static Label setLabel(int x, int y, String text) {
		Label lbl = new Label();
		lbl.setText(text);
		lbl.setTranslateX(x);
		lbl.setTranslateY(y);
		return lbl;
	}

	public static TextField setTextField(int x, int y, int size_x, int size_y) {
		TextField textField = new TextField();
		textField.setTranslateX(x);
		textField.setTranslateY(y);
		textField.setMaxSize(size_x, size_y);
		return textField;
	}
	
	public static Alert setAlert(String header, String content) {
		 Alert alert = new Alert(AlertType.INFORMATION);
	     alert.setTitle(header);
	     alert.setContentText(content);
	     return alert;
	}
}