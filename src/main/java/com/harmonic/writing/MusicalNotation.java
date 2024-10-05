package com.harmonic.writing;

public abstract class MusicalNotation{
    private MusicalNotationType notationType;

    public MusicalNotation(MusicalNotationType notationType){
        this.notationType = notationType;
    }


    //abstract void editContent();

    //public void saveToPDF();

    //public void display();


    public MusicalNotationType getNotationType(){
        return notationType;
    }

}