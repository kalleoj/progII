import java.util.Random;

public class Cat extends Animal {

    public Cat(Random random) {
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


        /*
        TODO:
         Välja en random hastighet mellan minSpeed och maxSpeed
         och addera motsvarande förskjutning till distance.
         rng.nextDouble-metoden måste användas här.
         */
    }

    public boolean willRun() {

        double eating = rng.nextDouble();

        if ((eating == eatingProbability) || (eating < eatingProbability)) {
            return false;

        }
        /*
        TODO:
         Se om djuret äter eller inte. Använd ett slumpmässigt nummer och jämför
         det till eatingProbability. rng.nextDouble-metoden måste användas här.
         */
        return true;
    }

    public double getDistanceTravelled() {
        return distance;
    }

    private Random rng;
    private double distance;

    private double minSpeed = 3.3;
    private double maxSpeed = 3.9;
    private double eatingProbability = 0.03;

}
