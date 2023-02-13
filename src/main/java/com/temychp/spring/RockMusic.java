package com.temychp.spring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RockMusic implements Music {

    List<String> rockSong = new ArrayList<>();

    {
        rockSong.add("rock song 1");
        rockSong.add("rock song 2");
        rockSong.add("rock song 3");
    }

    @Override
    public List<String> getSong() {
        return rockSong;
    }
}
