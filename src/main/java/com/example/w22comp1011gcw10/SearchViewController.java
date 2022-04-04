package com.example.w22comp1011gcw10;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private ProgressBar progressBar;

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
        progressBar.setVisible(false);
        getInfoButton.setVisible(false);
        msgLabel.setText("");

        //If the user selects a movie from the listview, get the poster art
        resultListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldMovieSelected, newMovieSelected) -> {

                    //create a new Thread to "fetch" the poster art
                    //this is calling the sleep method to simulate doing some slow running task
                    Thread fetchPosterThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisible(true);
                            double progress = 0;
                            for (int counter = 0; counter < 10; counter++) {
                                //simulate the computer doing work
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                progress += 0.1;

                                //create a "final" (non-changeable) variable to pass in the progress
                                //to the Javafx thread
                                final double reportedProgress = progress;

                                //This is the JavaFX thread.  The method runLater() will execute
                                //once the thread is available
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        //the poster has been loaded
                                        if (reportedProgress > .9)
                                        {
                                            progressBar.setVisible(false);
                                            try {
                                                imageView.setImage(new Image(newMovieSelected.getPoster()));
                                            }
                                            catch (IllegalArgumentException e)
                                            {
                                                imageView.setImage(new Image("https://trailerfailure.com/img/images/missingCoverPhoto.jpg"));
                                            }
                                        }
                                        progressBar.setProgress(reportedProgress);
                                    }
                                });
                            }
                        }
                    });
                    fetchPosterThread.start();

                    getInfoButton.setVisible(true);

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
