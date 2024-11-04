package com.example.flagsandcapitals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;


public class GameActivity extends AppCompatActivity {
    //    String[] countryArr = new String[]{"afghanistan", "albania", "algeria", "andorra", "angola", "anguilla", "argentina", "armenia", "aruba", "australia", "austria", "azerbaijan", "bahamas", "bahrain", "bangladesh", "barbados", "belarus", "belgium", "belize", "benin", "bermuda", "bhutan", "bolivia", "botswana", "brazil", "brunei", "bulgaria", "burkina_faso", "burundi", "cambodia", "cameroon", "canada", "canary_islands", "cape_verde", "cayman_islands", "chad", "chile", "china", "colombia", "comoros", "costa_rica", "croatia", "cuba", "curacao", "cyprus", "czech_republic", "denmark", "djibouti", "dominica", "dominican_republic", "east_timor", "ecuador", "egypt", "el_salvador", "england", "equatorial_guinea", "eritrea", "estonia", "ethiopia", "european_union", "finland", "france", "french_polynesia", "gabon", "gambia", "georgia", "germany", "ghana", "gibraltar", "greece", "greenland", "grenada", "guatemala", "guinea", "guinea_bissau", "haiti", "hawaii", "honduras", "hong_kong", "hungary", "iceland", "india", "indonesia", "iran", "iraq", "ireland", "italy", "ivory_coast", "jamaica", "japan", "jersey", "jordan", "kazakhstan", "kenya", "kiribati", "kosovo", "kwait", "kyrgyzstan", "laos", "latvia", "lebanon", "lesotho", "liberia", "libya", "liechtenstein", "lithuania", "luxembourg", "macao", "madagascar", "malawi", "maldives", "mali", "malta", "marshall_island", "mauritania", "mauritius", "mexico", "moldova", "monaco", "mongolia", "montenegro", "morocco", "mozambique", "myanmar", "namibia", "nato", "nauru", "nepal", "netherlands", "new_zealand", "nicaragua", "niger", "nigeria", "north_korea", "northern_cyprus", "norway", "oman", "pakistan", "palau", "palestine", "panama", "papua_new_guinea", "paraguay", "peru", "philippines", "poland", "portugal", "puerto_rico", "qatar", "republic_of_macedonia", "romania", "russia", "rwanda", "samoa", "san_marino", "saudi_arabia", "scotland", "senegal", "serbia", "seychelles", "sierra_leone", "singapore", "slovakia", "slovenia", "solomon_islands", "somalia", "somaliland", "south_africa", "south_korea", "south_sudan", "spain", "sri_lanka", "sudan", "suriname", "swaziland", "sweden", "switzerland", "syria", "taiwan", "tajikistan", "tanzania", "thailand", "togo", "tunisia", "turkey", "turkmenistan", "uganda", "ukraine", "united_arab_emirates", "united_kingdom", "united_nations", "united_states", "uruguay", "uzbekistan", "vanuatu", "vatican_city", "venezuela", "vietnam", "wales", "yemen", "zambia", "zimbabwe"};
//    MaterialCardView card1, card2, card3, card4;
//    ImageView flag1, flag2, flag3, flag4 , true_image,false_image;
    CircularRevealCardView goToProfile;
    //    int trueAn,falseAn;
//    TextView nameFlag;
//    int sizeArr = countryArr.length;
//    int trueAns;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        /*flag1 = findViewById(R.id.flag1);
        flag2 = findViewById(R.id.flag2);
        flag3 = findViewById(R.id.flag3);
        flag4 = findViewById(R.id.flag4);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        nameFlag = findViewById(R.id.name_flag);
        true_image = findViewById(R.id.true_image);
        false_image = findViewById(R.id.false_image);*/
        goToProfile = findViewById(R.id.back_button);
        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameActivity.this, HomeActivity.class));
                finish();
            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        //adapter.instantiateItem(viewPager,1);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

       /* card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 0) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 1) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 2) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 3) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });
        getFourFlag();*/
    }

    public void onSectionAttached(int number) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch (number) {
            case 1:
                fragment = new FirstFragment();
                break;
            case 2:
                fragment = new HomeFragment();
                break;
            case 3:
                fragment = new TheardFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.viewpager, fragment)
                    .setReorderingAllowed(true)
                    .commit();

        } else {
            // error in creating fragment
        }
    }

   /* Animation am;
    void animationTrue(){
        true_image.setVisibility(View.VISIBLE);
        am = AnimationUtils.loadAnimation(this, R.anim.animation);
        true_image.startAnimation(am);

    }
    void animationFalse(){
        false_image.setVisibility(View.VISIBLE);
        am = AnimationUtils.loadAnimation(this, R.anim.animation);
        false_image.startAnimation(am);

    }
    String[] flags = new String[4];

    private String[] getFourFlag() {
        Random random2 = new Random();
        trueAns = random2.nextInt(4);
        flags = new String[4];
        boolean s = true;
        int index = 0;
        int[] randomArr = new int[4];
        Random random = new Random();
        while (s) {
            if (index < 4) {
                *//*int low = 0;
                int high = 4;
                int randomNumber = random.nextInt(high-low) + low;*//*
                int randomNumber = random.nextInt(sizeArr);
                if (index == 0) {
                    randomArr[0] = randomNumber;
                    index++;
                } else {
                    switch (index) {
                        case 1:
                            if (randomNumber != randomArr[0]) {
                                randomArr[1] = randomNumber;
                                index++;
                            }
                            break;
                        case 2:
                            if (randomNumber != randomArr[0] && randomNumber != randomArr[1]) {
                                randomArr[2] = randomNumber;
                                index++;
                            }
                            break;
                        case 3:
                            if (randomNumber != randomArr[0] && randomNumber != randomArr[1] && randomNumber != randomArr[2]) {
                                randomArr[3] = randomNumber;
                                index++;
                            }
                            break;
                    }
                }
            } else s = false;
        }
        int resourceId = this.getResources().getIdentifier(countryArr[randomArr[trueAns]], "string", this.getPackageName());
        String s1 = getString(resourceId);
        nameFlag.setText(s1);


        flag1.setImageResource(getImageResourceId(this, countryArr[randomArr[0]]));
        flag2.setImageResource(getImageResourceId(this, countryArr[randomArr[1]]));
        flag3.setImageResource(getImageResourceId(this, countryArr[randomArr[2]]));
        flag4.setImageResource(getImageResourceId(this, countryArr[randomArr[3]]));

        return flags;
    }

    public static int getImageResourceId(Context context, String name) {
        try {
            return context.getResources().getIdentifier(name, "drawable",
                    context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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
        return new int[]{trueAns,falseAns};
    }
    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        int[] tfAns =getAns();
        saveAns(tfAns[0]+trueAn,tfAns[1]+falseAn);
        trueAn = 0;
        falseAn = 0;
    }

    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(1000);
        v.startAnimation(anim);

    }*/

}