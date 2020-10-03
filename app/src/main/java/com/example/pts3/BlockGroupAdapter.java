package com.example.pts3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BlockGroupAdapter extends RecyclerView.Adapter<BlockGroupAdapter.Viewholder> {

    private ArrayList<BlockGroup> listBlockGroup;

    public BlockGroupAdapter(ArrayList<BlockGroup> listBlockGroup) {
        this.listBlockGroup = listBlockGroup;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView mGroupName;
        public TextView mStudentNumber_tv;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            mGroupName = (TextView) itemView.findViewById(R.id.groupName_tv);
            mStudentNumber_tv = (TextView) itemView.findViewById(R.id.studentNumber_tv);
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
        BlockGroup blockGroup = listBlockGroup.get(position);

        TextView textViewGroupName = holder.mGroupName;
        textViewGroupName.setText(blockGroup.getCategorie());

        TextView textViewNumStudent = holder.mStudentNumber_tv;
        textViewNumStudent.setText("("+ blockGroup.getNbEleve()+"etudiants)");
    }

    @Override
    public int getItemCount() {
        return listBlockGroup.size();
    }
}
