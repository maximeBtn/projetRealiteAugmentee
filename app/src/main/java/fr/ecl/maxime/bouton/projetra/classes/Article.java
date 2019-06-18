package fr.ecl.maxime.bouton.projetra.classes;

/**
 * Created by Max on 2019-06-15.
 */
public class Article {

    private String mNom;
    private double mPrix;
    private String mDescription;

    public Article(String nom, double prix) {
        mNom = nom;
        mPrix = prix;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

    public double getPrix() {
        return mPrix;
    }

    public void setPrix(double prix) {
        mPrix = prix;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
