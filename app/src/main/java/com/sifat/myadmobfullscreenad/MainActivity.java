package com.sifat.myadmobfullscreenad;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    AdView mBannerAd;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        // Initialize Mobile Ads SDK
        MobileAds.initialize(this, initializationStatus -> {
            Log.d(TAG, "Mobile Ads SDK Initialized");
        });

        // Load the full-screen ad
        loadFullscreenAd();

        // Load the banner ad
        loadBannerAd();

        // Button to show interstitial ad
        button1.setOnClickListener(v -> {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(MainActivity.this);
                Log.d(TAG, "Interstitial Ad shown.");
            } else {
                Log.e(TAG, "Interstitial ad is not ready.");
            }
        });


        button2.setOnClickListener(v -> {
            Log.d(TAG, "Banner ad is visible.");
        });
    }

    private void loadFullscreenAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        Log.d(TAG, "Loading Interstitial Ad...");

        // Load the interstitial ad with the test ad ID
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.d(TAG, "Interstitial Ad loaded successfully.");


                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d(TAG, "Ad dismissed fullscreen content.");
                                mInterstitialAd = null;
                                loadFullscreenAd(); // Reload the ad when dismissed
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Log.e(TAG, "Ad failed to show fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                                Log.d(TAG, "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                Log.d(TAG, "Ad showed fullscreen content.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        Log.e(TAG, "Failed to load interstitial ad: " + loadAdError.getMessage());
                    }
                });
    }

    private void loadBannerAd() {
        mBannerAd = findViewById(R.id.adView);
        Log.d(TAG, "Loading Banner Ad...");


        AdRequest adRequest = new AdRequest.Builder().build();
        mBannerAd.loadAd(adRequest);
    }

    @Override
    protected void onPause() {
        if (mBannerAd != null) {
            mBannerAd.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBannerAd != null) {
            mBannerAd.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mBannerAd != null) {
            mBannerAd.destroy();
        }
        super.onDestroy();
    }


}