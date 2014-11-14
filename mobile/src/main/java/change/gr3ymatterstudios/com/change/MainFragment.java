package change.gr3ymatterstudios.com.change;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Afzal on 11/11/14.
 */
public class MainFragment extends Fragment {
    ExcerciseViewAdapter arrayAdapter;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<String> fakePriorities = new ArrayList<String>();

        fakePriorities.add("Have a secondary source of income");
        fakePriorities.add("Build Aesthetic Physique");
        fakePriorities.add("Become a Singer");
        fakePriorities.add("Be Socially Suave");
        fakePriorities.add("Be A Finisher");
        fakePriorities.add("Be a strong individual");
        fakePriorities.add("Learn how to Fight");
        fakePriorities.add("Become a Millionaire");
        fakePriorities.add("Be Happy");
        fakePriorities.add("Make your life mean something");


        ListView view = (ListView)rootView.findViewById(R.id.listView_priorities);
        view.setAdapter(new ExcerciseViewAdapter(fakePriorities));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), (parent.getItemAtPosition(position)).toString(), Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }


    class ExcerciseViewAdapter extends ArrayAdapter<String>{

        public ExcerciseViewAdapter(ArrayList<String> excercise){
            super(getActivity(),R.layout.excercise_row,R.id.excercise_title, excercise);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);

            Button button = (Button)row.findViewById(R.id.view_Details_button);


            return row;
        }
    }

}
