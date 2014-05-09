package io.zhpooer.estore.utils;

import java.util.UUID;

public class UploadUtil {
    public static String generateRandomFilename(String filename){
        String ext = filename.substring(filename.lastIndexOf("."));
        return UUID.randomUUID().toString() + ext;
    }
    
    public static String generateRadnomDir(String filename) {
        int hashCode = filename.hashCode();
        int dir1 = hashCode&0xf;
        int dir2 = (hashCode&0xf0) >> 4;
        return "/" + dir1 + "/" + dir2; 
    }
}
