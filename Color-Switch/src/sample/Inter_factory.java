package sample;

import java.io.FileNotFoundException;

public abstract class Inter_factory {

    public abstract Interactable create_inter(int inter) throws FileNotFoundException;

}
