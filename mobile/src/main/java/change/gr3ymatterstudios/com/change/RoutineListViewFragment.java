package change.gr3ymatterstudios.com.change;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.UUID;

/**
 * Created by Afzal on 11/11/14.
 * Routine ListView Fragment of the application. This will be hosted in the main activity AFTER sign in
 * This should be present in the middle of the swipe view.
 */
public class RoutineListViewFragment extends android.support.v4.app.Fragment {

   static RoutineManager mRoutineManager;
   public static String EXTRA_ROUTINE_ID = "com.gr3ymatter.change.ROUTINE_ID";
    public static String ROUTINE_LISTVIEW_TAG = "RoutineListViewFragment";

    public RoutineListViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mRoutineManager == null) {
            mRoutineManager = new RoutineManager();
            Log.d(ROUTINE_LISTVIEW_TAG, "mRoutineManager Instantiated");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listview, container, false);

        mRoutineManager.createMockRoutineExcercise();
        mRoutineManager.createFakeUser();

        ListView view = (ListView)rootView.findViewById(R.id.listView_routines);
        view.setAdapter(new RoutineViewAdapter(mRoutineManager.getRoutines()));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), (parent.getItemAtPosition(position)).toString(), Toast.LENGTH_LONG).show();
                UUID uuid = ((Routine)parent.getItemAtPosition(position)).getID();
                Intent i = new Intent(getActivity(), RoutineDetailActivity.class).putExtra(EXTRA_ROUTINE_ID, uuid);
                startActivity(i);
            }
        });


        return rootView;
    }


    /*
    Adapter class to bind views with information in each row of the Routine List
     */
    class RoutineViewAdapter extends ArrayAdapter<Routine>{

        public RoutineViewAdapter(ArrayList<Routine> excercise){
            super(getActivity(),R.layout.routine_list_row,R.id.routine_name_textView, excercise);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);

            //Button button = (Button)row.findViewById(R.id.view_Details_button);
            TextView daysCompleted = (TextView)row.findViewById(R.id.daysCompleted_textView);
            daysCompleted.setText("Days Completed: 4");
            ImageView startImage = (ImageView)row.findViewById(R.id.start_routine_imageView);


            startImage.setOnClickListener(new View.OnClickListener() {
                Routine routine = (Routine)getItem(position);
                UUID uuid = (UUID)routine.getID();
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), StartWorkoutActivity.class).putExtra(EXTRA_ROUTINE_ID, uuid);
                    startActivity(i);
                }
            });

            return row;
        }
    }

}
