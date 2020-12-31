
package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class Game extends Application implements Serializable
{
    private transient Scene game_scene;
    private transient Pause_menu pause_menu;
    private transient Death_menu death_menu;
    private static transient MainPage mainPage;
    private transient StackPane pane;
    private transient Ball ball;
    private transient Text score_num;
    private transient Timeline timeline;
    private transient Group s_group;
    private transient Group b_group;
    private transient Inter_factory item_factory = new Item_factory();
    private transient Inter_factory obstacle_factory = new Obstacle_factory();
    transient ArrayList<Interactable> interactables = new ArrayList<>();
    private transient Stage stage_ref;
    private int score=0;
    private int color_code;
    private double ball_y_coords=0;
    private int nw=0;
    private double h=280;
    ArrayList<Integer> int_check = new ArrayList<>();
    private ArrayList<Double> y_coords = new ArrayList<>();

    public MainPage getMainPage()
    {
        return mainPage;
    }
    public Timeline getTimeline()
    {
        return timeline;
    }
    public StackPane getPane() { return pane;}
    public Ball getBall() { return ball; }
    public void re_ball()
    {
        ball = new Ball();
    }
    public void setBall_null() { ball = null; }
    public void g_action(Interactable interactable)
    {
        interactable.action(this);
    }
    public void inc_score()
    {
        score++;
    }
    public void set_score()
    {
        score_num.setText(String.valueOf(score));
    }
    public int get_score()
    {
        return score;
    }
    public double get_interactable_y_coords( int ind)
    {
        return interactables.get(ind).getGrp().getTranslateY();
    }
    public void insert_y_coords( double coords)
    {
        y_coords.add(coords);
    }

    public Game getGame() { return this; }
    public void loading_Game(MainPage _mainPage) throws FileNotFoundException {

        mainPage = _mainPage;
        interactables = new ArrayList<>();
        obstacle_factory = new Obstacle_factory();
        item_factory = new Item_factory();

        Image s_image = new Image(new FileInputStream("./img/stars.png"));
        ImageView s_imageView = new ImageView(s_image);
        s_imageView.setX(5);
        s_imageView.setY(5);
        s_imageView.setFitHeight(50);
        s_imageView.setFitWidth(50);
        s_imageView.setPreserveRatio(true);
        score_num = new Text();


        set_score();
        s_group = new Group(s_imageView, score_num);
        s_group.setTranslateX(-260);
        s_group.setTranslateY(-260);

        ball = new Ball();
        ball.setColor(color_code);
        ball.getGrp().setTranslateY(ball_y_coords);
        pane=new StackPane();

        pane.getChildren().addAll(s_group);
        //System.out.println("check1 "+pane.getChildren().size() + "check1 " + int_check.size());
        //System.out.println("int_check: "+ int_check.size() + "y_coords_size: "+ y_coords.size());
        for(int i=int_check.size()-1;i>0;i--)
        {

            if(int_check.get(i)<=6)
            {
                Obstacle o = (Obstacle) obstacle_factory.create_inter(int_check.get(i));
                interactables.add(o);
                pane.getChildren().add(0, o.getGrp());
                o.getGrp().setTranslateY(y_coords.get(i));
                if(int_check.get(i)==6)
                {
                    o.getGrp().setTranslateX(90);
                }

            }
            else
            {
                Item it = null;
                try {
                    it = (Item) item_factory.create_inter(int_check.get(i));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                interactables.add(it);
                pane.getChildren().add(0, it.getGrp());
                it.getGrp().setTranslateY(y_coords.get(i));
            }
        }
        //System.out.println("check2 "+pane.getChildren().size()+ "check2 " + int_check.size());
        y_coords.clear();
    }





    Game(MainPage _mainPage) throws FileNotFoundException {

        Obstacle.set_rot(1);
        Obstacle.set_rot_d(1);
        Text t = new Text();
        t.setText("Press 'A' to make the ball jump");
        t.setFont(Font.font(20));
        t.setFill(Color.WHITE);
        Group t_group = new Group(t);
        t_group.setTranslateX(1);
        t_group.setTranslateY(230);

        Image s_image = new Image(new FileInputStream("./img/stars.png"));
        ImageView s_imageView = new ImageView(s_image);
        s_imageView.setX(5);
        s_imageView.setY(5);
        s_imageView.setFitHeight(50);
        s_imageView.setFitWidth(50);
        s_imageView.setPreserveRatio(true);



        score_num = new Text();
        s_group = new Group(s_imageView, score_num);
        s_group.setTranslateX(-260);
        s_group.setTranslateY(-260);
        mainPage = _mainPage;
        pane=new StackPane();
        Loop loop = new Loop();
        Color_switcher color_switcher = new Color_switcher();
        Star star = new Star();
        interactables.add(star);                  // 7 star
        interactables.add(loop);                         // 0 loop
        interactables.add(color_switcher);       // 8 color_switcher


        ball = new Ball();
        ball.getGrp().setTranslateY(280);
        color_code= ball.getColor();
        int_check.add(7);
        int_check.add(0);
        int_check.add(8);
        loop.getGrp().setTranslateY(-220);
        star.getGrp().setTranslateY(-220);
        color_switcher.getGrp().setTranslateY(100);

        pane.getChildren().addAll(s_group, t_group);
        pane.getChildren().add(0,star.getGrp());

        pane.getChildren().add(0, loop.getGrp());                  //inserted at 0 to keep ball on the foreground
        pane.getChildren().add(0, color_switcher.getGrp());

    }
    protected int get_star()
    {
        return score;
    }
    private void open_p_menu(Stage _stage)
    {
        pause_menu.display(_stage);

        try{

            if(ball == null)
            {
                throw new BallNotFoundException();
            }
            ball.toggle_pause();

        }
        catch (BallNotFoundException ex)
        {
            System.out.println("Ball is dead");
        }
        Iterator<Interactable> iter = interactables.iterator();
        //int x =0;
        while(iter.hasNext())
        {
            Interactable temp = iter.next();
            if(!(temp instanceof Star || temp instanceof Color_switcher) && !temp.get_paused())
            {
                //System.out.println(interactables.get(x).paused);
                temp.set_paused(true);
                if(temp instanceof Obstacle )
                {
                    ((Obstacle) temp).toggle_pause();
                    //System.out.println(interactables.get(x).paused);
                }

            }
            //x++;
        }

    }

    public void play(Stage _stage)
    {
        stage_ref = _stage;
        pause_menu = new Pause_menu(this);
        death_menu = new Death_menu(this);
        score_num.setText(String.valueOf(score));
        score_num.setX(25);
        score_num.setY(60);
        score_num.setFont(Font.font(20));
        score_num.setFill(Color.WHITE);
        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected Scene getGame_scene()
    {
        return game_scene;
    }
    public void setupTimeline(){
        KeyFrame kf = new KeyFrame(Duration.millis(10), new TimeHandler());
        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public int get_color_code() {

        return color_code;
    }

    public void set_color_code(int color) {

        color_code=color;
    }

    public void set_ball_y_coords(double translateY) {

        ball_y_coords = translateY;
    }




    public class TimeHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){

            if(ball==null)
            {
                timeline.pause();
                death_menu.display(stage_ref);
            }

            if((int)h/500>nw)
            {
                //System.out.println("inner nw"+ nw);
                //System.out.println("int_check: "+ int_check.size() + "y_coords_size: "+ y_coords.size());
                boolean star_check= false;
                Random rand=new Random();
                int obstacle_checker = rand.nextInt(7);
                if(nw>0 && nw%5==0)
                {
                    star_check=true;
                }
                if(nw<=6){

                    obstacle_checker%=3;
                }
                else if(nw<=16)
                {
                    obstacle_checker%=5;
                }
                int_check.add(obstacle_checker);
                //System.out.println("obc"+obstacle_checker);
                Obstacle o = null;
                try {
                    o = (Obstacle) obstacle_factory.create_inter(obstacle_checker);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if(obstacle_checker==0 || obstacle_checker==1 || obstacle_checker==4)
                {
                    if (star_check)
                    {
                        SuperStar superStar = null;
                        try {
                            superStar = (SuperStar)item_factory.create_inter(9);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        interactables.add(superStar);
                        superStar.getGrp().setTranslateY(-500);
                        pane.getChildren().add(0, superStar.getGrp());
                        //y_coords.add(superStar.getGrp().getTranslateY());
                        int_check.add(9);
                        star_check=false;
                    }
                    else
                    {
                        Star star=null;
                        try {
                            star = (Star)item_factory.create_inter(7);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        interactables.add(star);
                        star.getGrp().setTranslateY(-500);
                        pane.getChildren().add(0, star.getGrp());
                        //y_coords.add(star.getGrp().getTranslateY());
                        int_check.add(7);
                    }
                }


                interactables.add(o);


                pane.getChildren().add(0, o.getGrp());


                o.getGrp().setTranslateY(-500);
                if(obstacle_checker==6)
                {
                    o.getGrp().setTranslateX(90);
                }
                nw++;

                if(nw-5>=0 && (nw%8==5 || nw%8==6))
                {

                    int_check.add(10);
                    Invinciball invinciball = null;
                    try {
                        invinciball = (Invinciball)item_factory.create_inter(10);;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    interactables.add(invinciball);
                    pane.getChildren().add(0, invinciball.getGrp());
                    invinciball.getGrp().setTranslateY(-750);

                }
                else
                {
                    Random r = new Random();
                    int chk = r.nextInt(10);
                    if(chk<=6)
                    {
                        int_check.add(8);
                        Color_switcher color_switcher = null;
                        try {
                            color_switcher = (Color_switcher) item_factory.create_inter(8);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        interactables.add(color_switcher);
                        pane.getChildren().add(0, color_switcher.getGrp());
                        color_switcher.getGrp().setTranslateY(-750);
                    }
                    else if(chk==7)
                    {
                        int_check.add(11);
                        Speed_ball speed_ball = null;
                        try {
                            speed_ball = (Speed_ball) item_factory.create_inter(11);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        interactables.add(speed_ball);
                        pane.getChildren().add(0, speed_ball.getGrp());
                        speed_ball.getGrp().setTranslateY(-750);
                    }
                    else if(chk==8)
                    {
                        int_check.add(12);
                        Slow_ball slow_ball = null;
                        try {
                            slow_ball = (Slow_ball) item_factory.create_inter(12);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        interactables.add(slow_ball);
                        pane.getChildren().add(0, slow_ball.getGrp());
                        slow_ball.getGrp().setTranslateY(-750);
                    }
                    else
                    {
                        int_check.add(13);
                        Reverse_ball reverse_ball = null;
                        try {
                            reverse_ball = (Reverse_ball) item_factory.create_inter(13);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        interactables.add(reverse_ball);
                        pane.getChildren().add(0, reverse_ball.getGrp());
                        reverse_ball.getGrp().setTranslateY(-750);
                    }

                }


            }


            Iterator<Interactable> iter = interactables.iterator();
            while(iter.hasNext())
            {
                g_action(iter.next());
                try
                {
                    if(ball==null)
                    {
                        throw new BallNotFoundException();
                    }
                    color_code= ball.getColor();
                }
                catch(BallNotFoundException ex)
                {
                    System.out.println("Ball is dead");
                }

            }

            try{

                if(ball==null)
                {
                    throw new BallNotFoundException();
                }
                if(ball.getGrp().getTranslateY()<0)
                {
                    for (int i = 0; i < pane.getChildren().size() - 1; i++)
                    {
                        if (pane.getChildren().get(i).equals(s_group) || pane.getChildren().get(i).equals(b_group))
                        {
                            continue;
                        }
                        pane.getChildren().get(i).setTranslateY(pane.getChildren().get(i).getTranslateY() + (0 - ball.getGrp().getTranslateY()));
                        //y_coords.set(pane.getChildren().size()-1-i, pane.getChildren().get(i).getTranslateY() );
                    }
                    ball.updateMin_height((0 - ball.getGrp().getTranslateY()));
                    h -= ball.getGrp().getTranslateY();
                    ball.getGrp().setTranslateY(0);
                }
            }
            catch (BallNotFoundException ex)
            {
                System.out.println("Ball is dead");
            }


            for(int j=interactables.size()-1;j>=0;j--)
            {
                if(pane.getChildren().get(j).getTranslateY()>500) {

                    interactables.set(interactables.size()-1-j,null);
                    int_check.set(interactables.size()-1-j,null);

                }
            }
            for(int j=interactables.size()-1;j>=0;j--) {

                if(pane.getChildren().get(j).getTranslateY()>500) {

                    pane.getChildren().remove(j);

                }
            }

            for(int j=interactables.size()-1; j>=0; j--)
            {
                if(interactables.get(j)==null)
                {
                    interactables.remove(j);
                    int_check.remove(j);
                }
            }


            if(pane.getChildren().get(pane.getChildren().size()-1).getTranslateY() > 300)
            {
                ball=null;
            }


        }
    }
    @Override
    public void start(Stage stage) throws Exception {

        /*if(y_coords.size()==int_check.size())
        {
            System.out.println("yes");
        }
        else
        {
            System.out.println("No");
        }*/


        Button p_button = new Button();
        Image p_image = new Image(new FileInputStream("./img/pause.png"));
        p_button.setGraphic(new ImageView(p_image));
        p_button.setTranslateX(400);
        p_button.setTranslateY(20);
        p_button.setOnAction(e -> this.open_p_menu(stage));


        b_group = new Group(p_button);
        b_group.setTranslateX(260);
        b_group.setTranslateY(-260);

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));



        pane.getChildren().addAll(b_group);
        pane.getChildren().addAll(ball.getGrp());

        game_scene = new Scene(pane, 600, 600, Color.BLACK);
        game_scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                //System.out.println("A key was pressed");
                try{
                    if(ball == null)
                    {
                        throw new BallNotFoundException();

                    }
                    ball.impulse();
                }
                catch (BallNotFoundException ex)
                {
                    System.out.println("Ball is dead");
                }

            }
        });
        /*System.out.println("second"+pane.getChildren().size());
        System.out.println("int check size "+ int_check.size());
        System.out.println(y_coords.size());
        System.out.println("height: "+ h);
        System.out.println("nw: "+nw);*/
        stage.setScene(game_scene);
        stage.show();
        this.setupTimeline();


    }
}