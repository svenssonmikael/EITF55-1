import java.util.Random;
import java.math.BigInteger;

public class Proj1 {
    public static String RabinMiller(BigInteger input){
        if( (input.compareTo(BigInteger.valueOf(3)) <= 0 ) || (input.mod(BigInteger.TWO).equals(BigInteger.ZERO))) return "Invalid input";

        BigInteger n = input;
        BigInteger s = input.subtract(BigInteger.ONE);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger r = BigInteger.ZERO;                 //MICKE: changed from int to BigInteger
        while(s.mod(two).equals(BigInteger.ZERO)){
            r = r.add(BigInteger.ONE);                  //MICKE: changed from int to BigInteger
            s = s.divide(two);                          
        }// Calculate params for writing n as (2^r)*s
        
        Random rand = new Random();
        BigInteger a = new BigInteger(n.bitLength() , rand).add(BigInteger.TWO);
        if(a.compareTo(n) > 1) a.subtract(n);
        if(a.compareTo(BigInteger.TWO) < 0) a.add(BigInteger.TWO); // Makes sure we are between [2, n-2]
        
        BigInteger x = a.modPow(s,n);

        if((x.equals(BigInteger.valueOf(1))) || (x.equals(input.subtract(BigInteger.ONE)))) return "ProbablyPrime";

        for(BigInteger j = BigInteger.ONE; j.compareTo(r) < 0; j = j.add(BigInteger.ONE)){                  //MICKE: did som magic here to the assignment "j = j.add...." instead of "j++
            x = a.modPow(two.pow(j.intValue()).multiply(s), n);         //MICKE: added intValue() after above changes
            //x = x.modPow(BigInteger.TWO, n);

            if(x.equals(BigInteger.ONE)) return "Composite";
            if(x.equals((input.subtract(BigInteger.ONE)))) return "ProbablyPrime";
        }
      return "Composite";
    }

    public static boolean randomBaseTest(BigInteger x){         //MICKE: changed return type to boolean for convenience
        for(int i=0; i<20;i++){                                 //true is probably prime, false is composite
            if(RabinMiller(x).equals("Composite") || RabinMiller(x).equals("Invalid input") ) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        
        //MICKE: your job is to create primes, 100 of them. Therefore the loop below. Your problem was mainly that
        // you got stuck on the invalid input return in the beginning of the algorithm. So as soon as the mega number
        // that was generated was a even number, problems occured. However, some magic here and there and it seems to 
        // go well :) Read the comments to see what I did. I also changed the return type of the randomBaseTest.

        for(int i=0; i<100; i++){                               
            BigInteger s = new BigInteger(512, new Random());
            while(!randomBaseTest(s)){
                s = new BigInteger(512, new Random());
            }
            System.out.println("PRIME TIME:");
            System.out.println(s+" is probably prime!");
            System.out.println("Progress: "+i+"%");
        }
        System.out.println("Done! 100 primes generated!");
     }
}
