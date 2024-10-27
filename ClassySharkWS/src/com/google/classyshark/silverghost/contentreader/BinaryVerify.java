package com.google.classyshark.silverghost.contentreader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class BinaryVerify {
    public static final byte[] DEX_FILE_MAGIC = { 0x64, 0x65, 0x78, 0x0a }; // E.g "dex\n" signature
    public static boolean verifyMagic (File binary, byte[] targetBytes) {

        long position = 0;

        try (RandomAccessFile file = new RandomAccessFile(binary, "r")) {
            file.seek(position);
            byte[] buffer = new byte[targetBytes.length];
            int bytesRead = file.read(buffer);
            if (bytesRead == targetBytes.length) {
                if (Arrays.equals(buffer, targetBytes)) {
                    System.out.println("The " + targetBytes.length + " bytes of the file match.");
                    return true;
                } else {
                    System.out.println("The " + targetBytes.length + " bytes of the file do not match.");
                }
            } else {
                System.out.println("Failed to read " + targetBytes.length + " bytes in file.");
            }
        } catch (IOException e) {
            System.out.println("Error File Verify: " + e.getMessage());
        }
        return false;
    }
}
