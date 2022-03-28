package com.example.w22comp1011gcw10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Movie> resultListView;

    @FXML
    private ImageView imageView;

    @FXML
    private Button getInfoButton;

    @FXML
    private Label msgLabel;


    @FXML
    private void searchMovies()
    {
        ApiResponse apiResponse = APIUtility.getMoviesFromOMDBQuick(searchTextField.getText());
        resultListView.getItems().clear();
        if (apiResponse.getSearch() != null)
        {
            resultListView.getItems().addAll(apiResponse.getSearch());
            msgLabel.setText("");
        }
        else
            msgLabel.setText("Movie not found");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getInfoButton.setVisible(false);
        msgLabel.setText("");

        resultListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldMovieSelected, newMovieSelected) -> {
                    getInfoButton.setVisible(true);
                    try {
                        imageView.setImage(new Image(newMovieSelected.getPoster()));
                    }
                    catch (IllegalArgumentException e)
                    {
                        imageView.setImage(new Image("https://trailerfailure.com/img/images/missingCoverPhoto.jpg"));
                    }
                });
    }

    /**
     * This method will pass the imdb information to the movie details controller
     */
    @FXML
    private void getDetails(ActionEvent event) throws IOException {
        String imdbID = resultListView.getSelectionModel().getSelectedItem().getImdbID();
        System.out.println("Movie ID: "+imdbID);
        SceneChanger.changeScenes(event, "movie-details.fxml",imdbID);
    }
}
