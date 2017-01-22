package com.example.oem.planzajec;

        import java.util.Random;

public class RandomNumber {

    public RandomNumber()
    {}

    public int rndNumber()
    {
        Random r = new Random();
        int t1 = r.nextInt(80 - 65) + 65;
        int t2 = r.nextInt(80 - 65) + 65;
        int t3 = r.nextInt(80 - 65) + 65;
        int wy = t1 * t3 * t2;
        return wy;
    }
}