package be.drissamri.performance.services;


public class PingServiceImpl implements PingService {

    @Override
    public String pong() {
        return "{\"pong\":true}";
    }
}
