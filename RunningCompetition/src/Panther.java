import java.util.Random;

public class Panther extends Animal {

    public Panther(Random random) {
        super(random);
        distance = 0;
    }

    /*
     * Nedan finns implementeringarna för parent-klassens run, wilLRun och
     * getDistanceTravelled.
     */

    public void run() {

        double hastighet = minSpeed + (maxSpeed-minSpeed)*rng.nextDouble();

        hastighet +=distance;



    }

    public boolean willRun() {

        double eating = rng.nextDouble();

        if ((eating == eatingProbability) || (eating < eatingProbability)) {
            return false;

        }

        return true;
    }

    public double getDistanceTravelled() {
        return distance;
    }

    private Random rng;
    private double distance;

    private double minSpeed = 3.7;
    private double maxSpeed = 5.3;
    private double eatingProbability = 0.23;

}
