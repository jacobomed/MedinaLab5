import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter shift value (integer): ");
        int shiftValue = scanner.nextInt();

        String encryptedText = encrypt(plaintext, shiftValue);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + plaintext);

        scanner.close();
    }

    public static String encrypt(String plaintext, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        // Convert plaintext to space code
        String spaceCode = CharCode.convertToSpaceCode(plaintext);

        // Apply Caesar cipher encryption on space code
        for (char character : spaceCode.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                char newCharacter = (char) (base + newAlphabetPosition);
                encryptedText.append(newCharacter);
            } else {
                encryptedText.append(character);
            }
        }

        return encryptedText.toString();
    }


    public static String decrypt(String plaintext, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        // Convert plaintext to space code
        String spaceCode = CharCode.convertToSpaceCode(plaintext);

        // Apply Caesar cipher encryption on space code
        for (char character : spaceCode.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition - shift) % 26;
                char newCharacter = (char) (base + newAlphabetPosition);
                decryptedText.append(newCharacter);
            } else {
                decryptedText.append(character);
            }
        }

        return decryptedText.toString();
    }

}