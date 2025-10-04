import java.util.*;

class SimpleColumn {
    public static void main(String sap[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Plain Text (Enter in Lower Case): ");
        String message = sc.nextLine().replaceAll("\\s+", "").toLowerCase(); // removing spaces & lowering case
        System.out.print("\nEnter key in numbers (e.g., 3142): ");
        String key = sc.next();
        int columnCount = key.length();
        int rowCount = (int) Math.ceil((double) message.length() / columnCount);
        char[][] plainText = new char[rowCount][columnCount];
        char[][] cipherText = new char[rowCount][columnCount];
        System.out.print("\n--------Encryption--------\n");
        cipherText = encrypt(plainText, cipherText, message, rowCount, columnCount, key);
        String ct = "";
        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < rowCount; j++) {
                ct += cipherText[j][i];
            }
        }
        System.out.println("\nCipher Text : " + ct);
        System.out.print("\n\n--------Decryption--------\n");
        plainText = decrypt(new char[rowCount][columnCount], cipherText, rowCount, columnCount, key);
        String pt = "";
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (plainText[i][j] != 'x') {
                    pt += plainText[i][j];
                }
            }
        }
        System.out.println("\nPlain Text : " + pt);
        sc.close();
    }
    static char[][] encrypt(char[][] plainText, char[][] cipherText, String message, int rowCount, int columnCount, String key) {
        int k = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (k < message.length()) {
                    plainText[i][j] = message.charAt(k++);
                } else {
                    plainText[i][j] = 'x'; // padding with 'x'
                }
            }
        }
        System.out.println("Plain Array (row-wise):");
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(plainText[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < columnCount; i++) {
            int currentCol = (key.charAt(i) - '0') - 1; // convert key digit to column index
            for (int j = 0; j < rowCount; j++) {
                cipherText[j][i] = plainText[j][currentCol];
            }
        }
        System.out.println("Cipher Array (column-wise):");
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(cipherText[i][j] + "\t");
            }
            System.out.println();
        }
        return cipherText;
    }
    static char[][] decrypt(char[][] plainText, char[][] cipherText, int rowCount, int columnCount, String key) {
        for (int i = 0; i < columnCount; i++) {
            int currentCol = (key.charAt(i) - '0') - 1;
            for (int j = 0; j < rowCount; j++) {
                plainText[j][currentCol] = cipherText[j][i];
            }
        }
        System.out.println("Decrypted Plain Array (row-wise):");
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(plainText[i][j] + "\t");
            }
            System.out.println();
        }
        return plainText;
    }
}