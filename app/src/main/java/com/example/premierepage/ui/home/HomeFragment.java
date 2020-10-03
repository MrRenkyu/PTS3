package com.example.premierepage.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premierepage.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView recyclerView;

    String[] s1;
    String[] s2;
    String[] s3;
    String[] s4;
    int[] images = {R.drawable.broccoli, R.drawable.lime, R.drawable.cherry,
            R.drawable.corn, R.drawable.banana, R.drawable.peach, R.drawable.paprika, R.drawable.tomato};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceSates) {

        recyclerView = getView().findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.nom);
        s2 = getResources().getStringArray(R.array.prenom);
        s3 = getResources().getStringArray(R.array.classe);
        s4 = getResources().getStringArray(R.array.groupe);

        Adapteur adapteur = new Adapteur(this.getContext(), s1, s2, s3, s4, images);
        recyclerView.setAdapter(adapteur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}