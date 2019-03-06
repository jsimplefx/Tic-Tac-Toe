package sauce;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;


public class gameController {

    // declaring the buttons
    @FXML
    private JFXButton btn0;

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton btn2;

    @FXML
    private JFXButton btn3;

    @FXML
    private JFXButton btn4;

    @FXML
    private JFXButton btn5;

    @FXML
    private JFXButton btn6;

    @FXML
    private JFXButton btn7;

    @FXML
    private JFXButton btn8;

    // winner label (invisible ny default)
    @FXML
    private Label winner;

    // current player label (X by default)
    @FXML
    private Label currentPlayer;

    // current player String (help in tracking player)
    private String player = "X";

    //handle button press even
    @FXML
    void handleBtn(ActionEvent event) {
        if (((JFXButton) event.getSource()).getText().equals("-")) { // check if the button is empty first before changing value( "-" means empty)
            ((JFXButton) event.getSource()).setText(player); // i cant believe you can actually do that(directly update the button text)
            checkWin(); // check if that move resulted in a win or tie
            checkPlayer(); // check current player and change to the other
        }
    }

    // switching between players
    private void checkPlayer(){
        if (currentPlayer.getText().equals("X")){
            player = "O";
            currentPlayer.setText(player);
        } else {
            player = "X";
            currentPlayer.setText(player);
        }
    }

    // check win or tie status
    private void checkWin(){
        checkRows(); // check wins across rows
        checkColumns(); // check wins across columns
        checkDiag(); // check wins across diagonal
        checkTie(); // check if all fields are empty (a tie)
    }

    // checking wins across rows
    private void checkRows(){
        // compare the text of each of the 3 buttons on each of the 3 rows
        if (btn0.getText().equals(btn1.getText()) && btn1.getText().equals(btn2.getText()) && !btn1.getText().equals("-")){
            winner.setText("Player " + player + " Won!"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
        if (btn3.getText().equals(btn4.getText()) && btn4.getText().equals(btn5.getText()) && !btn3.getText().equals("-")){
            winner.setText("Player " + player + " Won!"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
        if (btn6.getText().equals(btn7.getText()) && btn7.getText().equals(btn8.getText()) && !btn6.getText().equals("-")){
            winner.setText("Player " + player + " Won!"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
    }

    // check for wins across columns
    private void checkColumns(){
        // compare the text of each of the 3 buttons on each of the 3 rows
        if (btn0.getText().equals(btn3.getText()) && btn3.getText().equals(btn6.getText()) && !btn0.getText().equals("-")){
            winner.setText("Player " + player + " Won"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
        if (btn1.getText().equals(btn4.getText()) && btn4.getText().equals(btn7.getText()) && !btn1.getText().equals("-")){
            winner.setText("Player " + player + " Won!"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
        if (btn2.getText().equals(btn5.getText()) && btn5.getText().equals(btn8.getText()) && !btn2.getText().equals("-")){
            winner.setText("Player " + player + " Won!"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
    }

    // check for wins diagonally
    private void checkDiag(){
        // compare the text of each of the 3 buttons on each of the 3 rows
        if (btn0.getText().equals(btn4.getText()) && btn4.getText().equals(btn8.getText()) && !btn0.getText().equals("-")){
            winner.setText("Player " + player + " Won"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
        if (btn2.getText().equals(btn4.getText()) && btn4.getText().equals(btn6.getText()) && !btn2.getText().equals("-")){
            winner.setText("Player " + player + " Won!"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }

    }

    // check if all fields are't equal to "-" (and ofc there is no win combination if it made it here)
    private void checkTie(){
        if (!btn0.getText().equals("-") && !btn1.getText().equals("-") &&
            !btn2.getText().equals("-") && !btn3.getText().equals("-") &&
            !btn4.getText().equals("-") && !btn5.getText().equals("-") &&
            !btn6.getText().equals("-") && !btn7.getText().equals("-") &&
            !btn8.getText().equals("-")){
            winner.setText("Its a Tie!"); // set the winner label text
            winner.setVisible(true); // make it visible now
            reStart(); // check if user wants to restart the game
        }
    }

    // ask the user if they want to restart the game or exit using an alert message
    private void reStart(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("restart");
        alert.setHeaderText("restart the game?");
        ButtonType Yes = new ButtonType("Yes");
        ButtonType No = new ButtonType("No");
        alert.getButtonTypes().setAll(Yes, No);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == Yes){ // the isPresent() thingy is to make sure the result is not null (intelli complains bout this)
            // set all buttons text to empty and hide the winner label
            btn0.setText("-");
            btn1.setText("-");
            btn2.setText("-");
            btn3.setText("-");
            btn4.setText("-");
            btn5.setText("-");
            btn6.setText("-");
            btn7.setText("-");
            btn8.setText("-");
            winner.setVisible(false);
        }
        else if (result.isPresent() && result.get() == No){
            // close the program using the running platform's way
            Platform.exit();
            System.exit(0);
        }
    }
}
