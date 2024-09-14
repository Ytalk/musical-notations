package com.harmonic.writing;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChordChart {
    private Lyrics lyrics;
    private List<ChordPosition> chordPositions;
    private Map<String, Chord> chords;

    public ChordChart(Lyrics lyrics) {
        this.lyrics = lyrics;
        this.chordPositions = new ArrayList<>();
        this.chords = new HashMap<>();
    }

    public void adicionarChord(Chord chord){
        chords.put(chord.getName(), chord);
    }

    public void addChord(Chord chord, int line, int position) {//posicionar
        chordPositions.add( new ChordPosition(chord, line, position) );
    }

    public void showPane(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet lastChordStyle = null;

        try {
            //itera linhas
            for (int i = 0; i < lyrics.getSlicedLyrics().size(); i++) {
                String line = lyrics.getSlicedLyrics().get(i);

                //insere os acordes da linha i
                for (ChordPosition cp : chordPositions) {
                    if (cp.getLine() == i) {
                        //prepara style
                        SimpleAttributeSet chordStyle = new SimpleAttributeSet();
                        StyleConstants.setForeground(chordStyle, Color.decode(cp.getChord().getColor()));
                        StyleConstants.setBold(chordStyle, true);

                        //adiciona acorde com espaçamento
                        doc.insertString(doc.getLength(), cp.spacing() + cp.getChord().getName(), chordStyle);
                    }
                }

                //quebra linha para a letra
                doc.insertString(doc.getLength(), "\n", null);

                int lastPosition = 0;
                //insere a linha, colorindo conforme os acordes
                for (ChordPosition cp : chordPositions) {
                    if (cp.getLine() == i && cp.getPosition() >= lastPosition) {
                        if (cp.getPosition() > lastPosition) {
                            doc.insertString(doc.getLength(), line.substring(lastPosition, cp.getPosition()), null);
                        }

                        //adiciona parte da letra correspondente ao acorde com a cor
                        SimpleAttributeSet lyricStyle = new SimpleAttributeSet();
                        StyleConstants.setForeground(lyricStyle, Color.decode(cp.getChord().getColor()));

                        //define trecho colorido até o próximo acorde ou o final da linha
                        int nextPosition = line.length();
                        int indexCurrentChord = chordPositions.indexOf(cp);
                        if (indexCurrentChord < chordPositions.size() - 1) {
                            ChordPosition nextCp = chordPositions.get(indexCurrentChord + 1);
                            if (nextCp.getLine() == i) {
                                nextPosition = nextCp.getPosition();
                            }
                        }

                        //insere e colore o trecho
                        doc.insertString(doc.getLength(), line.substring(cp.getPosition(), nextPosition), lyricStyle);
                        lastPosition = nextPosition;

                        lastChordStyle = lyricStyle;
                    }
                }

                //adiciona o restante dos caracteres sem acordes
                if (lastPosition < line.length()) {
                    doc.insertString(doc.getLength(), line.substring(lastPosition), lastChordStyle);
                }

                //quebra de linha após letra
                doc.insertString(doc.getLength(), "\n", null);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }



    private static class ChordPosition {
        private final Chord chord;
        private final int position;
        private final int line;

        public ChordPosition(Chord chord, int line, int position) {
            this.chord = chord;
            this.position = position;
            this.line = line;
        }

        public Chord getChord() {
            return chord;
        }

        public int getPosition() {
            return position;
        }

        public int getLine() {
            return line;
        }

        public String spacing(){
            StringBuilder spacing = new StringBuilder();

            for(int x=0; x < (position*2); x++){
                spacing.append(" ");
            }

            return spacing.toString();
        }

    }
}

