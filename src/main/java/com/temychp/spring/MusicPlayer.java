package com.temychp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
@Autowired
@Qualifier ("classicalMusic")
    private Music music;
//    private ClassicalMusic classicalMusic;
//    private RockMusic rockMusic;
//@Autowired
//    public MusicPlayer(ClassicalMusic classicalMusic, RockMusic rockMusic) {
//        this.classicalMusic = classicalMusic;
//        this.rockMusic = rockMusic;
//    }

    public String playMusic() {
        return "Playing: " + music.getSong();
    }



}
