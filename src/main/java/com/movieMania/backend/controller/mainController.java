package com.movieMania.backend.controller;

import com.movieMania.backend.BackendApplication;
import com.movieMania.backend.Entity.urlReqBody;
import com.movieMania.backend.service.mainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Stack;

@RestController
@RequestMapping("/gateway")
@CrossOrigin
public class mainController {


    @Autowired
    private mainService mainService;


    @Autowired
    private BackendApplication backendApplication;

    @PostMapping("/setUrl")
    private String setValue(@RequestBody urlReqBody urlReqBody){
        return backendApplication.setVal(urlReqBody.getUrl());
    }

    @GetMapping("/get")
    private String getValue(){
       return backendApplication.getVal();
    }




    @GetMapping("/startMeeting")
    private String setType(){
        return backendApplication.setType();
    }

    @GetMapping("/stopMeeting")
    private String stop(){
        return backendApplication.stop();
    }



}
