import java.util.Scanner;

public class  lab {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== IP Address Utility ===");
        System.out.print("Enter a valid IP address (decimal form): ");
        String ipAddress = scanner.nextLine();

        if (!isValidIP(ipAddress)) {
            System.out.println("Invalid IP format!");
            return;
        }

        String ipClass = getIPClass(ipAddress);
        if (ipClass == null) {
            System.out.println("IP does not belong to Class A, B, or C.");
            return;
        }

        System.out.println("Detected IP Class: " + ipClass);

        System.out.println("Select an option:");
        System.out.println("1. Convert IP to Binary");
        System.out.println("2. Convert Binary to Decimal IP");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.println("Binary Format: " + decimalToBinary(ipAddress));
                break;
            case 2:
                System.out.print("Enter Binary IP (e.g. 11000000.10101000.00000001.00000001): ");
                String binaryInput = scanner.nextLine();
                if (isValidBinaryIP(binaryInput)) {
                    System.out.println("Decimal Format: " + binaryToDecimal(binaryInput));
                } else {
                    System.out.println("Invalid Binary IP Format!");
                }
                break;
            default:
                System.out.println("Invalid option!");
        }

        scanner.close();
    }

    static boolean isValidIP(String ip) {
        String[] octets = ip.split("\\.");
        if (octets.length != 4) return false;

        for (String octet : octets) {
            try {
                int val = Integer.parseInt(octet);
                if (val < 0 || val > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    static boolean isValidBinaryIP(String binaryIP) {
        String[] parts = binaryIP.split("\\.");
        if (parts.length != 4) return false;
        for (String part : parts) {
            if (part.length() != 8 || !part.matches("[01]{8}")) return false;
        }
        return true;
    }

    static String getIPClass(String ip) {
        int firstOctet = Integer.parseInt(ip.split("\\.")[0]);
        if (firstOctet >= 1 && firstOctet <= 126) return "A";
        else if (firstOctet >= 128 && firstOctet <= 191) return "B";
        else if (firstOctet >= 192 && firstOctet <= 223) return "C";
        else return null;
    }

    static String decimalToBinary(String ip) {
        StringBuilder binary = new StringBuilder();
        for (String part : ip.split("\\.")) {
            int val = Integer.parseInt(part);
            binary.append(String.format("%8s", Integer.toBinaryString(val)).replace(' ', '0')).append(".");
        }
        return binary.substring(0, binary.length() - 1);
    }

    static String binaryToDecimal(String binaryIP) {
        StringBuilder decimal = new StringBuilder();
        for (String part : binaryIP.split("\\.")) {
            decimal.append(Integer.parseInt(part, 2)).append(".");
        }
        return decimal.substring(0, decimal.length() - 1);
    }
}
