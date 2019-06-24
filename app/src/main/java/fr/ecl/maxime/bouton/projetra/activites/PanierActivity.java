package fr.ecl.maxime.bouton.projetra.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;
import fr.ecl.maxime.bouton.projetra.classes.Panier;
import fr.ecl.maxime.bouton.projetra.utils.ArticleAdapter;


//Gère l'affichage de tous les articles scannés, avec possibilité de les supprimer
public class PanierActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mArticleAdapter;
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        mRecyclerView= findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mArticleAdapter = new ArticleAdapter(Panier.mListeArticle, new ArticleAdapter.OnClickListener() {
            @Override
            public void onTextClicked(Article article) {
                // On passe la main à la DetailsActivity si on clique sur le nom de l'article
                Intent i = new Intent(PanierActivity.this, DetailsActivity.class);
                i.putExtra(POSITION, Panier.mListeArticle.indexOf(article));
                startActivity(i);
            }

            @Override
            public void onButtonClicked(Article article) {
                // On retire l'article de la liste si on clique sur le bouton suppression associé
                Panier.mListeArticle.remove(article);
                mArticleAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setAdapter(mArticleAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

}
