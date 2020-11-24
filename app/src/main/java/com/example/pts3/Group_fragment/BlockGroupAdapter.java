package com.example.pts3.Group_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pts3.R;

import java.util.ArrayList;

public class BlockGroupAdapter extends RecyclerView.Adapter<BlockGroupAdapter.Viewholder> {

    private ArrayList<BlockGroup> listBlockGroup;
    private GroupesFragment groupesFragment;

    public BlockGroupAdapter(ArrayList<BlockGroup> listBlockGroup) {
        this.listBlockGroup = listBlockGroup;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView mGroupName;
        public TextView mStudentNumber_tv;
        public ImageView mArrow_IMV;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            mGroupName = (TextView) itemView.findViewById(R.id.groupName_tv);
            mStudentNumber_tv = (TextView) itemView.findViewById(R.id.studentNumber_tv);
            mArrow_IMV = (ImageView) itemView.findViewById(R.id.fleche);
        }
    }

    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View blockGroupView = inflater.inflate(R.layout.layout_groupblock, parent, false);

        // Return a new holder instance
        Viewholder viewHolder = new Viewholder(blockGroupView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final BlockGroup blockGroup = listBlockGroup.get(position);

        TextView textViewGroupName = holder.mGroupName;
        holder.mGroupName.setText(blockGroup.getCategorie());

        TextView textViewNumStudent = holder.mStudentNumber_tv;
        textViewNumStudent.setText("("+ blockGroup.getNbEleve()+" étudiants)");

        int tabtextViewGroupName = blockGroup.getNbTab();
        textViewGroupName.setX(50*tabtextViewGroupName+25);

        holder.mArrow_IMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                groupesFragment.startGroupDetailActivity(blockGroup.getStudentArrayList(),blockGroup.getCategorie());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBlockGroup.size();
    }


    public void setGroupesFragment(GroupesFragment groupesFragment) {
        this.groupesFragment = groupesFragment;
    }
}
