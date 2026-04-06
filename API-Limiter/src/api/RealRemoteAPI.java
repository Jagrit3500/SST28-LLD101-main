package api;

public class RealRemoteAPI implements IRemoteAPI {

    @Override
    public String call() {
        return "Remote API Response";
    }
}