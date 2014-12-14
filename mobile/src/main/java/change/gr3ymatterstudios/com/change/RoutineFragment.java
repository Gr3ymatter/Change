package change.gr3ymatterstudios.com.change;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Afzal on 11/11/14.
 */
public class RoutineFragment extends Fragment {

    private ArrayList<Routine> routines;

    public RoutineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        routines = new ArrayList<>();

        routines.add(routine1);
        routines.add(routine2);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

//        ArrayList<String> fakeRoutines = new ArrayList<String>();
//
//        fakeRoutines.add("Upper Body Day 1");
//        fakeRoutines.add("Lower Body Day 2");
//        fakeRoutines.add("Upper Body Day 3");
//        fakeRoutines.add("Lower Body Day 4");
//        fakeRoutines.add("blah blah blah");
//        fakeRoutines.add("Be a strong individual");
//        fakeRoutines.add("Learn how to Fight");
//        fakeRoutines.add("Become a Millionaire");
//        fakeRoutines.add("Be Happy");
//        fakeRoutines.add("Make your life mean something");


        ListView view = (ListView)rootView.findViewById(R.id.listView_routines);
        view.setAdapter(new ExcerciseViewAdapter(routines));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), (parent.getItemAtPosition(position)).toString(), Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }


    class ExcerciseViewAdapter extends ArrayAdapter<Routine>{

        public ExcerciseViewAdapter(ArrayList<Routine> excercise){
            super(getActivity(),R.layout.routine_list_row,R.id.routine_name_textView, excercise);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);

            //Button button = (Button)row.findViewById(R.id.view_Details_button);
            TextView daysCompleted = (TextView)row.findViewById(R.id.daysCompleted_textView);
            daysCompleted.setText("Days Completed: 4");

            ImageView startImage = (ImageView)row.findViewById(R.id.start_routine_imageView);


            return row;
        }
    }

}
