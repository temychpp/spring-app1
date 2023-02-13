package com.temychp.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");


     //   ClassicalMusic classicalMusic = context.getBean("musicBean", ClassicalMusic.class);
      //  System.out.println(classicalMusic.getSong());


//        Music music = context.getBean("rockMusic", Music.class);
//        MusicPlayer musicPlayer = new MusicPlayer(music);
//
//        Music music2 = context.getBean("classicalMusic", Music.class);
//        MusicPlayer musicPlayer2 = new MusicPlayer(music2);
////        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//        musicPlayer.playMusic();
//        musicPlayer2.playMusic();

//        System.out.println(testBean.getName());
//        System.out.println(musicPlayer.getName());
//        System.out.println(musicPlayer.getVolume());

        Computer computer = context.getBean("computer", Computer.class);
        System.out.println(computer);

        context.close();
    }
}
