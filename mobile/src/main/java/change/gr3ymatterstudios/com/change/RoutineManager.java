package change.gr3ymatterstudios.com.change;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

/**
 *
 * This class will manage the interaction of Routines and Excercises.
 * All communication of the app such as requests for updating, editing, adding and deleting either
 * of these two objects will occur through this class.
 *
 * Created by Afzal on 12/13/14.
 */
public class RoutineManager {

   static ArrayList<Excercise> mExcercises;
   static ArrayList<Routine> mRoutines;
    static User mUser;

    public RoutineManager(){

        mExcercises = new ArrayList<>();
        mRoutines = new ArrayList<>();

    }

    public void createMockRoutineExcercise(){

        if(!mRoutines.isEmpty() || !mExcercises.isEmpty())
            return;

        Random r = new Random();

        Routine routine1 = new Routine("Upper Body");
        Routine routine2 = new Routine("Lower Body");

        routine1.addExcercise(new Excercise("Bench Press"));
        routine1.addExcercise(new Excercise("Pull Ups"));
        routine1.addExcercise(new Excercise("Bent Over Rows"));

        routine2.addExcercise(new Excercise("Squats"));
        routine2.addExcercise(new Excercise("DeadLifts"));
        routine2.addExcercise(new Excercise("Lunges"));

        for (int i = 0; i<routine1.getExcercise().size(); i++) {
            routine1.getExcercise().get(i).setDate(new Date());
            for (int x = 0; x < 3; x++) {
                routine1.getExcercise().get(i).addRep(r.nextInt(15 - 6) + 6);
                routine2.getExcercise().get(i).addRep(r.nextInt(15-6) +6);
                routine1.getExcercise().get(i).addWeight(r.nextFloat()*(200-25) + 25);
                routine2.getExcercise().get(i).addWeight(r.nextFloat()*(200-25) + 25);
            }
        }
        Log.d("RoutineManager", "Routine Manager is creating mock routines");

        mRoutines.add(routine1);
        mRoutines.add(routine2);
    }

    public ArrayList<Excercise> getExcercises() {
        return mExcercises;
    }

    public void setExcercises(ArrayList<Excercise> excercises) {
        mExcercises = excercises;
    }

    public ArrayList<Routine> getRoutines() {
        return mRoutines;
    }

    public void setRoutines(ArrayList<Routine> routines) {
        mRoutines = routines;
    }

    public Routine getRoutine(UUID routineID){

        for(Routine routine : mRoutines){
            if(routine.getID().equals(routineID))
                return routine;
        }

        return null;
    }


    public void createFakeUser(){
        mUser = new User();
        mUser.setName("Sal");
        mUser.setAge(26);
        mUser.setGender("Male");
        mUser.setGoalWeight(210);
        mUser.setHeight(76);
        mUser.setWeight(200);
        mUser.setDateOfBirth((new GregorianCalendar(1986, 26, 10)).getTime());
    }

}
