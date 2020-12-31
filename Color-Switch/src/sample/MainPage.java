package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

class MainPage extends Application implements Page, Serializable{

    private static Scene scene1;
    private Group root ;
    private Text t;
    private Game game;
    private int total_stars=0;
    private String filename = "file.ser";

    public int getTotal_stars()
    {
        return total_stars;
    }
    public void decTotal_stars()
    {
        total_stars-=10;
    }
    public static Scene getScene1()
    {
        return scene1;
    }
    private static MainPage mainPage_gen = null;
    public static MainPage getInstance() { if (mainPage_gen == null) {
        mainPage_gen = new MainPage(); }
        return mainPage_gen; }

    private MainPage() { }
    public void display(Stage _stage)
    {
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            total_stars = (int)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add_stars(int num)
    {
        total_stars+=num;
        root.getChildren().remove(t);
        t.setText(String.valueOf(total_stars));
        root.getChildren().add(t);
    }
    private void start_new(Stage stage) throws FileNotFoundException {
        game = new Game(this);
        game.play(stage);
    }
    private void resume_game(Stage stage)
    {

        Load_page load_page = new Load_page(this);
        load_page.display(stage);
    }
    @Override
    public void do_exit(Stage _stage)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(total_stars);
            out.close();
            file.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Platform.exit();
    }




    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image(new FileInputStream("./img/title.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(5);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        t = new Text();

        t.setText(String.valueOf(total_stars));
        t.setX(25);
        t.setY(60);
        t.setFont(Font.font(20));
        t.setFill(Color.WHITE);

        Image _image = new Image(new FileInputStream("./img/stars.png"));
        ImageView _imageView = new ImageView(_image);
        _imageView.setX(5);
        _imageView.setY(5);
        _imageView.setFitHeight(50);
        _imageView.setFitWidth(50);
        _imageView.setPreserveRatio(true);

        Image image1 = new Image(new FileInputStream("./img/new1.png"));
        Button button = new Button();
        button.setGraphic(new ImageView(image1));
        button.setTranslateX(60);
        button.setTranslateY(150);
        button.setOnAction(e -> {
            try {
                this.start_new(stage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        t.setText(String.valueOf(total_stars));

        Image image2 = new Image(new FileInputStream("./img/save1.png"));
        Button button1 = new Button();
        button1.setGraphic(new ImageView(image2));
        button1.setTranslateX(60);
        button1.setTranslateY(250);
        button1.setOnAction(e -> this.resume_game(stage));

        Image image3 = new Image(new FileInputStream("./img/exit1.png"));
        Button button2 = new Button();
        button2.setGraphic(new ImageView(image3));
        button2.setTranslateX(60);
        button2.setTranslateY(350);
        button2.setOnAction(e -> this.do_exit(stage));

        root = new Group(_imageView,imageView, button, button1, button2,t);

        stage.setTitle("Color Switch");
        scene1 = new Scene(root, 300, 500, Color.BLACK);
        stage.setScene(scene1);
        stage.show();


    }


}
