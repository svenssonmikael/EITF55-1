import java.util.Random;
import java.math.BigInteger;

public class Proj1 {
    public static String RabinMiller(BigInteger input){
        if( (input.compareTo(BigInteger.valueOf(3)) <= 0 ) || (input.mod(BigInteger.TWO).equals(BigInteger.ZERO))) return "invalid input";

        BigInteger n = input;
        BigInteger s = input.subtract(BigInteger.ONE);
        BigInteger two = BigInteger.valueOf(2);
        int r = 0;
        while(s.mod(two).equals(BigInteger.ZERO)){
            r++;
            s = s.divide(two);
        }// Calculate params for writing n as (2^r)*s
        
        Random rand = new Random();
        BigInteger a = new BigInteger(n.bitLength() , rand).add(BigInteger.TWO);
        if(a.compareTo(n) > 1) a.subtract(n);
        if(a.compareTo(BigInteger.TWO) < 0) a.add(BigInteger.TWO); // Makes sure we are between [2, n-2]
        
        BigInteger x = a.modPow(s,n);

        if((x.equals(BigInteger.valueOf(1))) || (x.equals(input.subtract(BigInteger.ONE)))) return "ProbablyPrime";

        for(int j = 1; j<=r-1; j++){
            x = a.modPow(two.pow(j).multiply(s), n);
            //x = x.modPow(BigInteger.TWO, n);

            if(x.equals(BigInteger.ONE)) return "Composite";
            if(x.equals((input.subtract(BigInteger.ONE)))) return "ProbablyPrime";
        }
      return "Composite";
    }

    public static String randomBaseTest(BigInteger x){
        for(int i=0; i<20;i++){
            if(RabinMiller(x).equals("Composite")) return "Composite";
        }
        return "ProbablyPrime";
    }

    public static void main(String[] args) {
        System.out.println("(one base test) 53 is " + RabinMiller(BigInteger.valueOf(53)));
        System.out.println("101 is "+ randomBaseTest(BigInteger.valueOf(101))+" ,expected Prime");
        System.out.println("8191 is "+ randomBaseTest(BigInteger.valueOf(8191))+" ,expected Prime"); 
        System.out.println("15 is "+ randomBaseTest(BigInteger.valueOf(15))+" ,expected Composite");
        System.out.println("524287 is "+ randomBaseTest(BigInteger.valueOf(524287))+" ,expected Prime");
        System.out.println("221 is "+ randomBaseTest(BigInteger.valueOf(221))+" ,expected Composite");
        System.out.println("104729 is "+ randomBaseTest(BigInteger.valueOf(104729))+" ,expected Prime");
        System.out.println("997 is "+ randomBaseTest(BigInteger.valueOf(997))+" ,expected Prime");
        BigInteger s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
        s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
        s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
        s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
        s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
        s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
        s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
        s = new BigInteger(512, new Random());
        System.out.println(s+"  , " + randomBaseTest(s));
     }
}
