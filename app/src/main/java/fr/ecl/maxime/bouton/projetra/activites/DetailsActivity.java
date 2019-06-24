package fr.ecl.maxime.bouton.projetra.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;
import fr.ecl.maxime.bouton.projetra.classes.Panier;

public class DetailsActivity extends AppCompatActivity {

    private TextView txt_nom, txt_ingredients, txt_origine, txt_label, txt_indice, txt_quantite, txt_marque, txt_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_nom = findViewById(R.id.txt_nom);
        txt_ingredients = findViewById(R.id.ingredients);
        txt_origine = findViewById(R.id.origin);
        txt_label = findViewById(R.id.labels);
        txt_indice = findViewById(R.id.indice);
        txt_quantite = findViewById(R.id.quantite);
        txt_marque = findViewById(R.id.marque);
        txt_categories = findViewById(R.id.categories);

        Intent i = getIntent();
        int n = i.getIntExtra(PanierActivity.POSITION, 0);

        Article article = Panier.mListeArticle.get(n);
        txt_nom.setText(article.getProduct().getProductName());
        txt_ingredients.setText(article.getProduct().getIngredientsText());
        txt_origine.setText(article.getProduct().getOrigins());
        txt_label.setText(article.getProduct().getLabels());
        txt_indice.setText(article.getProduct().getNutritionGrades().toUpperCase());
        txt_quantite.setText(article.getProduct().getQuantity());
        txt_marque.setText(article.getProduct().getBrands());
        txt_categories.setText(article.getProduct().getCategories());
    }
}
