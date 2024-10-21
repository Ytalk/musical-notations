package com.harmonic.writing;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private String description;
    private List<Music> musics;


    public Playlist(String name){
        this.name = name;
        musics = new ArrayList<>();
    }

    public void addMusic(Music music){
        musics.add(music);
    }

    public String getName(){
        return name;
    }

    public List<Music> getMusics() {
        return musics;
    }

    /*public void createMusic(){

    }

    public void removeMusic(){

    }

    public void accessMusic(){

    }

    public void editPlaylist(){

    }*/

}
