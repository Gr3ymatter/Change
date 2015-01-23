package change.gr3ymatterstudios.com.change;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RoutineDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RoutineDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */



public class RoutineDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String EXTRA_ROUTINE_ID = "ROUTINE_ID";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private UUID routineID;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ReminderDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoutineDetailFragment newInstance(UUID routineID) {
        RoutineDetailFragment fragment = new RoutineDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ROUTINE_ID, routineID);
        fragment.setArguments(args);
        return fragment;
    }
    public RoutineDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            routineID = (UUID)getArguments().getSerializable(EXTRA_ROUTINE_ID);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Routine routine = RoutineListViewFragment.mRoutineManager.getRoutine(routineID);

        getActivity().setTitle(routine.toString());

        View rootView = inflater.inflate(R.layout.fragment_listview, container, false);

        final ListView excerciseList = (ListView) rootView.findViewById(R.id.listView_routines);
        excerciseList.setAdapter(new ExcerciseListViewAdapter(routine.getExcercise()));
        excerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  excerciseId = ((Excercise)parent.getItemAtPosition(position)).getTitle();
                Intent i = new Intent(getActivity(), StatsViewActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//
//    class ExpandableListViewAdapter extends BaseExpandableListAdapter{
//
//        ArrayList<Excercise> mExcercises;
//        private Activity mActivity;
//        private LayoutInflater mLayoutInflater;
//
//        public ExpandableListViewAdapter(LayoutInflater inflater, ArrayList<Excercise> excercises) {
//            this.mExcercises = excercises;
//            mLayoutInflater = inflater;
//        }
//
//
//        @Override
//        public int getGroupTypeCount() {
//            return super.getGroupTypeCount();
//        }
//
//        @Override
//        public int getChildType(int groupPosition, int childPosition) {
//            return super.getChildType(groupPosition, childPosition);
//        }
//
//        @Override
//        public int getChildTypeCount() {
//            return super.getChildTypeCount();
//        }
//
//        @Override
//        public int getGroupType(int groupPosition) {
//            return super.getGroupType(groupPosition);
//        }
//
//        @Override
//        public int getGroupCount() {
//            return mExcercises.size();
//        }
//
//        @Override
//        public int getChildrenCount(int groupPosition) {
//            return mExcercises.get(groupPosition).getReps().size();
//        }
//
//        @Override
//        public Object getGroup(int groupPosition) {
//            return null;
//        }
//
//        @Override
//        public Object getChild(int groupPosition, int childPosition) {
//           ArrayList<Integer> reps = mExcercises.get(groupPosition).getReps();
//           String childTextView = "Set#" + childPosition + 1 + " with " + reps.get(childPosition) + " reps @ " + mExcercises.get(groupPosition).getWeight(childPosition);
//            return childTextView;
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return 0;
//        }
//
//        @Override
//        public long getChildId(int groupPosition, int childPosition) {
//            return childPosition;
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//            String excerciseTitle = mExcercises.get(groupPosition).getTitle();
//                convertView = mLayoutInflater.inflate(R.layout.routine_detail_row, null);
//
//
//            TextView exTitle = (TextView)convertView.findViewById(R.id.excercise_title_textView);
//            exTitle.setText(excerciseTitle);
//            return convertView;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//
//            String childString = (String)getChild(groupPosition, childPosition);
//
//                convertView = mLayoutInflater.inflate(R.layout.fragment_routine_set_info_list,null);
//
//            TextView infoView = (TextView)convertView.findViewById(R.id.info_textView);
//            infoView.setText(childString);
//
//            return convertView;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return false;
//        }
//    }


    class ExcerciseListViewAdapter extends ArrayAdapter<Excercise>
    {
        public ExcerciseListViewAdapter(ArrayList<Excercise> excercises){
            super(getActivity(),R.layout.routine_detail_row,R.id.excercise_title_textView, excercises);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            final Excercise excercise = getItem(position);
            LinearLayout excerciseDetailContainer = (LinearLayout)row.findViewById(R.id.linearLayout_exercise_detail);
            if(excercise.getReps().size() != 0){
                String size = Integer.toString(excercise.getReps().size());
                ArrayList reps = excercise.getReps();
                for(int i = 0; i<excercise.getReps().size(); i++){
                    TextView setInfo = new TextView(getContext());
                    int setNo = 1+i;
                    setInfo.setGravity(Gravity.CENTER);
                    setInfo.setText("Set #" + setNo + ": " + excercise.getReps().get(i) + " reps @ " + excercise.getWeight(i));
                    excerciseDetailContainer.addView(setInfo, i);
                }



               ImageView stats = (ImageView) row.findViewById(R.id.excercise_stats_imageView);

                stats.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Clicked Image View of " + excercise.getTitle() , Toast.LENGTH_SHORT).show();

                    }
                });

            }
            else
            {
                TextView nodetails = new TextView(getContext());
            }
            return row;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
