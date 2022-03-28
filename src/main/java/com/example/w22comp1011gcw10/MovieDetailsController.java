package com.example.w22comp1011gcw10;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieDetailsController {

    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Label runTimeLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label writersLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private ListView<String> actorsListView;

    @FXML
    private ListView<Rating> ratingsListView;

    @FXML
    private ImageView imageView;

    private MovieDetails movie;

    /**
     * This method will load a movieID
     */
    public void loadMovieDetails(String imdbID)
    {
        movie = APIUtility.getMovieDetails(imdbID);

        movieTitleLabel.setText(movie.getTitle());
        languageLabel.setText(movie.getLanguage());
        genreLabel.setText(movie.getGenre());
        releaseDateLabel.setText(movie.getReleaseDate());
        runTimeLabel.setText(movie.getRuntime());
        writersLabel.setText(movie.getWriter());

        try {
            imageView.setImage(new Image(movie.getPosterURL()));
        }
        catch (IllegalArgumentException e)
        {
            imageView.setImage(new Image("https://trailerfailure.com/img/images/missingCoverPhoto.jpg"));
        }

        ratingsListView.getItems().addAll(movie.getRatings());
    }
}