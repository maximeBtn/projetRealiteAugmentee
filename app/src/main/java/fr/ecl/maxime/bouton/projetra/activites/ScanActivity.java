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

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, OnDSListener, OnDSPermissionsListener {

    public static final String BASE_URL = "https://fr.openfoodfacts.org/api/v0/produit/";
    private ZXingScannerView mScannerView;
    private FrameLayout mCameraView;
    private TextView message;
    private ImageButton mBoutonPanier;
    private DroidSpeech mDroidSpeech;
    private long lastTimeWorking;
    private Call<Article> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Panier.mListeArticle = new ArrayList<>(0);
        message = findViewById(R.id.text1);
        mCameraView = findViewById(R.id.camera_preview);
        mBoutonPanier = findViewById(R.id.btn_panier);

        mBoutonPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScanActivity.this, PanierActivity.class);
                startActivity(i);
                mScannerView.stopCamera();
            }
        });

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mScannerView = new ZXingScannerView(getApplicationContext());
        mCameraView.addView(mScannerView);

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

        mDroidSpeech = new DroidSpeech(getApplicationContext(),null);
        mDroidSpeech.setOnDroidSpeechListener(this);
        mDroidSpeech.setShowRecognitionProgressView(false);
        mDroidSpeech.startDroidSpeechRecognition();

    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, "Article ajouté", Toast.LENGTH_SHORT).show();
        ajouterArticleAuPanier(result.getText());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(ScanActivity.this);
            }
        }, 3000);
    }

    @Override
    public void onDroidSpeechSupportedLanguages(String currentSpeechLanguage, List<String> supportedSpeechLanguages) {
        if(supportedSpeechLanguages.contains("fr-FR"))
        {
            mDroidSpeech.setPreferredLanguage("fr-FR");
        }
    }

    @Override
    public void onDroidSpeechRmsChanged(float rmsChangedValue) {
        lastTimeWorking = System.currentTimeMillis();
    }

    @Override
    public void onDroidSpeechLiveResult(String liveSpeechResult) {

    }

    @Override
    public void onDroidSpeechFinalResult(String finalSpeechResult) {
        if (finalSpeechResult.equalsIgnoreCase("Panier") || finalSpeechResult.toLowerCase().contains("panier")) {
            Intent i = new Intent(ScanActivity.this, PanierActivity.class);
            startActivity(i);
            mDroidSpeech.closeDroidSpeechOperations();
        }
    }

    @Override
    public void onDroidSpeechClosedByUser() {
        mDroidSpeech.closeDroidSpeechOperations();
    }

    @Override
    public void onDroidSpeechError(String errorMsg) {

    }

    @Override
    public void onDroidSpeechAudioPermissionStatus(boolean audioPermissionGiven, String errorMsgIfAny) {

    }

    private void ajouterArticleAuPanier(String CodeBarre){
        Services services = ServiceFactory.createService(Services.class);
        call = services.obtenirArticle(CodeBarre);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                Log.i("TAG", response.body().toString());
                Panier.mListeArticle.add(response.body());
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
            }
        });
    }
}
