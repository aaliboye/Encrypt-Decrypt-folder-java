/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.*;

/**
 *
 * @author mamadou
 */
public class Main {
    
    public static void Encrypt() { 
        try {
            CryptoImpl crypto = new CryptoImpl();
            SecretKey key = null;
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Choisissez la clé de chiffrement");
            int option = jfc.showOpenDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filePath = jfc.getSelectedFile().getAbsolutePath();
                key = crypto.getKey(filePath);
            }

            CipherImpl cipher = new CipherImpl();
            Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 1);
            jfc.setDialogTitle("Choisissez le fichier à chiffrer");
            option = jfc.showOpenDialog(jfc);
            String originalFilePath = "";
            String encryptedFilePath = "";

            if (option == JFileChooser.APPROVE_OPTION) {
                originalFilePath = jfc.getSelectedFile().getAbsolutePath();
            }

            jfc.setDialogTitle("Choisissez le chemin du fichier chiffrer pour l'enregistré");
            option = jfc.showSaveDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                encryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }
            cipher.encryptFile(c, originalFilePath, encryptedFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Decrypt() {
        try {
            CryptoImpl crypto = new CryptoImpl();
            SecretKey key = null;
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Choisissez la clé de déchiffrement");
            int option = jfc.showOpenDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filePath = jfc.getSelectedFile().getAbsolutePath();
                key = crypto.getKey(filePath);
            }
            CipherImpl cipher = new CipherImpl();
            Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 2);

            jfc.setDialogTitle("Choisissez le fichier à déchiffrer");
            option = jfc.showOpenDialog(jfc);
            String encryptedFilePath = "";
            String decryptedFilePath = "";

            if (option == JFileChooser.APPROVE_OPTION) {
                encryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }

            jfc.setDialogTitle("Choisissez le chemin du fichier déchiffrer pour l'enregistré");
            option = jfc.showSaveDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                decryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }

            cipher.decryptFile(c, encryptedFilePath, decryptedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GenKey() {
        CryptoImpl crypto = new CryptoImpl();
        SecretKey key = crypto.genKey(ICipher.keyAlgorithme, ICipher.keySize);
        SecretKey key2 = null;
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Choisissez le chemin pour enregistrer la clé");
        int option = jfc.showSaveDialog(jfc);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            crypto.saveKey(key, filePath);
        }
    }
    
    public static void EncryptFolder( String foldertoEncryptPath ) {
        try {
                CryptoImpl crypto = new CryptoImpl();
                SecretKey key = null ;
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Choisissez la clé de chiffrement");
                int option = jfc.showOpenDialog(jfc);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filePath = jfc.getSelectedFile().getAbsolutePath();
                    key = crypto.getKey(filePath);
                }
            CipherImpl cipher = new CipherImpl();
            Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 1);
            
           
           
            String encryptedFolderpath="";
            
            /*jfc.setDialogTitle("choisir le dossier a crypté");
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
            
            option = jfc.showSaveDialog(jfc);
            if(option == JFileChooser.APPROVE_OPTION){
                foldertoEncryptPath = jfc.getSelectedFile().getAbsolutePath();
            }*/
            
            jfc.setDialogTitle("choisir le dossier de destination");
             jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            option = jfc.showSaveDialog(jfc);
            if(option == JFileChooser.APPROVE_OPTION){
                encryptedFolderpath = jfc.getSelectedFile().getAbsolutePath();
            }
            
            
            cipher.encryptFolder(c, foldertoEncryptPath, encryptedFolderpath);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void DecryptFolder(String foldercrypte) {
         try {
                CryptoImpl crypto = new CryptoImpl();
                SecretKey key = null ;
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Choisissez la clé de dechiffrement");
                int option = jfc.showOpenDialog(jfc);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filePath = jfc.getSelectedFile().getAbsolutePath();
                    key = crypto.getKey(filePath);
                }
            CipherImpl cipher = new CipherImpl();
            Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 2);
            
           
            
            String decryptedFolderPath ="";
            
            /*jfc.setDialogTitle("choisir le dossier a decypte");
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
            
            option = jfc.showOpenDialog(jfc);
            if(option == JFileChooser.APPROVE_OPTION){
                foldercrypte = jfc.getSelectedFile().getAbsolutePath();
            }*/
            
            jfc.setDialogTitle("choisir le dossier de destination decrypté");
             jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            option = jfc.showSaveDialog(jfc);
            if(option == JFileChooser.APPROVE_OPTION){
                decryptedFolderPath = jfc.getSelectedFile().getAbsolutePath();
            }
            
            cipher.decryptFolder(c, decryptedFolderPath, foldercrypte);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //SecretKeyFactory
    public static void main(String[] args) {
        Vue ui = new Vue();
        ui.setTitle("The cipher");
        ui.setVisible(true);
    }
}
