import java.util.Random;

public class Rabbit extends Animal {

    public Rabbit (Random random) {
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

    private double minSpeed = 0.2;
    private double maxSpeed = 6.6;
    private double eatingProbability = 0.02;

}
