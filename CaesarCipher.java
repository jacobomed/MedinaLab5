import java.util.Scanner;

/** Project: SoloLab 5
 * Purpose Details: To encrypt and decrypt strings using custom language, CaesarCipher, and brute force to decrypt
 * Course: IST 242
 * Author: Jacobo Medina
 * Date Developed: March 20, 2024
 * Last Date Changed: March 31, 2024
 * Rev: Last revised March 31st
 */
public class CaesarCipher {

    /**
     * Jacobo Medina
     */
    public static void main(String[] args) {

    /**
     *Main function accepts a string to encrypt and a shift value. Prints out the encrypted and decrypted strings
     */


        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter shift value (integer): ");
        int shiftValue = scanner.nextInt();

        String encryptedText = encrypt(plaintext, shiftValue);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, shiftValue);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();

    }

    /**
     * Jacobo Medina
     */
    public static String encrypt(String plaintext, int shift) {

    /**
     *converts plain text to new shifted stringa nd applies space code on top
     */



            StringBuilder encryptedText = new StringBuilder();

            for (char character : plaintext.toCharArray()) {
                if (Character.isLetter(character)) {
                    char base = Character.isLowerCase(character) ? 'a' : 'A';
                    int originalAlphabetPosition = character - base;
                    int newAlphabetPosition = (originalAlphabetPosition + shift + 26) % 26;
                    char newCharacter = (char) (base + newAlphabetPosition);
                    encryptedText.append(newCharacter);
                } else {
                    encryptedText.append(character);
                }
            }

            // Convert encrypted text to space code
            String spaceCode = CharCode.convertToSpaceCode(encryptedText.toString());

            return spaceCode;
        }


    /**
     * Jacobo Medina
     */

    public static String decrypt(String encryptedText, int shift) {
        /**
         *converts encrypted text to plain text from space code. applies negative shift to revert back to plain text
         */

        StringBuilder decryptedText = new StringBuilder();

        // Convert plaintext to space code
        String decryptedCode = CharCode.convertFromSpaceCode(encryptedText);

        for (char character : decryptedCode.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition - shift + 26) % 26;
                char newCharacter = (char) (base + newAlphabetPosition);
                decryptedText.append(newCharacter);
            } else {
                decryptedText.append(character);
            }
        }

        return decryptedText.toString();
    }

}


