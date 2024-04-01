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


        bruteForceDecrypt(encrypt);

        scanner.close();
    }

    /**
     * Jacobo Medina
     */

    public static void bruteForceDecrypt(String encrypt) {
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decryptedText = new StringBuilder();

            for (char character : CharCode.convertFromSpaceCode(encrypt).toCharArray()) {
                if (Character.isLetter(character)) {
                    char base = Character.isLowerCase(character) ? 'a' : 'A';
                    int originalAlphabetPosition = character - base;
                    int newAlphabetPosition = (originalAlphabetPosition - shift + 26) % 26; // Corrected decryption logic
                    char newCharacter = (char) (base + newAlphabetPosition);
                    decryptedText.append(newCharacter);
                } else {
                    decryptedText.append(character);
                }
            }
            String newlang = CaesarCipher.encrypt(decryptedText.toString(), shift);
            System.out.println("Shift " + shift + " as normal string: " + decryptedText);
            System.out.println("Shift " + shift + " as new language: " + newlang);
            System.out.println();
        }
    }

}