package sample;

import java.io.FileNotFoundException;

public class Obstacle_factory extends Inter_factory {
    @Override
    public Interactable create_inter(int inter) throws FileNotFoundException {

        switch (inter)
        {
            case 0:

                return new Loop();

            case 1:

                return new Rhombus();


            case 2:

                return new Twin_loop_h();


            case 3:

                return new Twin_loop_v();


            case 4:

                return new Tri_loop();


            case 5:

                return new Fan_loop();


            case 6:

                return new Fan();

        }
        return null;

    }
}
