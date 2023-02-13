package com.temychp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MusicPlayer {
    private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;
    private RapMusic rapMusic;

    @Autowired
    public MusicPlayer(ClassicalMusic classicalMusic, RockMusic rockMusic, RapMusic rapMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
        this.rapMusic = rapMusic;
    }

    public void playMusic(Genre genre) {
        Random rnd = new Random();
        int songNumber = rnd.nextInt(3);

        if (genre.equals(Genre.CLASSICAL)) {
            System.out.println(classicalMusic.getSong().get(songNumber));
        } else if (genre == Genre.ROCK) {
            System.out.println(rockMusic.getSong().get(songNumber));
        }
        else
        System.out.println(rapMusic.getSong().get(songNumber));
    }

}
