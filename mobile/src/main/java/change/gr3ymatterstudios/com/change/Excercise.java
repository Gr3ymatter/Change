package change.gr3ymatterstudios.com.change;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Afzal on 12/7/14.
 */
public class Excercise {


    public String mTitle;
    public UUID mID;
    public Date mDate;
    private ArrayList<Integer> mReps;
    private ArrayList<Float> mWeight;
    private int mMaxWeight;


    public Excercise(String title)
    {
        mID = UUID.randomUUID();
        this.mTitle = title;
        mReps = new ArrayList<>();
        mWeight = new ArrayList<>();
    }

    public int getMaxWeight() {
        return mMaxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        mMaxWeight = maxWeight;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public ArrayList<Integer> getReps() {
        return mReps;
    }

    public int getRep(int setIndex) {return mReps.get(setIndex);}

    public void setRep(int setIndex, int repNo) {mReps.set(setIndex, repNo);}

    public void addRep(int repNo) {mReps.add(repNo);}

    public void setReps(ArrayList<Integer> reps) {
        mReps = reps;
    }

    public float getWeight(int setIndex) {return mWeight.get(setIndex);}

    public void setWeight(int setIndex, float weight) { mWeight.set(setIndex,weight);}

    public void addWeight(float weight){mWeight.add(weight);}

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
