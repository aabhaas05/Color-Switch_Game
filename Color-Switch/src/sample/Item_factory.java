package sample;

import java.io.FileNotFoundException;

public class Item_factory extends Inter_factory {
    @Override
    public Interactable create_inter(int inter) throws FileNotFoundException {

        //star 7 super_star 9 color_switcher 8   invincible ball 10  speed_ball 11 slow_ball 12 reverse_ball 13
        switch (inter)
        {
            case 7:

                return new Star();

            case 8:

                return new Color_switcher();


            case 9:

                return new SuperStar();


            case 10:

                return new Invinciball();


            case 11:

                return new Speed_ball();


            case 12:

                return new Slow_ball();


            case 13:

                return new Reverse_ball();

        }
        return null;

    }
}
