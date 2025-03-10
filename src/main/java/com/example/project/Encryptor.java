package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    public static void main(String[] args) {
        String message = "og=t s titshauthj w e mtwiio rnowks   otetnt oigdrn wiI o";
        decryptMessage(message, 3);
    }
    
    public static int determineColumns(int messageLen, int rows) {
        if (messageLen == 0) {
            return 1;
        }
        return (int) Math.ceil((float) messageLen/rows);
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int messageLength = message.length();
        int columns = determineColumns(messageLength, rows);
        String[][] encryptArray = new String[rows][columns];

        int idx = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                idx++;
                if (idx > messageLength - 1) {encryptArray[i][j] = "=";}
                else {encryptArray[i][j] = message.substring(idx,idx+1);}
            }
        }

        return encryptArray;
    }

    public static String encryptMessage(String message, int rows){
        String[][] encryptArray = generateEncryptArray(message, rows);
        int columns = determineColumns(message.length(), rows);
        String newMessage = "";

        for (int i = columns - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                newMessage += encryptArray[j][i];
            }
        }
        // for testing purposes
        return newMessage;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        int messageLength = encryptedMessage.length();
        int columns = determineColumns(messageLength, rows);
        String[][] decryptArray = new String[rows][columns];
        int idx = 0;
    
        for (int i = columns - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                if (idx < messageLength) {
                    decryptArray[j][i] = encryptedMessage.substring(idx, idx + 1);
                    idx++;
                } else {
                    decryptArray[j][i] = "=";
                }
            }
        }
    
        String originalMessage = "";
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!decryptArray[i][j].equals("=")) {
                    originalMessage += decryptArray[i][j];
                }
            }
        }
        // for testing purposes
        System.out.println(originalMessage);
        return originalMessage;
    }
    
    
}