package fr.ecl.maxime.bouton.projetra.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;
import com.vikramezhil.droidspeech.OnDSPermissionsListener;

import java.util.ArrayList;
import java.util.List;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;
import fr.ecl.maxime.bouton.projetra.classes.Panier;
import fr.ecl.maxime.bouton.projetra.utils.ServiceFactory;
import fr.ecl.maxime.bouton.projetra.utils.Services;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    public static final String BASE_URL = "https://fr.openfoodfacts.org/api/v0/produit/";
    private ZXingScannerView mScannerView;
    private FrameLayout mCameraView;
    private TextView message;
    private ImageButton mBoutonPanier;
    private Call<Article> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Initialisation du panier au lancement de l'application comme une liste vide d'articles
        Panier.mListeArticle = new ArrayList<>(0);
        message = findViewById(R.id.text1);
        mCameraView = findViewById(R.id.camera_preview);
        mBoutonPanier = findViewById(R.id.btn_panier);

        // Si on clique sur le bouton panier, on passe à l'activité PanierActivity
        mBoutonPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScanActivity.this, PanierActivity.class);
                startActivity(i);
                mScannerView.stopCamera();
            }
        });


        //Permission d'accès à la caméra
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //On recrée un scanner en cas de retour sur l'activité de Scan
        mScannerView = new ZXingScannerView(getApplicationContext());
        mCameraView.addView(mScannerView);

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, "Article ajouté", Toast.LENGTH_SHORT).show();
        ajouterArticleAuPanier(result.getText());

        //Ce Handler permet de dégeler la caméra au bout de 3 secondes après le scan d'un article, pour ne pas faire de doublon
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(ScanActivity.this);
            }
        }, 3000);
    }


    private void ajouterArticleAuPanier(String CodeBarre){
        Services services = ServiceFactory.createService(Services.class);
        call = services.obtenirArticle(CodeBarre);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                // On ajoute un nouvel article au panier à partir de la requête http
                Log.i("TAG", response.body().toString());
                Panier.mListeArticle.add(response.body());
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
            }
        });
    }
}
