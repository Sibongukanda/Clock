import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {
     boolean played = false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        File file = new File("sound.mp3");
        String path=  new File(file.getAbsolutePath()).toURI().toString();
        Media media= new Media(path);
        MediaPlayer player = new MediaPlayer(media);
        Text time = new Text();
        time.setFont(new Font("Lucida Bright Demibold", 120));
        time.setFill(Color.WHITE);
        Text date = new Text();
        date.setFont(new Font("Monospaced Bold", 45));
        date.setFill(Color.CRIMSON);
        VBox root = new VBox(5, time, date);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 800, 460, Color.web("#123"));
        window.setScene(scene);
        window.setTitle("clock");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy");
        Timeline liner = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String fullTime = timeFormat.format(new Date());
            time.setText(fullTime);
            date.setText(dateFormat.format(new Date()).toUpperCase());
            String[] times =fullTime.split(":");
            if (times[1].equalsIgnoreCase("00") && !played){
                player.stop();
                player.play();
                played = true;
            }else if (!times[1].equalsIgnoreCase("00")){
                played =false;
            }
        }));
        liner.setCycleCount(Timeline.INDEFINITE);
        liner.play();
        window.centerOnScreen();
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        root.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                window.setFullScreen(!window.isFullScreen());
            }
        });
        window.show();
    }
}