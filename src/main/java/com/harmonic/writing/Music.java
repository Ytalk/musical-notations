package com.harmonic.writing;

import java.util.List;
import java.util.ArrayList;

public class Music {
    private List<MusicalNotation> notations;
    private String title;
    private String artist;

    public Music(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.notations = new ArrayList<>();
    }

    public void addNotation(MusicalNotation notation) {
        notations.add(notation);
    }

    public void removeNotation(MusicalNotation notation) {
        notations.remove(notation);
    }

}
