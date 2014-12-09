package change.gr3ymatterstudios.com.change;

/**
 * Created by Afzal on 12/7/14.
 */
public class Excercise {


    public String mTitle;
    public int mDate;
    private int[] mReps;
    private int mMaxWeight;


    public Excercise(String title, int repNo)
    {
        this.mTitle = title;
        mReps = new int[3];
    }

    public int getMaxWeight() {
        return mMaxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        mMaxWeight = maxWeight;
    }

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        mDate = date;
    }

    public int[] getReps() {
        return mReps;
    }

    public void setReps(int[] reps) {
        mReps = reps;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
