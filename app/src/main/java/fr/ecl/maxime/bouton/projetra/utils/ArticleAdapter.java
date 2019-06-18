package fr.ecl.maxime.bouton.projetra.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;
import fr.ecl.maxime.bouton.projetra.classes.Panier;

/**
 * Created by Max on 2019-06-15.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ItemViewHolder> {

    private final ArrayList<Article> mArticles;

    public ArticleAdapter(Panier panier) {
        Panier panier1 = panier;
        mArticles = panier.getListeArticle();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_article, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Article article = mArticles.get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private final TextView txt_nom;
        private final TextView txt_prix;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nom = itemView.findViewById(R.id.nom_article);
            txt_prix= itemView.findViewById(R.id.prix_article);
        }

        public void bind(Article article){
            txt_nom.setText(article.getNom());
            txt_prix.setText(Double.toString(article.getPrix()));
        }
    }
}
