import java.util.Scanner;

/** Project: SoloLab 5
 * Purpose Details: To encrypt and decrypt strings using custom language, CaesarCipher, and brute force to decrypt
 * Course: IST 242
 * Author: Jacobo Medina
 * Date Developed: March 20, 2024
 * Last Date Changed: March 31, 2024
 * Rev: Last revised March 31st
 */
public class BruteForce {

    /**
     * Jacobo Medina
     */

    public static void main(String[] args) {
        /**
         *accepts plain text and prints out all possible shifts applied to the string
         */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String userText = scanner.nextLine();
        System.out.print("Enter shift value (integer): ");
        int shiftValue = scanner.nextInt();

        String encrypt = CaesarCipher.encrypt(userText, shiftValue);
        System.out.println("Your encrypted code: " + encrypt + " will now be decoded using brute force");


        bruteForceEncrypt(encrypt);

        scanner.close();
    }

    /**
     * Jacobo Medina
     */

    public static void bruteForceEncrypt(String encrypt) {

        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decryptedString = new StringBuilder();
            for (char character : CharCode.convertFromSpaceCode(encrypt).toCharArray()) {
                if (Character.isLetter(character)) {
                    char base = Character.isLowerCase(character) ? 'a' : 'A';
                    int originalAlphabetPosition = character - base;
                    int newAlphabetPosition = (originalAlphabetPosition - shift + 26) % 26;
                    char newCharacter = (char) (base + newAlphabetPosition);
                    decryptedString.append(newCharacter);
                } else {
                    decryptedString.append(character);
                }
            }


            String encryptedText = CaesarCipher.encrypt(CharCode.convertFromSpaceCode(encrypt), shift);
            System.out.println("Shift " + shift);
            System.out.println("Normal String: " + decryptedString);
            System.out.println("Encrypted String: " + CaesarCipher.encrypt(decryptedString.toString(), 5));
            System.out.println();
        }
    }


}









