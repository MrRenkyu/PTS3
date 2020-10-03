package com.example.premierepage.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premierepage.R;

public class Adapteur extends RecyclerView.Adapter<Adapteur.ViewHolder> {

    String[] data1;
    String[] data2;
    String[] data3;
    String[] data4;
    int[] images;
    Context context;

    public Adapteur(Context ct, String[] s1, String[] s2, String[] s3, String[] s4, int[] img) {
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        data4 = s4;

        images = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ligne, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text1.setText(data1[position]);
        holder.text2.setText(data2[position]);
        holder.text3.setText(data3[position]);
        holder.text4.setText(data4[position]);
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return data2.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2, text3, text4;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            text3 = itemView.findViewById(R.id.text3);
            text4 = itemView.findViewById(R.id.text4);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
