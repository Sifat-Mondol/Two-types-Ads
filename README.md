AdMob Full-Screen and Banner Ads Integration for Android
This Android project demonstrates how to integrate AdMob's Interstitial Ads and Banner Ads into an Android application. It showcases the loading, displaying, and handling of both types of ads with test ad unit IDs, suitable for testing purposes during the development phase.

Features
Interstitial Ads: Displays a full-screen ad when triggered by a button click.
Banner Ads: Displays a banner ad at the bottom of the screen.
Logging: Provides logs for ad events, such as ad load, click, dismissal, and failure.
AdMob SDK Integration: Integrates Google Mobile Ads SDK for ad management.
Requirements
Android Studio: Android Studio 4.0 or higher.
AdMob Account: Ensure you have an AdMob account to replace test ad IDs with real ones for production.
Internet Access: The app requires internet access to load ads.
Setup
1. Clone the repository
bash
Copy
Edit
git clone https://github.com/your-username/AdMob-FullScreen-Banner-Ads.git
cd AdMob-FullScreen-Banner-Ads
2. Add Google Mobile Ads SDK
Ensure that the Google Mobile Ads SDK is integrated into your project. You can add it by including the following dependency in your build.gradle file:

gradle
Copy
Edit
dependencies {
    implementation 'com.google.android.gms:play-services-ads:21.0.0'
}
Sync your project with the Gradle files after adding the dependency.

3. Update AndroidManifest.xml
In the AndroidManifest.xml file, add your AdMob APP ID inside the <application> tag:

xml
Copy
Edit
<application
    android:name=".MyApplication"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name">
    
    <!-- AdMob Application ID -->
    <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-3940256099942544~3347511713" />
    
    <!-- Your other configurations here -->
</application>
4. Configure Ad Units
Replace the test ad unit IDs in the MainActivity.java file with your own real AdMob ad unit IDs when you're ready to go live:

java
Copy
Edit
// Banner Ad Unit ID
app:adUnitId="ca-app-pub-3940256099942544/6300978111"

// Interstitial Ad Unit ID
InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {...});
For testing purposes, these test ad IDs are provided by Google:

Banner Ad Test ID: ca-app-pub-3940256099942544/6300978111
Interstitial Ad Test ID: ca-app-pub-3940256099942544/1033173712
5. Run the App
Once the setup is complete, you can build and run the app. The app will display a banner ad at the bottom of the screen, and you can click the "Show Full-Screen Ad" button to display the interstitial ad.

Code Walkthrough
MainActivity.java: The core activity handles both the loading and displaying of ads.
The loadFullscreenAd() method loads an Interstitial Ad.
The loadBannerAd() method loads a Banner Ad.
Button clicks trigger the display of respective ads, and logs the event in the console.
activity_main.xml: The layout file includes two buttons for interaction and a AdView to display the banner ad.
Event Handling
The app provides logging to track the lifecycle events of ads:

onAdLoaded: Ad successfully loaded.
onAdFailedToLoad: Ad failed to load.
onAdClicked: The ad was clicked.
onAdDismissedFullScreenContent: The full-screen ad was dismissed.
onAdFailedToShowFullScreenContent: Failed to show the full-screen ad.
License
This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgments
AdMob: For providing the SDK for mobile ads integration.
Google Developers: For providing the official documentation and resources for ads integration.
