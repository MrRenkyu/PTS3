package com.example.pts3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupesFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Intent groupDetailIntent;
    private GroupesFragment actualGroupFragment;

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

        View rootView = inflater.inflate(R.layout.fragment_groupes, container, false);
        groupDetailIntent = new Intent(inflater.getContext(),GroupDetailActivity.class);
        searchView = rootView.findViewById(R.id.searchView);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.onActionViewExpanded();
            }
        });

        final ArrayList<BlockGroup> blockGroupsList = new ArrayList<BlockGroup>();

        ArrayList<Promo> allPromos = MainActivity.studentManager.getAllPromos();
        Log.e("size of promo list", allPromos.size()+" promo");
        for (Promo eachPromo : allPromos){
            blockGroupsList.add(new BlockGroup(1, eachPromo.getName(), eachPromo.getNumberStudent(), eachPromo.getStudentList()));
            for( GroupTD eachGrTd : eachPromo.getGroupsTD()){
                blockGroupsList.add(new BlockGroup(2, eachGrTd.getName(), eachGrTd.getNumberStudent()));
                for (GroupTP eachGrTp : eachGrTd.getGroupsTP()){
                    blockGroupsList.add(new BlockGroup(3, eachGrTp.getName(), eachGrTp.getNumberStudent()));
                    Log.e(eachGrTp.getName(), String.valueOf(eachGrTp.getNumberStudent()) );
                }
            }
        }

        actualGroupFragment = this;

        mRecyclerView = rootView.findViewById(R.id.rvBlockGroup);
        mAdapter = new BlockGroupAdapter(blockGroupsList);

        ((BlockGroupAdapter)mAdapter).setGroupesFragment(actualGroupFragment);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<BlockGroup> tempList = new ArrayList<>();
                for (int i = 0; i < blockGroupsList.size();i++){
                    if (blockGroupsList.get(i).getCategorie().contains(newText)){
                        tempList.add(blockGroupsList.get(i));
                    }
                }
                mAdapter = new BlockGroupAdapter(tempList);
                ((BlockGroupAdapter)mAdapter).setGroupesFragment(actualGroupFragment);
                mLayoutManager = new LinearLayoutManager(getContext());

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                return false;
            }
        });

        return rootView;
    }



    public void startGroupDetailActivity(){

        startActivity(groupDetailIntent);
    }


}