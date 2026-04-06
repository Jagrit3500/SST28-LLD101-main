package controller;

import service.MyService;

public class MyController {

    private MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    public void handleRequest(boolean condition) {
        service.process(condition);
    }
}