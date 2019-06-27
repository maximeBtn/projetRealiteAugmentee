package fr.ecl.maxime.bouton.projetra.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechDelegate;
import net.gotev.speech.SpeechRecognitionNotAvailable;

import java.util.List;

import fr.ecl.maxime.bouton.projetra.R;
import fr.ecl.maxime.bouton.projetra.classes.Article;
import fr.ecl.maxime.bouton.projetra.classes.Panier;
import fr.ecl.maxime.bouton.projetra.utils.ArticleAdapter;


//Gère l'affichage de tous les articles scannés, avec possibilité de les supprimer
public class PanierActivity extends AppCompatActivity implements SpeechDelegate {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mArticleAdapter;
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        Speech.init(this,getPackageName());

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

    @Override
    protected void onStart() {
        super.onStart();
        initiateSpeechRecognition();
    }

    private void initiateSpeechRecognition(){
        if (Speech.getInstance().isListening()) {
            Speech.getInstance().stopListening();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                onRecordAudioPermissionGranted();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            }
        }
    }

    private void onRecordAudioPermissionGranted() {
        try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().startListening(PanierActivity.this);

        } catch (SpeechRecognitionNotAvailable exc) {
            Log.e("speech", "Speech recognition is not available on this device!");
        } catch (GoogleVoiceTypingDisabledException exc) {
            Log.e("speech", "Google voice typing must be enabled!");
        }
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        Speech.getInstance().shutdown();
    }

    @Override
    public void onStartOfSpeech() {
        Log.i("speech", "speech recognition is now active");
    }

    @Override
    public void onSpeechRmsChanged(float value) {
        Log.d("speech", "rms is now: " + value);
    }

    @Override
    public void onSpeechPartialResults(List<String> results) {
        StringBuilder str = new StringBuilder();
        for (String res : results) {
            str.append(res).append(" ");
        }

        Log.i("speech", "partial result: " + str.toString().trim());
    }

    @Override
    public void onSpeechResult(String result) {
        Log.i("speech", "result: " + result);
        if (result!="") {
            String[] action = result.split(" ", 2);
            switch (action[0]) {
                case "nouveau":
                    PanierActivity.this.finish();
                    break;
                case "retour":
                    PanierActivity.this.finish();
                    break;

                case "supprimer":
                    String nomSupp = action[1];
                    Article articleSupp = mArticleAdapter.getArticleFromName(nomSupp);
                    if (articleSupp != null) {
                        Panier.mListeArticle.remove(articleSupp);
                        mArticleAdapter.notifyDataSetChanged();
                    }
                    break;

                case "article":
                    String nomInfo = action[1];
                    Article articleInfo = mArticleAdapter.getArticleFromName(nomInfo);
                    if (articleInfo != null) {
                        Intent i = new Intent(PanierActivity.this, DetailsActivity.class);
                        i.putExtra(POSITION, Panier.mListeArticle.indexOf(articleInfo));
                        startActivity(i);
                    }
                    break;

                default:
                    break;
            }
        }
        initiateSpeechRecognition();
    }
}
