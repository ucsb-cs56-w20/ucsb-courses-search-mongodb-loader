package edu.ucsb.cs156.student;

public class Luhn {

    public static int checkDigit(int num) {
        
        int currentPlace = 0;
        int sumOfDigits = 0;

        while (num > 0) {
            currentPlace += 1;
            int leastSigDigit = num % 10;
            int thisValue = leastSigDigit;

            if (currentPlace % 2 == 1) {
                thisValue = leastSigDigit * 2;
            }

            if (thisValue <= 9)
                sumOfDigits += thisValue;
            else
                sumOfDigits += 1 + (thisValue % 10);
            num = num / 10;
        }

        return ( sumOfDigits * 9 ) % 10;
    }

    // public static void main(String [] args) {
    //     System.out.println(checkDigit(Integer.parseInt(args[0])));
    // }

}