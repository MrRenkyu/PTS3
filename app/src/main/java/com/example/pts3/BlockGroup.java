package com.example.pts3;

public class BlockGroup {
    private String categorie;
    private int nbTab;
    private int nbEleve;

    public BlockGroup(int nbTab, String categorie, int nbEleve) {
        this.nbTab = nbTab;
        this.categorie = categorie;
        this.nbEleve = nbEleve;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbTab() {
        return nbTab;
    }

    public void setNbTab(int nbTab) {
        this.nbTab = nbTab;
    }

    public int getNbEleve() {
        return nbEleve;
    }

    public void setNbEleve(int nbEleve) {
        this.nbEleve = nbEleve;
    }
}
