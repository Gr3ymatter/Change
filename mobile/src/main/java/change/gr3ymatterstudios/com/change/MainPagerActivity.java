package change.gr3ymatterstudios.com.change;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import change.gr3ymatterstudios.com.change.view.SlidingTabLayout;


public class MainPagerActivity extends FragmentActivity implements UserStateViewFragment.OnFragmentInteractionListener {


    ViewPager mViewPager;
    SlidingTabLayout mTablayout;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_tabsview);

        if (savedInstanceState == null) {

            FragmentManager fm = getSupportFragmentManager();
            mViewPager = (ViewPager)findViewById(R.id.viewPager);
            mViewPager.setAdapter(new MainViewPagerAdapter(fm));

            mTablayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
            mTablayout.setDistributeEvenly(true);
            mTablayout.setSelectedIndicatorColors(Color.CYAN);
            mViewPager.setCurrentItem(1);

            mTablayout.setViewPager(mViewPager);


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
        switch (item.getItemId()) {
            case R.id.action_add:
            {
                // Open New Activity
                Intent i = new Intent(this, RoutineEditActivity.class);
                startActivity(i);
            }
                return true;
            case R.id.action_calendar:
                Intent i = new Intent(this, CalendarViewActivity.class);
                startActivity(i);
                return true;
            case R.id.action_settings:
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }



    protected class MainViewPagerAdapter extends FragmentPagerAdapter{

        public MainViewPagerAdapter(FragmentManager fm){ super(fm);}

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {

                case 0:
                    return new UserStateViewFragment();

                case 1:
                    return new RoutineListViewFragment();

                default:
                    Fragment fragment = new DummySectionFragment();
                    Bundle args = new Bundle();
                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                    fragment.setArguments(args);
                    return fragment;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {

            switch(position){
                case 0:
                    return "Stats";
                case 1:
                    return "Workout";
                default:
                    return "Motivation";
            }

        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_dummy, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
