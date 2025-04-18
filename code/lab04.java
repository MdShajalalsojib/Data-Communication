import java.util.Scanner;
    public class lab04 {
    public static String encode(String data) {
        StringBuilder encoded = new StringBuilder();
        char signal = '1';   

        for (char bit : data.toCharArray()) {
            if (bit == '0') {
                 
                signal = (signal == '1') ? '0' : '1';  
                encoded.append(signal);
                signal = (signal == '1') ? '0' : '1';  
                encoded.append(signal);
            } else if (bit == '1') {
                 
                encoded.append(signal);               
                signal = (signal == '1') ? '0' : '1';  
                encoded.append(signal);
            }
        }
        return encoded.toString();
    }

    public static String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < encoded.length(); i += 2) {
            char first = encoded.charAt(i);
            char second = encoded.charAt(i + 1);
            if (first != second) decoded.append('0');  
            else decoded.append('1');                  
        }
        return decoded.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter binary data to encode (e.g., 1101): ");
        String data = sc.nextLine();

        if (!data.matches("[01]+")) {
            System.out.println("Invalid input! Please enter only 0s and 1s.");
            return;
        }
        String encoded = encode(data);
        String decoded = decode(encoded);

        System.out.println("Encoded Signal: " + encoded);
        System.out.println("Decoded Data  : " + decoded);

        if (decoded.equals(data)) {
            System.out.println("Decoding successful.");
        } else {
            System.out.println("Decoding failed.");
        }
    }
}


