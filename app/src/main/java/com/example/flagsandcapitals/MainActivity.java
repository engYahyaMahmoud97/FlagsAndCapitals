package com.example.flagsandcapitals;

import static com.example.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flagsandcapitals.model.AUser;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CircularProgressIndicator progressIndicator;
    TextView progressPercent,textView2;
    int currentProgress = 0;
    FirebaseFirestore db;
    ImageView wifi_image;
    NetworkInfo mWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        progressIndicator = findViewById(R.id.circularProgressIndicator);
        progressPercent = findViewById(R.id.numProgress);
        textView2 = findViewById(R.id.textView2);
        wifi_image = findViewById(R.id.wifi_image);
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.swiperefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        refreshData();

    }

    private void refreshData() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected()) {
            wifi_image.setVisibility(View.GONE);
            progressPercent.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            progressIndicator.setVisibility(View.VISIBLE);
            CountDownTimer countDownTimer = new CountDownTimer(2000, 15) {
                @Override
                public void onTick(long l) {
                    if (currentProgress != 100) {
                        currentProgress = currentProgress + 1;
                        progressIndicator.setProgress(currentProgress);
                        progressPercent.setText(currentProgress + "");
                        progressIndicator.setMax(100);
                    }
                }

                @Override
                public void onFinish() {
                    String pubguid = getPubgUid();
                    checkLogin(pubguid);
                }
            };
            countDownTimer.start();
        }else {
            wifi_image.setVisibility(View.VISIBLE);
            progressPercent.setVisibility(View.GONE);
            textView2.setVisibility(View.GONE);
            progressIndicator.setVisibility(View.GONE);
        }
    }

    private String getPubgUid() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("pubgUid", "null");//"No name defined" is the default value.
        return name;
    }

    private void checkLogin(String s) {
        db.collection("users").whereEqualTo("pubgUid", s)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            System.err.println("Listen failed:" + e);
                            Toast.makeText(MainActivity.this, getString(R.string.errorconnection), Toast.LENGTH_LONG).show();
                            return;
                        }
                        ArrayList<AUser> listUsers = new ArrayList<AUser>();

                        for (DocumentSnapshot doc : snapshots) {
                            AUser user = doc.toObject(AUser.class);
                            listUsers.add(user);
                        }
                        if (!listUsers.isEmpty()) {
                            Intent intent = new Intent(MainActivity.this, GameActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("urlImage", listUsers.get(0).getImageProfile());
                            intent.putExtra("pubguid", listUsers.get(0).getPubgUid());
                            intent.putExtra("username", listUsers.get(0).getUsername());
                            intent.putExtra("whatsapp", listUsers.get(0).getWhatsappNumber());
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                        //updateListUsers(listUsers);
                    }
                });
    }

}