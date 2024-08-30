package com.harmonic.writing;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;
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
        chordPositions.add( new ChordPosition(chord, position) );
    }

    public String getLyrics() {
        return lyrics;
    }

    public void showPane(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();

        try {
            for (ChordPosition cp : chordPositions) {
                //add acorde acima
                SimpleAttributeSet chordStyle = new SimpleAttributeSet();
                StyleConstants.setForeground(chordStyle, Color.decode(cp.getChord().getColor()));
                StyleConstants.setBold(chordStyle, true);
                doc.insertString(doc.getLength(), cp.getChord().getName() + " ", chordStyle);
            }

            //quebra de linha para lyrics
            doc.insertString(doc.getLength(), "\n", null);

            //add lyrics com cores
            int lastPosition = 0;
            for (ChordPosition cp : chordPositions) {
                if (cp.getPosition() > lastPosition) {
                    doc.insertString(doc.getLength(), lyrics.substring(lastPosition, cp.getPosition()), null);
                }

                //add o trecho da letra com a cor
                SimpleAttributeSet lyricStyle = new SimpleAttributeSet();
                StyleConstants.setForeground(lyricStyle, Color.decode(cp.getChord().getColor()));

                //calcula até onde colorir
                int nextPosition = lyrics.length(); //próximo acorde não existe
                int indexCurrentChord = chordPositions.indexOf(cp);
                if (indexCurrentChord < chordPositions.size() - 1) {
                    nextPosition = chordPositions.get(indexCurrentChord + 1).getPosition();
                }

                //insere e colore
                doc.insertString(doc.getLength(), lyrics.substring(cp.getPosition(), nextPosition), lyricStyle);
                lastPosition = nextPosition;
            }

            //insere qualquer texto restante após o último acorde
            if (lastPosition < lyrics.length()) {
                doc.insertString(doc.getLength(), lyrics.substring(lastPosition), null);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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

