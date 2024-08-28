package com.harmonic.writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        String lyrics = "Never gonna give you up...";
        ChordChart chordChart = new ChordChart(lyrics);

        chordChart.addChord(Chord.G, 0);
        chordChart.addChord(Chord.A, 7);

        chordChart.showChordChart();
    }
}