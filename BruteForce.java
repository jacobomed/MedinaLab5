import java.util.Scanner;

public class BruteForce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter plain text: ");
        String userText = scanner.nextLine();
        bruteForceDecrypt(userText);

        scanner.close();
    }

    public static void bruteForceDecrypt(String userText) {

        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decryptedText = new StringBuilder();

            // Apply Caesar cipher decryption with current shift
            for (char character : userText.toCharArray()) {
                if (Character.isLetter(character)) {
                    char base = Character.isLowerCase(character) ? 'a' : 'A';
                    int originalAlphabetPosition = character - base;
                    int newAlphabetPosition = (originalAlphabetPosition - shift + 26) % 26;
                    char newCharacter = (char) (base + newAlphabetPosition);
                    decryptedText.append(newCharacter);
                } else {
                    decryptedText.append(character); // Handle characters that are not letters
                }
            }

            System.out.println("Shift " + shift + ": " + decryptedText);
        }
    }

}
