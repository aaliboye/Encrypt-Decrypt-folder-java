/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;


/**
 *
 * @author aaliboye
 */
public class CipherImpl implements ICipher {

    @Override
    public boolean encryptFile(Cipher c, String originalFilePath, String encryptFilePath) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(originalFilePath));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(encryptFilePath));
            CipherOutputStream cos = new CipherOutputStream(bos, c);
            int r_lines = 0;
            while ((r_lines = bis.read()) != -1) {
                cos.write(r_lines);
            }
            cos.close();
            bos.close();
            bis.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean decryptFile(Cipher c, String encryptFilePath, String decryptedFilePath) {
        try {
            FileInputStream file = new FileInputStream(encryptFilePath);
            BufferedInputStream bais = new BufferedInputStream(file);
            CipherInputStream cis = new CipherInputStream(bais, c);
            BufferedOutputStream decrypt = new BufferedOutputStream(new FileOutputStream(decryptedFilePath));
            int lines = 0;
            while ((lines = cis.read()) != -1) {
                System.out.print((char) lines);
                decrypt.write((char) lines);
            }
            decrypt.close();
            cis.close();
            cis.close();
            file.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Cipher getCipher(String cipherAlgorithm, IvParameterSpec iv, SecretKey key, int mode) {
        try {
            Cipher c = Cipher.getInstance(cipherAlgorithm);
            if (mode == 1) {
                c.init(Cipher.ENCRYPT_MODE, key, iv);
            } else {
                c.init(Cipher.DECRYPT_MODE, key, iv);
            }
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean encryptFolder(Cipher c, String foldertoEncryptPath, String foldercrypte){
        try {
            File rep = new File(foldertoEncryptPath);
            String repath=null;
            File[] listefile = rep.listFiles();          
            for(File fic : listefile){
                if(fic.isFile()){
                    String originalFilePath = fic.getAbsolutePath();
                    repath = rep.getAbsolutePath();
                    String decryptedFilePath = foldercrypte+originalFilePath.substring(repath.length());
                    encryptFile(c, originalFilePath, decryptedFilePath);
                }
                else{
                    foldertoEncryptPath = fic.getAbsolutePath();
                    encryptFolder(c, foldertoEncryptPath, foldercrypte);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
        public boolean decryptFolder(Cipher c, String decryptFolderPath, String foldercrypte){
        try {
            File rep = new File(foldercrypte);
            String repath=null;
            File[] listefile = rep.listFiles();          
            for(File fic : listefile){
                if(fic.isFile()){
                    String encryptedFile = fic.getAbsolutePath();
                    repath = rep.getAbsolutePath();
                    String decryptedFilePath = decryptFolderPath+encryptedFile.substring(repath.length());
                    decryptFile(c, encryptedFile, decryptedFilePath);
                }
                else{
       
                    decryptFolder(c, decryptFolderPath,  foldercrypte);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return false;
    }
}
