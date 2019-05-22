package com.hfad.musicwizard;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hfad.musicwizard.MusicPlayer.MainActivity;

import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;

import java.io.IOException;
import java.util.List;

public class ConcertActivity extends AppCompatActivity {

    public String API_KEY = "5wi7kOO20VAi3xDlMNUWhwS9CS2yaIh7";
    public DiscoveryApi api = new DiscoveryApi(API_KEY);

    EditText editTextConcerts;
    TextView textViewEventName, textViewEventTime, textViewEventLocation, textViewEventDescription;

    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert);
        event = new Event();
        wireWidgets();
    }

    private void wireWidgets() {

        editTextConcerts = findViewById(R.id.edittext_concertactiity);
        textViewEventName = findViewById(R.id.textview_concertactivity_eventname);
        textViewEventTime = findViewById(R.id.textview_concertactivity_eventtime);
        textViewEventLocation = findViewById(R.id.textview_concertactivity_eventlocation);
        textViewEventDescription = findViewById(R.id.textView_concertactivity_eventdescription);

        Button buttonConcerts = findViewById(R.id.button_concertactivity);
        buttonConcerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Search(event).execute(editTextConcerts.getText().toString());
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(ConcertActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_lyrics:
                        Intent intent2 = new Intent(ConcertActivity.this, LyricActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_concerts:
                        break;
                }

                return false;
            }
        });
    }

    public class Search extends AsyncTask<String,String,String> {

        String eventName, eventTime, eventLocation;
        Event event;

        Search(Event event) {
            this.event = event;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                PagedResponse<Events> page = null;
                page = api.searchEvents(new SearchEventsOperation().keyword(strings[0]));
                List<Event> events = page.getContent().getEvents();

                event = events.get(1);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);

            eventName = event.getName();
            eventTime = event.getDates().getStart().getLocalDate() + " | " + event.getDates().getStart().getLocalTime();
            //eventLocation = event.getPlace().getCountry().getName();

            textViewEventName.setText(eventName);
            textViewEventTime.setText(eventTime);
            //textViewEventLocation.setText(eventLocation);

            if (event.getInfo() != null)
                textViewEventDescription.setText(event.getInfo());
            else
                textViewEventDescription.setText("No information is currently available.");

        }

    }

}