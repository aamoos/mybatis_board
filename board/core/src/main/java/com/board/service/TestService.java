package com.board.service;

import org.springframework.stereotype.Service;

@Service("testService")
public class TestService {

    public void test(){
        System.out.println("test!!!!");
    }

}