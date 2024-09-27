package com.harmonic.writing;

public abstract class MusicalNotation{
    private MusicalNotationType notationType;

    public MusicalNotation(MusicalNotationType notationType){
        this.notationType = notationType;
    }


    //public abstract void editContent();

    //public void saveToPDF();

    //public void display();


    public MusicalNotationType getMusicalNotationType(){
        return notationType;
    }

}