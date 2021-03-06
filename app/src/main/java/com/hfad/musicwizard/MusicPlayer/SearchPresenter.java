package com.hfad.musicwizard.MusicPlayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.hfad.musicwizard.SpotifyAPI.SpotifyAPI;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public class SearchPresenter implements Search.ActionListener {

    private static final String TAG = "SearchPresenter";
    private static final int PAGE_SIZE = 20;

    private final Context context;
    private final Search.View view;
    private String currentQuery;
    private SearchPager searchPager;
    private SearchPager.CompleteListener completeListener;
    private Player player;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            player = ((PlayerService.PlayerBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            player = null;
        }
    };

    public SearchPresenter(Context context, Search.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void init(String accessToken) {
        logMessage("Api Client created");
        SpotifyAPI spotifyAPI = new SpotifyAPI();

        if (accessToken != null) {
            spotifyAPI.setToken(accessToken);
        } else {
            logError("No valid access token");
        }

        searchPager = new SearchPager(spotifyAPI.getService());

        context.bindService(PlayerService.getIntent(context), mServiceConnection, Activity.BIND_AUTO_CREATE);
    }


    @Override
    public void search(@Nullable String searchQuery) {
        if (searchQuery != null && !searchQuery.isEmpty() && !searchQuery.equals(currentQuery)) {
            logMessage("query text submit " + searchQuery);
            currentQuery = searchQuery;
            view.reset();
            completeListener = new SearchPager.CompleteListener() {
                @Override
                public void onComplete(List<Track> items) {
                    view.addData(items);
                }

                @Override
                public void onError(Throwable error) {
                    logError(error.getMessage());
                }
            };
            searchPager.getFirstPage(searchQuery, PAGE_SIZE, completeListener);
        }
    }


    @Override
    public void destroy() {
        context.unbindService(mServiceConnection);
    }

    @Override
    @Nullable
    public String getCurrentQuery() {
        return currentQuery;
    }

    @Override
    public void resume() {
        context.stopService(PlayerService.getIntent(context));
    }

    @Override
    public void pause() {
        context.startService(PlayerService.getIntent(context));
    }

    @Override
    public void loadMoreResults() {
        Log.d(TAG, "Load more...");
        searchPager.getNextPage(completeListener);
    }

    @Override
    public void selectTrack(Track item) {
        String previewUrl = item.preview_url;

        if (previewUrl == null) {
            logMessage("Track doesn't have a preview");
            return;
        }

        if (player == null) return;

        String currentTrackUrl = player.getCurrentTrackURL();

        if (currentTrackUrl == null || !currentTrackUrl.equals(previewUrl)) {
            player.play(previewUrl);
        } else if (player.isPlaying()) {
            player.pause();
        } else {
            player.resume();
        }
    }

    private void logError(String msg) {
        Toast.makeText(context, "Error: " + msg, Toast.LENGTH_SHORT).show();
        Log.e(TAG, msg);
    }

    private void logMessage(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, msg);
    }

    //TODO: review
}
