package fr.ecl.maxime.bouton.projetra.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;
import fr.ecl.maxime.bouton.projetra.classes.Panier;
import fr.ecl.maxime.bouton.projetra.utils.ArticleAdapter;

public class PanierActivity extends AppCompatActivity {

    Panier monPanier;
    RecyclerView mRecyclerView;
    TextView txt_prixTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        monPanier= new Panier();
        monPanier.ajouterArticle(new Article("nouille", 1));
        monPanier.ajouterArticle(new Article("riz", 2));
        monPanier.ajouterArticle(new Article("poisson", 5));
        monPanier.ajouterArticle(new Article("viande", 8));

        mRecyclerView= findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArticleAdapter itemAdapter = new ArticleAdapter(monPanier);
        mRecyclerView.setAdapter(itemAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        txt_prixTotal=findViewById(R.id.txt_affichage_prix);
        txt_prixTotal.setText(Double.toString(monPanier.getPrixTotal()));
    }

}
