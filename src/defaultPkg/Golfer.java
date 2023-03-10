package defaultPkg;

public class Golfer {
    private String name;
    private boolean ride;

    public Golfer(String name, boolean ride) {
        this.name = name;
        this.ride = ride;
    }

    public Golfer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isRide() {
        return ride;
    }
}


