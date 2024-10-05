package com.harmonic.writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

@SpringBootApplication
public class Main {
    public Main() {
        /*JFrame frame = new JFrame("test");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        Lyrics lyrics = new Lyrics("Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna turn around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n");

        ChordChart chordChart = new ChordChart(lyrics);
        Music music = new Music("Never Gonna Give You Up", "Rick Astley");
        music.addNotation(chordChart);

        chordChart.addChord(Chord.G, 0, 0);
        chordChart.addChord(Chord.A, 0, 20);
        chordChart.addChord(Chord.F, 1, 12);
        chordChart.showPane(textPane);

        frame.add(new JScrollPane(textPane));
        frame.setVisible(true);*/
    }


    public static void main(String[] args) {


        SpringApplication.run(Main.class, args);
        //new Main();
    }

}