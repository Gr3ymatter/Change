package change.gr3ymatterstudios.com.change;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Afzal on 11/11/14.
 */
public class MainFragment extends Fragment {
    ArrayAdapter<String> arrayAdapter;

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

        arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.priority_item_textview, R.id.item_textview, fakePriorities);
        ListView view = (ListView)rootView.findViewById(R.id.listView_priorities);
        view.setAdapter(arrayAdapter);
        return rootView;
    }

}
