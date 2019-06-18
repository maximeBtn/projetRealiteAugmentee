package fr.ecl.maxime.bouton.projetra.classes;

import java.util.ArrayList;

/**
 * Created by Max on 2019-06-15.
 */
public class Panier {

    private ArrayList<Article> mListeArticle;
    private double mPrixTotal;

    public Panier() {
        mListeArticle = new ArrayList<>();
        mPrixTotal=0;
    }

    public ArrayList<Article> getListeArticle() {
        return mListeArticle;
    }

    public double getPrixTotal() {
        return mPrixTotal;
    }

    public void ajouterArticle(Article article){
        mListeArticle.add(article);
        mPrixTotal+=article.getPrix();
    }

    public void retirerArticle(int i){
        mPrixTotal-=mListeArticle.get(i).getPrix();
        mListeArticle.remove(i);
    }
}
