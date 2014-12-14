package change.gr3ymatterstudios.com.change;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Afzal on 12/7/14.
 */
public class Routine {

    private String mTitle;
    private UUID mID;
    private int mDate;
    private ArrayList<Excercise> Excercises;


    public Routine(String title){
        mTitle = title;
        mID = UUID.randomUUID();
        Excercises = new ArrayList<>();
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public void addExcercise(Excercise excercise){
       Excercises.add(excercise);
    }
    public ArrayList<Excercise> getExcercise(){return Excercises;}
    public Excercise getExcercise(int index){return Excercises.get(index);}


}
