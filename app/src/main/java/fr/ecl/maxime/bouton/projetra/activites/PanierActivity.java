package fr.ecl.maxime.bouton.projetra.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;
import fr.ecl.maxime.bouton.projetra.classes.Panier;
import fr.ecl.maxime.bouton.projetra.utils.ArticleAdapter;

public class PanierActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mArticleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        mRecyclerView= findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mArticleAdapter = new ArticleAdapter(Panier.mListeArticle, new ArticleAdapter.OnClickListener() {
            @Override
            public void onTextClicked(Article article) {

            }

            @Override
            public void onButtonClicked(Article article) {
                Panier.mListeArticle.remove(article);
                mArticleAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setAdapter(mArticleAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

}
