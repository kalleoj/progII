import java.util.Random;

public class köttbulla extends Animal {
    /**
     * The constructor for the Animal. Do not modify.
     *
     * @param random the random number generator which MUST
     *               be the same as the one used in the class Competition
     */
    public köttbulla(Random random) {
        super(random);
    }

    @Override
    public void run() {

    }

    @Override
    public boolean willRun() {
        return false;
    }

    @Override
    public double getDistanceTravelled() {
        return 0;
    }
}
