package com.hfad.musicwizard;

import java.util.List;

public class MusixMatch {

    private String apiKey = "4ab5ae9e96c6b208d9601e182c4af443";
    private List<MusixMatchResponse> message;

    public MusixMatch(String apiKey, List<MusixMatchResponse> message) {
        this.apiKey = apiKey;
        this.message = message;
    }

    public void setMessage(List<MusixMatchResponse> message) {
        this.message = message;
    }

    public List<MusixMatchResponse> getMessage() {
        return message;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "MusixMatch{" +
                "message=" + message +
                '}';
    }
/*
    public Lyrics getLyrics(int trackID) throws MusixMatchException {
        Lyrics lyrics = null;
        LyricsGetMessage message = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.API_KEY, apiKey);
        params.put(Constants.TRACK_ID, new String("" + trackID));
        String response = null;
        response = MusixMatchRequest.sendRequest(Helper.getURLString(
                Methods.TRACK_LYRICS_GET, params));
        Gson gson = new Gson();
        try {
            message = gson.fromJson(response, LyricsGetMessage.class);
        } catch (JsonParseException jpe) {
            handleErrorResponse(response);
        }
        lyrics = message.getContainer().getBody().getLyrics();
        return lyrics;
    }
    public Snippet getSnippet(int trackID) throws MusixMatchException {
        Snippet snippet = null;
        SnippetGetMessage message = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.API_KEY, apiKey);
        params.put(Constants.TRACK_ID, new String("" + trackID));
        String response = null;
        response = MusixMatchRequest.sendRequest(Helper.getURLString(
                Methods.TRACK_SNIPPET_GET, params));
        Gson gson = new Gson();
        try {
            message = gson.fromJson(response, SnippetGetMessage.class);
        } catch (JsonParseException jpe) {
            handleErrorResponse(response);
        }
        snippet = message.getContainer().getBody().getSnippet();
        return snippet;
    }
    public Subtitle getSubtitle(int trackID) throws MusixMatchException {
        Subtitle subtitle = null;
        SubtitleGetMessage message = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.API_KEY, apiKey);
        params.put(Constants.TRACK_ID, new String("" + trackID));
        String response = null;
        response = MusixMatchRequest.sendRequest(Helper.getURLString(
                Methods.TRACK_SUBTITLE_GET, params));
        Gson gson = new Gson();
        try {
            message = gson.fromJson(response, SubtitleGetMessage.class);
        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
            handleErrorResponse(response);
        }
        subtitle = message.getContainer().getBody().getSubtitle();
        return subtitle;
    }
    public List<Track> searchTracks(String q, String q_artist, String q_track,
                                    int page, int pageSize, boolean f_has_lyrics)
            throws MusixMatchException {
        List<Track> trackList = null;
        TrackSeachMessage message = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.API_KEY, apiKey);
        params.put(Constants.QUERY, q);
        params.put(Constants.QUERY_ARTIST, q_artist);
        params.put(Constants.QUERY_TRACK, q_track);
        params.put(Constants.PAGE, page);
        params.put(Constants.PAGE_SIZE, pageSize);
        if (f_has_lyrics) {
            params.put(Constants.F_HAS_LYRICS, "1");
        } else {
            params.put(Constants.F_HAS_LYRICS, "0");
        }
        String response = null;
        response = MusixMatchRequest.sendRequest(Helper.getURLString(
                Methods.TRACK_SEARCH, params));
        Gson gson = new Gson();
        try {
            message = gson.fromJson(response, TrackSeachMessage.class);
        } catch (JsonParseException jpe) {
            handleErrorResponse(response);
        }
        int statusCode = message.getTrackMessage().getHeader().getStatusCode();
        if (statusCode > 200) {
            throw new MusixMatchException("Status Code is not 200");
        }
        trackList = message.getTrackMessage().getBody().getTrack_list();
        return trackList;
    }
    public Track getTrack(int trackID) throws MusixMatchException {
        Track track = new Track();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.API_KEY, apiKey);
        params.put(Constants.TRACK_ID, new String("" + trackID));
        track = getTrackResponse(Methods.TRACK_GET, params);
        return track;
    }
    public Track getMatchingTrack(String q_track, String q_artist)
            throws MusixMatchException {
        Track track = new Track();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.API_KEY, apiKey);
        params.put(Constants.QUERY_TRACK, q_track);
        params.put(Constants.QUERY_ARTIST, q_artist);
        track = getTrackResponse(Methods.MATCHER_TRACK_GET, params);
        return track;
    }
    private Track getTrackResponse(String methodName, Map<String, Object> params)
            throws MusixMatchException {
        Track track = new Track();
        String response = null;
        TrackGetMessage message = null;
        response = MusixMatchRequest.sendRequest(Helper.getURLString(
                methodName, params));
        Gson gson = new Gson();
        try {
            message = gson.fromJson(response, TrackGetMessage.class);
        } catch (JsonParseException jpe) {
            handleErrorResponse(response);
        }
        TrackData data = message.getTrackMessage().getBody().getTrack();
        track.setTrack(data);
        return track;
    }
    private void handleErrorResponse(String jsonResponse)
            throws MusixMatchException {
        StatusCode statusCode;
        Gson gson = new Gson();
        System.out.println(jsonResponse);
        ErrorMessage errMessage = gson.fromJson(jsonResponse,
                ErrorMessage.class);
        int responseCode = errMessage.getMessageContainer().getHeader()
                .getStatusCode();
        switch (responseCode) {
            case 400:
                statusCode = StatusCode.BAD_SYNTAX;
                break;
            case 401:
                statusCode = StatusCode.AUTH_FAILED;
                break;
            case 402:
                statusCode = StatusCode.LIMIT_REACHED;
                break;
            case 403:
                statusCode = StatusCode.NOT_AUTHORIZED;
                break;
            case 404:
                statusCode = StatusCode.RESOURCE_NOT_FOUND;
                break;
            case 405:
                statusCode = StatusCode.METHOD_NOT_FOUND;
                break;
            default:
                statusCode = StatusCode.ERROR;
                break;
        }
        throw new MusixMatchException(statusCode.getStatusMessage());
    }**/
}