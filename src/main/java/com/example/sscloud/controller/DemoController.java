package com.example.sscloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello, SSCloud!" , HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submit(){
        return new ResponseEntity<>("Submission successful!" , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(){
        return new ResponseEntity<>("Update successful!" , HttpStatus.OK);
    }

    @PatchMapping("/modify")
    public ResponseEntity<String> modify(){
        return new ResponseEntity<>("Modification successful!" , HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(){
        return new ResponseEntity<>("Removal successful!" , HttpStatus.OK);
    }

    @RequestMapping("/any")
    public ResponseEntity<String> anyMethod(){
        return new ResponseEntity<>("Any method accepted!" , HttpStatus.OK);
    }
}
