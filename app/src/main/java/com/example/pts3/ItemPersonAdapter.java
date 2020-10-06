package com.example.pts3;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class ItemPersonAdapter extends RecyclerView.Adapter<ItemPersonAdapter.ItemPersonViewHolder> {
    private ArrayList<Student> mItemPersonList;
    public OnItemClickListener mListener;
    public ItemPersonViewHolder itemViewHolder;

    public interface OnItemClickListener {
        void onAddClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public static class ItemPersonViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView firstName_tv;
        public ImageView mImageView2;
        public TextView lastName_tv;
        public TextView promo_tv;
        public TextView grTd_tv;
        public TextView grTp_tv;


        public ItemPersonViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagePerson);
            firstName_tv = itemView.findViewById(R.id.firstName_tv);
            lastName_tv = itemView.findViewById(R.id.lastName_tv);
            promo_tv = itemView.findViewById(R.id.promo_tv);
            grTd_tv = itemView.findViewById(R.id.grTd_tv);
            grTp_tv = itemView.findViewById(R.id.grTp_tv);
            mImageView2 = itemView.findViewById(R.id.imageView19);

            mImageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        itemViewHolder = new ItemPersonViewHolder(v, mListener);
        return itemViewHolder;
}

    public ItemPersonAdapter(ArrayList<Student> itemlist) {
        mItemPersonList = itemlist;
    }


    @Override
    public void onBindViewHolder(@NonNull ItemPersonViewHolder holder, int position) {
        Student currentItem = mItemPersonList.get(position);

        currentItem.setItemPersonViewHolder(holder);

        holder.mImageView.setImageDrawable(currentItem.getPhoto().getPicture());
        holder.firstName_tv.setText((currentItem.getFirstName()));
        holder.lastName_tv.setText(currentItem.getLastName());
        holder.promo_tv.setText(currentItem.getGroupTP().getGroupTD().getPromo().getName());
        holder.grTd_tv.setText(currentItem.getGroupTP().getGroupTD().getName());
        holder.grTp_tv.setText(currentItem.getGroupTP().getName());
    }

    @Override
    public int getItemCount() {
        return mItemPersonList.size();
    }


    public void updateImage(int pos) {
        Drawable pictureOfStudent = mItemPersonList.get(pos).getPhoto().getPicture();
        ImageView viewOfPicture = mItemPersonList.get(pos).getItemPersonViewHolder().mImageView;
        if(pictureOfStudent != null) {
            viewOfPicture.setImageDrawable(pictureOfStudent);
        }
        Log.e("update image", "picture of student n° " + mItemPersonList.get(pos).getFirstName());
    }



}





