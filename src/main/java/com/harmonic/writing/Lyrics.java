package com.harmonic.writing;

import java.util.ArrayList;
import java.util.List;

public class Lyrics extends MusicalNotation{
    private String lyrics;
    private List<String> slicedLyrics;

    public Lyrics(String lyrics){
        super( MusicalNotationType.LYRICS );

        this.lyrics = lyrics;
        this.slicedLyrics = new ArrayList();
        sliceLyrics();
    }

    public String getLyrics() {
        return lyrics;
    }

    public List<String> getSlicedLyrics(){
        return slicedLyrics;
    }

    private void sliceLyrics(){
        StringBuilder line = new StringBuilder();

        for (int x = 0; x < lyrics.length(); x++) {
            char currentChar = lyrics.charAt(x);

            if (currentChar == '\n') {
                slicedLyrics.add(line.toString());
                line = new StringBuilder();
            }
            else
                line.append(currentChar);
        }
    }

}
