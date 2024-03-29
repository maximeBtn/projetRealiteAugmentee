package fr.ecl.maxime.bouton.projetra.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;

/**
 * Created by Max on 2019-06-15.
 */

//Adapter du recyclerview de PanierActivity
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ItemViewHolder> {

    private final ArrayList<Article> mArticles;
    private final OnClickListener mListener;

    //Interface de click selon se la vue cliquée (bouton suppression ou nom article)
    public interface OnClickListener{

        void onTextClicked(Article article);
        void onButtonClicked(Article article);

    }

    public ArticleAdapter(ArrayList<Article> articles, OnClickListener listener) {
        mArticles = articles;
        mListener = listener;
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
        holder.bind(article, mListener);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }


    public Article getArticleFromName(String name){
        for (Article a : mArticles){
            if (a.getProduct().getProductName().equalsIgnoreCase(name)){
                return a;
            }
        }
        return null;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private final TextView txt_nom;
        private final Button btn_delete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nom = itemView.findViewById(R.id.nom_article);
            btn_delete = itemView.findViewById(R.id.btn_suppression);
        }

        public void bind(final Article article, final OnClickListener listener){
            txt_nom.setText(article.getProduct().getProductName());
            txt_nom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTextClicked(article);
                }
            });
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonClicked(article);
                }
            });
        }
    }
}
