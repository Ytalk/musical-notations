package com.harmonic.writing;

import java.util.ArrayList;
import java.util.List;

public class ChordChart {
    private String lyrics;
    private List<ChordPosition> chordPositions;

    public ChordChart(String lyrics) {
        this.lyrics = lyrics;
        this.chordPositions = new ArrayList<>();
    }

    public void addChord(Chord chord, int position) {
        chordPositions.add(new ChordPosition(chord, position));
    }

    public String getLyrics() {
        return lyrics;
    }

    public List<ChordPosition> getChordPositions() {
        return chordPositions;
    }

    public void showChordChart() {
        StringBuilder formattedLyrics = new StringBuilder(lyrics);
        for (ChordPosition cp : chordPositions) {
            String chordText = cp.getChord().getName();
            formattedLyrics.insert(cp.getPosition(), chordText + " ");
        }
        System.out.println(formattedLyrics.toString());
    }

    private static class ChordPosition {
        private final Chord chord;
        private final int position;

        public ChordPosition(Chord chord, int position) {
            this.chord = chord;
            this.position = position;
        }

        public Chord getChord() {
            return chord;
        }

        public int getPosition() {
            return position;
        }
    }
}

