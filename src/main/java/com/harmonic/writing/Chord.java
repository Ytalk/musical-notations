package com.harmonic.writing;

public enum Chord {
    C("C", "#FF0000"),//vermelho
    D("D", "#00FF00"),//verde
    E("E", "#0000FF"),//azul
    F("F", "#FFFF00"),//amarelo
    G("G", "#FF00FF"),//rosa
    A("A", "#00FFFF"),//ciano
    B("B", "#FFA500");//laranja

    private final String name;
    private final String color;

    Chord(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}

