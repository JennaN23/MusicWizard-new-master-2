package com.hfad.musicwizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.hfad.musicwizard.MusicPlayer.MainActivity;

import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import static com.hfad.musicwizard.R.id.navigation_home;

public class LyricActivity extends AppCompatActivity {

    private TextView textViewLyrics;
    private EditText editTextSong;
    private String artistName;
    private String trackName;
    private int trackID;
    private TrackData data;
    private Track track;
    private EditText editTextArtist;
    private String apiKey = "4ab5ae9e96c6b208d9601e182c4af443";
    private String keyword;
    private int x = 0;
    private Button buttonLyrics;
    MusixMatch musixMatch = new MusixMatch(apiKey);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_lyrics);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case navigation_home:
                        Intent a = new Intent(LyricActivity.this, MainActivity.class);
                        startActivity(a);
                        return true;
                    case R.id.navigation_lyrics:
                        return true;
                    case R.id.navigation_concerts:
                        Intent b = new Intent(LyricActivity.this, ConcertActivity.class);
                        startActivity(b);
                        return true;
                }

                return false;
            }
        });

        wireWidgets();
        buttonLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getLyrics();
                } catch (MusixMatchException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void wireWidgets() {
        textViewLyrics = findViewById(R.id.textView_music_lyrics);
        editTextArtist = findViewById(R.id.editText_lyric_artist);
        buttonLyrics = findViewById(R.id.button_lyrics);
        editTextSong = findViewById(R.id.editText_lyric_song);
    }

    /*    private void getMusixMatch() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.musixmatch.com/ws/1.1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MusixMatchService service = retrofit.create(MusixMatchService.class);

            keyword = musicSearch.getQuery().toString();
            Call<MusixMatchResponse> MusixMatchResponseCall = service.searchMusixMatch(keyword, 1);

            MusixMatchResponseCall.enqueue(new Callback<MusixMatchResponse>() {
                @Override
                public void onResponse(@NonNull Call<MusixMatchResponse> call, @NonNull Response<MusixMatchResponse> response) {
                    MusixMatch musixMatch = response.body().getBody();
                    List<MusixMatchResponse> musixMatchResponses = musixMatch.getMessage();
                    String title = musixMatchResponses.get(x).toString();
                    int startIndex = title.indexOf("title");
                    int endIndex = title.indexOf("'}}");
                    textViewLyrics.setText(musixMatch.toString());
                    Log.d("ENQUEUE", "onResponse: " + musixMatch.toString());
                }

                @Override
                public void onFailure(@NonNull Call<MusixMatchResponse> call, @NonNull Throwable t) {
                    Log.d("ENQUEUE", "onResponse: " + t.getMessage());
                }
            });





        }**/
    public void search() throws MusixMatchException {
        trackName = editTextSong.getText().toString();
        artistName = editTextArtist.getText().toString();
        track = musixMatch.getMatchingTrack(trackName, artistName);
        data = track.getTrack();
        System.out.println("AlbumID : " + data.getAlbumId());
        System.out.println("Album Name : " + data.getAlbumName());
        System.out.println("Artist ID : " + data.getArtistId());
        System.out.println("Album Name : " + data.getArtistName());
        trackID = data.getTrackId();
    }

        public void getLyrics() throws MusixMatchException {
        trackName = editTextSong.getText().toString();
        artistName = editTextArtist.getText().toString();
        track = musixMatch.getMatchingTrack(trackName, artistName);
            data = track.getTrack();
            trackID = data.getTrackId();
            Lyrics lyrics = musixMatch.getLyrics(trackID);
            textViewLyrics.setText(lyrics.getLyricsBody());

        }


    }
