package service;

import api.IRemoteAPI;

public class MyService {

    private IRemoteAPI remoteAPI;

    public MyService(IRemoteAPI remoteAPI) {
        this.remoteAPI = remoteAPI;
    }

    public void process(boolean condition) {

        System.out.println("Processing request...");

        if (condition) {
            String response = remoteAPI.call();
            System.out.println(response);
        } else {
            System.out.println("Condition not met, skipping API call");
        }
    }
}