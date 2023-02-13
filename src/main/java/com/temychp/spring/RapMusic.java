package com.temychp.spring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RapMusic implements Music{

    List<String>rapSong = new ArrayList<>();
    {
        rapSong.add("rap song 1");
        rapSong.add("rap song 2");
        rapSong.add("rap song 3");
    }


    @Override
    public List<String>  getSong() {
        return rapSong;
    }
}
