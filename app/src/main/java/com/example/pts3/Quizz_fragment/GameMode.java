package com.example.pts3.Quizz_fragment;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public enum GameMode {
    FACES4_NAME1("4 visages, 1 nom"),FACE1_NAME4("1 visage, 4 noms");

    private String explicate;

    GameMode(String value) {
        this.explicate = value;
    }

    public String getValue(){
        return this.explicate;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getValue();
    }
}
