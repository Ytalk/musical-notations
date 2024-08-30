package com.harmonic.writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

@SpringBootApplication
public class Main {
    public Main() {
        JFrame frame = new JFrame("test");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        ChordChart chordChart = new ChordChart("Never gonna give you up...");
        chordChart.addChord(Chord.G, 0);
        chordChart.addChord(Chord.A, 20);
        chordChart.showPane(textPane);

        frame.add(new JScrollPane(textPane));
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //SpringApplication.run(Main.class, args);
        new Main();
    }

}