package com.hfad.musicwizard;


class MusixMatchResponse {

    private int header;
    private MusixMatch body;

    public MusixMatchResponse(int header, MusixMatch body) {
        this.header = header;
        this.body = body;
    }

    public int getHeader() {
        return header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public MusixMatch getBody() {
        return body;
    }

    public void setBody(MusixMatch body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MusixMatchResponse{" +
                "header=" + header +
                ", body='" + body + '\'' +
                '}';
    }
}
