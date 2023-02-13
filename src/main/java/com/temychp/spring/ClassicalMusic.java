package com.temychp.spring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ClassicalMusic implements Music {

    Random rnd = new Random();

    private List<String>classicSong = new ArrayList<>();

    {
        classicSong.add("classic song1");
        classicSong.add("classic song2");
        classicSong.add("classic song3");
    }

    @Override
    public List<String> getSong() {
       return classicSong;
    }
}
