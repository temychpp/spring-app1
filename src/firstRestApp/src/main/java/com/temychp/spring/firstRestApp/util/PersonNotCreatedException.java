package com.temychp.spring.firstRestApp.util;

public class PersonNotCreatedException extends RuntimeException{

    public PersonNotCreatedException (String msg){
        super(msg);
    }

}
