package com.example.flagsandcapitals;

import static com.example.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flagsandcapitals.model.AUser;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ImageView imageView;
    CircleImageView circleImageView;
    CircularRevealCardView backButton;
    MaterialButton reset_button;
    TextView uidText, usernameText, text_rate_f, text_true, text_false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        circleImageView = findViewById(R.id.profile_image);
        usernameText = findViewById(R.id.text_username);
        uidText = findViewById(R.id.text_id_pubg);
        backButton = findViewById(R.id.back_button);
        text_rate_f = findViewById(R.id.text_rate_f);
        text_true = findViewById(R.id.text_true);
        text_false = findViewById(R.id.text_false);
        reset_button = findViewById(R.id.reset_button);
        db = FirebaseFirestore.getInstance();
        String pubguid = getPubgUid();
        checkLogin(pubguid);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAns();
            }
        });
        math();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        /*String linkImage = getIntent().getStringExtra("urlImage");
        String uid = getIntent().getStringExtra("pubguid");
        String username = getIntent().getStringExtra("username");

        try {
            if ((!uid.isEmpty()) && (!username.isEmpty())) {
                usernameText.setText(username);
                uidText.setText(uid);
            }
        } catch (Exception e) {

        }
        Picasso.get().load(linkImage).into(circleImageView);*/
    }

    private void math() {
        int[] tf = getAns();
        int truePercent = 0;

        float b = tf[0] + tf[1];
        float a = tf[0];
        truePercent = (int) (a / b *100);

        text_rate_f.setText(truePercent  + "%");
        text_true.setText(tf[0] + "");
        text_false.setText(tf[1] + "");
    }

    private void resetAns() {
        saveAns(0,0);
        text_rate_f.setText(0  + "%");
        text_true.setText(0 + "");
        text_false.setText(0 + "");
    }
    private void saveAns(int trueAns,int falseAns) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("trueAns", trueAns);
        editor.putInt("falseAns", falseAns);
        editor.apply();
    }
    private int[] getAns() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int trueAns = prefs.getInt("trueAns", 0);//"No name defined" is the default value.
        int falseAns = prefs.getInt("falseAns", 0);//"No name defined" is the default value.
        return new int[]{trueAns, falseAns};
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
                            Toast.makeText(HomeActivity.this, getString(R.string.errorconnection), Toast.LENGTH_LONG).show();
                            return;
                        }
                        ArrayList<AUser> listUsers = new ArrayList<AUser>();

                        for (DocumentSnapshot doc : snapshots) {
                            AUser user = doc.toObject(AUser.class);
                            listUsers.add(user);
                        }
                        usernameText.setText(listUsers.get(0).getUsername());
                        uidText.setText(listUsers.get(0).getPubgUid());
                        Picasso.get().load(listUsers.get(0).getImageProfile()).into(circleImageView);
                    }
                });
    }
}