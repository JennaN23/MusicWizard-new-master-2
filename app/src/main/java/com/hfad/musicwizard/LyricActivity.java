package com.hfad.musicwizard;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.musixmatch.lyrics.MissingPluginException;
import com.musixmatch.lyrics.musiXmatchLyricsConnector;

import com.hfad.musicwizard.MusicPlayer.MainActivity;

public class LyricActivity extends AppCompatActivity {

    private TextView textViewLyrics;
    private musiXmatchLyricsConnector lyricsPlugin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);

        textViewLyrics = findViewById(R.id.textview_lyricactivity);
        textViewLyrics.setText("Lyrics");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(LyricActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_lyrics:
                        break;

                    case R.id.navigation_concerts:
                        Intent intent3 = new Intent(LyricActivity.this, ConcertActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });

        /*
        final String artistName = "The Beatles";
        final String trackName = "Let It Be";

        lyricsPlugin = new musiXmatchLyricsConnector(this);
        lyricsPlugin.setLoadingMessage("Lyrics", "Loading");

        Button buttonLyrics = findViewById(R.id.button_lyricactivity_showlyrics);
        buttonLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    lyricsPlugin.startLyricsActivity(artistName, trackName);
                }
                catch (MissingPluginException e) {
                    lyricsPlugin.downloadLyricsPlugin();
                }
                catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        */

    }


    /*
    @Override
    protected void onResume() {
        lyricsPlugin.doBindService();
        super.onResume();
    }

    @Override
    protected void onPause() {
        lyricsPlugin.doUnbindService();
        super.onPause();
    }
    */


}
