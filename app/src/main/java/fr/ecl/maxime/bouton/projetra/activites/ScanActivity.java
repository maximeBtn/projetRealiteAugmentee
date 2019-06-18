package fr.ecl.maxime.bouton.projetra.activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.zxing.Result;

import fr.ecl.maxime.bouton.projetra.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private FrameLayout mCameraView;
    private TextView message;
    private ImageButton mBoutonPanier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        message = findViewById(R.id.text1);
        mCameraView = findViewById(R.id.camera_preview);
        mBoutonPanier = findViewById(R.id.btn_panier);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mScannerView = new ZXingScannerView(getApplicationContext());
        mCameraView.addView(mScannerView);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

        mBoutonPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScanActivity.this, PanierActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void handleResult(Result result) {
        Log.v("TAG RESULTAT", result.getText());
        Log.v("TAG", result.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

}
