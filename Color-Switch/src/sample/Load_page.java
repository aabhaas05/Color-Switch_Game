package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

class Load_page extends Application implements Page{



    private String[] g_file = {"game_file1.ser", "game_file2.ser", "game_file3.ser", "game_file4.ser"};
    private Game[] game;
    private final MainPage mp;

    Load_page(MainPage _mainPage)
    {
        mp= _mainPage;
        game = new Game[4];
    }
    public void display(Stage _stage)
    {

        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void load_game(Stage _stage, int ind) throws FileNotFoundException {

        FileInputStream file;
        ObjectInputStream in;
        try
        {
            file = new FileInputStream(g_file[ind]);
            in = new ObjectInputStream(file);
            game[ind] = (Game) in.readObject();
            in.close();
            file.close();
        }
        catch(IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        try{
            if(game[ind]==null)
            {
                throw new SavedGameNotFoundException();
            }
            game[ind].loading_Game(mp);
            game[ind].play(_stage);
        }
        catch(SavedGameNotFoundException ex)
        {
            System.out.println("Saved Game "+ (ind+1)+" Not Found");
            ex.printStackTrace();
        }




    }

    @Override
    public void do_exit(Stage _stage) {

        _stage.setScene(MainPage.getScene1());
    }

    @Override
    public void start(Stage stage) throws Exception {

        Image image4 = new Image(new FileInputStream("./img/select.png"));
        ImageView imageView1 = new ImageView(image4);
        imageView1.setX(50);
        imageView1.setY(5);
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);
        imageView1.setPreserveRatio(true);

        Image image5 = new Image(new FileInputStream("./img/game1.png"));
        Button button3 = new Button();

        button3.setGraphic(new ImageView(image5));
        //button3.setContentDisplay(ContentDisplay.LEFT);
        button3.setTranslateX(20);
        button3.setTranslateY(100);
        button3.setOnAction(e -> {
            try {
                this.load_game(stage,0);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Image image6 = new Image(new FileInputStream("./img/game2.png"));
        Button button4 = new Button();
        button4.setGraphic(new ImageView(image6));
        button4.setTranslateX(20);
        button4.setTranslateY(180);
        button4.setOnAction(e -> {
            try {
                this.load_game(stage,1);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Image image7 = new Image(new FileInputStream("./img/game3.png"));
        Button button5 = new Button();
        button5.setGraphic(new ImageView(image7));
        button5.setTranslateX(20);
        button5.setTranslateY(260);
        button5.setOnAction(e -> {
            try {
                this.load_game(stage,2);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Image image8 = new Image(new FileInputStream("./img/game4.png"));
        Button button6 = new Button();
        button6.setGraphic(new ImageView(image8));
        button6.setTranslateX(20);
        button6.setTranslateY(340);
        button6.setOnAction(e -> {
            try {
                this.load_game(stage,3);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


        Image image9 = new Image(new FileInputStream("./img/home1.png"));
        Button button7 = new Button();
        button7.setGraphic(new ImageView(image9));
        button7.setTranslateX(65);
        button7.setTranslateY(420);
        button7.setOnAction(e-> this.do_exit(stage));
        Group root1 = new Group(imageView1, button3, button4, button5, button6, button7);
        Scene scene2 = new Scene(root1, 300, 500, Color.BLACK);
        stage.setScene(scene2);
        stage.show();
    }
}
