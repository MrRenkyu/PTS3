package com.example.pts3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GroupesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupesFragment newInstance(String param1, String param2) {
        GroupesFragment fragment = new GroupesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("On create view","On create pfff fragment initialise");
        ArrayList<BlockGroup> blockGroupsList = new ArrayList<BlockGroup>();
        blockGroupsList.add(new BlockGroup(0,"INFO1",40));
        blockGroupsList.add(new BlockGroup(1,"TD11",20));
        blockGroupsList.add(new BlockGroup(2,"TPGA",10));

        View view = inflater.inflate(R.layout.fragment_groupes, container, false);

        RecyclerView rvGroup = (RecyclerView) view.findViewById(R.id.rvBlockGroup);

        // Create adapter passing in the sample user data
        BlockGroupAdapter adapter = new BlockGroupAdapter(blockGroupsList);
        // Attach the adapter to the recyclerview to populate items
        rvGroup.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvGroup.setLayoutManager(linearLayoutManager);
        // That's all!

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_groupes, container, false);
    }
}