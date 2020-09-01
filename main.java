import java.util.*;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int run = 1;
        String decodedPhrase = "";
        String encodedPhrase = "";
        String[] mainAlphabet = new String[26];
        String[] encodedAlphabet = new String[26];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i++) {
            mainAlphabet[i] = "" + alphabet.charAt(i);      //fills mainAlphabet array with normal alphabet
        }
        while(run == 1){    //loops program until user exit

            int choice;
            System.out.println("Encode[e], decode[d], or exit[q]? ");
            String userChoice = scanner.next();
            if (userChoice.equalsIgnoreCase("e")) {
                System.out.println("Enter your own shift[s] or random shift[r]? ");
                String userResponse = scanner.next();
                if(userResponse.equalsIgnoreCase("s")) {
                    int shift1;
                    System.out.println("Enter shift: ");
                    shift1 = scanner.nextInt();
                    scanner.nextLine();
                    shiftAlphabet(shift1, encodedAlphabet, mainAlphabet, alphabet); //shifts alphabet
                    System.out.println();
                    System.out.println("Enter phrase to be encoded: ");
                    String userPhrase = scanner.nextLine();
                    System.out.println(returnEncodedPhrase(userPhrase, mainAlphabet, encodedPhrase, encodedAlphabet));


                }
                else if(userResponse.equalsIgnoreCase("r")) {
                    int shift2 = (int) (Math.random() * 25) + 1;
                    shiftAlphabet(shift2, encodedAlphabet, mainAlphabet, alphabet);
                    System.out.println("Enter phrase to be encoded: ");
                    scanner.nextLine();
                    String userPhrase2 = scanner.nextLine();
                    System.out.println(returnEncodedPhrase(userPhrase2, mainAlphabet, encodedPhrase, encodedAlphabet));

                }
                else {
                    System.out.println("Not a valid choice :(");
                }


            } else if (userChoice.equalsIgnoreCase("d")) { //decode
                int shift;
                scanner.nextLine();
                System.out.println("Shift known? (y/n) ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("y")) {  //shift is known
                    System.out.println("Enter shift: ");
                    shift = scanner.nextInt();
                    scanner.nextLine();
                    shiftAlphabet(shift, encodedAlphabet, mainAlphabet, alphabet); //shifts alphabet
                    System.out.println();
                    System.out.println("Enter phrase to be decoded: ");
                    String phrase = scanner.nextLine(); //stores phrase to be decoded
                    System.out.println(returnDecodedPhrase(phrase, mainAlphabet, decodedPhrase, encodedAlphabet));     //prints decoded phrase


                } else {  //shift is not known
                    shift = 1;  //start shift at 1...shift 0 would be the start
                    System.out.println("Enter phrase to be decoded: ");
                    String phrase = scanner.nextLine();     // store phrase to be decoded
                    while (shift != 27) {    // goes through shift 26 times to include start shift at the end
                        shiftAlphabet(shift, encodedAlphabet, mainAlphabet, alphabet);  //shifts alphabet
                        System.out.println(returnDecodedPhrase(phrase, mainAlphabet, decodedPhrase, encodedAlphabet));   //returns encoded phrase
                        shift++;

                    }
                }

            } else {
                System.out.println("Bye!");
                run = 0;

            }
        }






    }

    public static void shiftAlphabet(int shift, String[] encodedAlphabet, String[] mainAlphabet, String alphabet
                                ) {
        int x = shift;

        for(int i = 0; i < 26; i++) {
            int mod = (i+shift) % 26;
            encodedAlphabet[mod] = mainAlphabet[i];
        }


        //for (int i = (26 - shift); i < 26; i++) {   // 26 - shift = shift back so letters after index 25 will go to index 0
         //   int index = shift - x;  // increments beginning of array index...0...1...2...n
        //    encodedAlphabet[index] = mainAlphabet[i]; //shifts later mainAlphabet letters to beginning indexes of encodedAlphabet
        //    x--;    //decrements x to increase array index from 0...1...2...n

       // }
      //  for (int m = 0; m + shift < alphabet.length(); m++) { // shifts beginning of alphabet until last index is reached
      //      encodedAlphabet[m + shift] = mainAlphabet[m]; //encodedAlphabet shifts of by m(letter index) + shift...arrays are lined up
     //  }


    }
    public static String returnDecodedPhrase(String phrase, String[] mainAlphabet, String decodedPhrase, String[] encodedAlphabet ) {
        for(int letter = 0; letter < phrase.length(); letter++) {  //letter of word program is on
            for(int ab = 0; ab < mainAlphabet.length; ab++) { // index of mainAlphabet program is on (to decode)
                if(mainAlphabet[ab].equalsIgnoreCase(String.valueOf(phrase.charAt(letter)))) { //if letter matches letter at index ab in
                                                                                    //mainAlphabet...
                    decodedPhrase += encodedAlphabet[ab]; //letter from the same index as main but instead used for encodedAlphabet is added to phrase
                }
            }
        }
        return decodedPhrase;
    }

    public static String returnEncodedPhrase(String phrase, String[] mainAlphabet, String encodedPhrase, String[] encodedAlphabet) {
        for(int letter = 0; letter < phrase.length(); letter++) {
            for(int ab = 0; ab < encodedAlphabet.length; ab++) {
                if(encodedAlphabet[ab].equalsIgnoreCase((String.valueOf(phrase.charAt(letter))))) { //compares letter with encodedAlphabet
                    encodedPhrase += mainAlphabet[ab];  // index that matches encodedAlphabet is used
                }
            }
        }
        return encodedPhrase;
    }
}
