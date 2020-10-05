package com.example.premierepage.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.premierepage.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    //moi
    private Button boutonGroupe;
    private Button boutonTrouver;
    private Button boutonModalite;
    private Switch switchQuestionHasard;

        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
            View root = inflater.inflate(R.layout.fragment_notifications, container, false);
            final TextView textView = root.findViewById(R.id.text_notifications);
            notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    //textView.setText(s);
                }
            });
            return root;

    }

    // instanciations des boutons
    public void onViewCreated(View view, @Nullable Bundle savedInstanceStates) {

        boutonGroupe = getView().findViewById(R.id.buttonGroupe);
        boutonGroupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirPageGroupe();
            }
        });

        boutonTrouver = getView().findViewById(R.id.buttonTrouver);
        boutonTrouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirPageTrouver();
            }
        });

        boutonModalite = getView().findViewById(R.id.buttonModalite);
        boutonModalite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirPageModalite();
            }
        });

    }

    // méthodes ouvrir pages
    public void ouvrirPageGroupe() {

        Intent intent = new Intent(this.getContext(), PageReglageGroupe.class);
        startActivity(intent);
    }


    public void ouvrirPageTrouver() {

        Intent intent = new Intent(this.getContext(), PageReglageTrouver.class);
        startActivity(intent);

    }

    public void ouvrirPageModalite() {

        Intent intent = new Intent(this.getContext(), PageReglageModalite.class);
        startActivity(intent);

    }
}