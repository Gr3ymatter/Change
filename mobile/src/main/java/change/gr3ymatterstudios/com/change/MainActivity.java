package change.gr3ymatterstudios.com.change;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> arrayAdapter;

        public PlaceholderFragment() {
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
}
