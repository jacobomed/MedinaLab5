import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

public class CharCode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        String spaceCode = convertToSpaceCode(sentence);
        System.out.println("Encrypted text: " + spaceCode);

        String textWords = convertFromSpaceCode(spaceCode);
        System.out.println("Decrypted text: " + textWords);

        try {
            String key = "secret";
            String hmac = calculateHMAC(spaceCode, key);
            System.out.println("HMAC: " + hmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static String convertToSpaceCode(String text) {
        HashMap<Character, String> spaceCodeMap = new HashMap<>();
        spaceCodeMap.put('A', "@!"); spaceCodeMap.put('B', "@!!!"); spaceCodeMap.put('C', "!!!@");
        spaceCodeMap.put('D', "$@!"); spaceCodeMap.put('E', "#"); spaceCodeMap.put('F', "*^&");
        spaceCodeMap.put('G', "$$"); spaceCodeMap.put('H', "@#"); spaceCodeMap.put('I', "**");
        spaceCodeMap.put('J', "^%$"); spaceCodeMap.put('K', "$@#"); spaceCodeMap.put('L', "#&^^");
        spaceCodeMap.put('M', "@*&"); spaceCodeMap.put('N', "&%"); spaceCodeMap.put('O', "!!*!");
        spaceCodeMap.put('P', "*@%%"); spaceCodeMap.put('Q', "%%#"); spaceCodeMap.put('R', "@@^$");
        spaceCodeMap.put('S', "&#%"); spaceCodeMap.put('T', "^^"); spaceCodeMap.put('U', "*!@");
        spaceCodeMap.put('V', "$^%^"); spaceCodeMap.put('W', "^^^"); spaceCodeMap.put('X', "@*!%");
        spaceCodeMap.put('Y', "&@@#"); spaceCodeMap.put('Z', "*!^^"); spaceCodeMap.put('1', "!***");
        spaceCodeMap.put('2', "!!**"); spaceCodeMap.put('3', "#@@!"); spaceCodeMap.put('4', "@@$%!*");
        spaceCodeMap.put('5', "##^!&"); spaceCodeMap.put('6', "&&@*!"); spaceCodeMap.put('7', "!*#$");
        spaceCodeMap.put('8', "$&@!"); spaceCodeMap.put('9', "@@&$"); spaceCodeMap.put('0', "*@&##");

        StringBuilder spaceCodeBuilder = new StringBuilder();
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                spaceCodeBuilder.append(" ");
            } else if (spaceCodeMap.containsKey(c)) {
                spaceCodeBuilder.append(spaceCodeMap.get(c)).append(" ");
            }
        }

        return spaceCodeBuilder.toString().trim();
    }

    public static String convertFromSpaceCode(String text) {
        HashMap<String, Character> spaceToCharMap = new HashMap<>();
        spaceToCharMap.put("@!", 'A'); spaceToCharMap.put("@!!!", 'B'); spaceToCharMap.put("!!!@", 'C');
        spaceToCharMap.put("$@!", 'D'); spaceToCharMap.put("#", 'E'); spaceToCharMap.put("*^&", 'F');
        spaceToCharMap.put("$$", 'G'); spaceToCharMap.put("@#", 'H'); spaceToCharMap.put("**", 'I');
        spaceToCharMap.put("^%$", 'J'); spaceToCharMap.put("$@#", 'K'); spaceToCharMap.put("#&^^", 'L');
        spaceToCharMap.put("@*&", 'M'); spaceToCharMap.put("&%", 'N'); spaceToCharMap.put("!!*!", 'O');
        spaceToCharMap.put("*@%%", 'P'); spaceToCharMap.put("%%#", 'Q'); spaceToCharMap.put("@@^$", 'R');
        spaceToCharMap.put("&#%", 'S'); spaceToCharMap.put("^^", 'T'); spaceToCharMap.put("*!@", 'U');
        spaceToCharMap.put("$^%^", 'V'); spaceToCharMap.put("^^^", 'W'); spaceToCharMap.put("@*!%", 'X');
        spaceToCharMap.put("&@@#", 'Y'); spaceToCharMap.put("*!^^", 'Z'); spaceToCharMap.put("!***", '1');
        spaceToCharMap.put("!!**", '2'); spaceToCharMap.put("#@@!", '3'); spaceToCharMap.put("@@$%!*", '4');
        spaceToCharMap.put("##^!&", '5'); spaceToCharMap.put("&&@*!", '6'); spaceToCharMap.put("!*#$", '7');
        spaceToCharMap.put("$&@!",'8'); spaceToCharMap.put("@@&$", '9'); spaceToCharMap.put("*@&##", '0');

        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");

        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                if (spaceToCharMap.containsKey(letter)) {
                    result.append(spaceToCharMap.get(letter));
                }
            }
            result.append(" ");
        }

        return result.toString().trim();
    }

    public static String calculateHMAC(String data, String key)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = "HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        mac.init(secretKeySpec);
        byte[] hmacBytes = mac.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(hmacBytes);
    }
}








