package com.example.seanshi.android;

/*
import android.util.Base64;


import org.spongycastle.jce.ECNamedCurveTable;
import org.spongycastle.jce.ECPointUtil;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
*/

/**
 * Created by Alex.Prince on 11/13/2015.
 */
public class CryptoUtilities {
    /*
    private static final int IV_LENGTH = 16;
    private static final int SHA_256_DIGEST_LENGTH = 32;
    private static final String KEY_ALGORITHM = "secp521r1";
    private static final byte[] Magic = new byte[]{69, 67, 75, 53, 66, 0,0,0};

    public static byte[] generateHMACHSA256(byte[] value, byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        return generateHMAC("HmacSHA256", keyBytes, value);
    }

    public static byte[] generateHMAC(String alg, byte[] keyBytes, byte[] value) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey;
        Mac mac = Mac.getInstance(alg);
        secretKey = new SecretKeySpec(keyBytes,mac.getAlgorithm());
        mac.init(secretKey);
        byte[] hmac = mac.doFinal(value);
        return hmac;
    }

    public static byte[] DoAES256CBC(byte[] value, byte[] key, byte[] iv, int mode) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        SecretKeySpec newKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(mode, newKey, ivSpec); // Cipher.ENCRYPT_MODE / Cipher.DECRYPT_MODE
        return cipher.doFinal(value);
    }

    public static byte[] generateIV() {
        return generateRandom(IV_LENGTH);
    }

    public static byte[] generateRandom(int size) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] bytes = new byte[size];
            random.nextBytes(bytes);
            return bytes;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static KeyPair GenerateECDHKeyPair(){
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDH","SC");
            kpg.initialize(new ECGenParameterSpec(KEY_ALGORITHM));
            return kpg.generateKeyPair();
        }catch(Exception e) {
        }
        return null;
    }

    public static byte[] getBytes(Object object) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(object);
        return out.toByteArray();
    }

    public static <t> t fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        return  (t) is.readObject();
    }

    //Ingenico functions
    public static byte[] generateFrameFromEncryptedData(byte[] data, byte[] key){
        if(data.length <= SHA_256_DIGEST_LENGTH + IV_LENGTH){
            return null;
        }
        byte[] receivedHMAC = Arrays.copyOfRange(data, data.length - SHA_256_DIGEST_LENGTH, data.length);
        byte[] IVAndMessageData = Arrays.copyOfRange(data, 0, data.length - SHA_256_DIGEST_LENGTH);
        byte[] calculatedHmac;
        try {
            calculatedHmac = generateHMACHSA256(IVAndMessageData, key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            return null;
        }

        if (!Arrays.equals(receivedHMAC, calculatedHmac)) {
            //return null;
        }

        byte[] iv = Arrays.copyOfRange(IVAndMessageData, 0, IV_LENGTH);
        byte[] message = Arrays.copyOfRange(IVAndMessageData,IV_LENGTH, IVAndMessageData.length);

        byte[] decryptedMessage = null;
        try {
            decryptedMessage = DoAES256CBC(message, key, iv, Cipher.DECRYPT_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedMessage;
    }

    public static byte[] generateEncryptedFrameWithData(byte[] data, byte[] key) {
        return generateEncryptedFrameWithData(data, key, generateIV());
    }

    public static byte[] generateEncryptedFrameWithData(byte[] data, byte[] key, byte[] IV){
        byte[] mutableData = null;
        try {
            byte[] cipherText =  DoAES256CBC(data, key, IV, Cipher.ENCRYPT_MODE);

            mutableData = new byte[IV.length + cipherText.length];
            System.arraycopy(IV, 0, mutableData ,0, IV.length);
            System.arraycopy(cipherText, 0, mutableData ,IV.length, cipherText.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        byte[] hmac;
        try {
            hmac = generateHMACHSA256(mutableData, key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        byte[] returnData = new byte[mutableData.length + hmac.length];

        System.arraycopy(mutableData, 0, returnData ,0, mutableData.length);
        System.arraycopy(hmac, 0, returnData ,mutableData.length, hmac.length);
        return returnData;
    }

    public static byte[] generateSecret(KeyPair privateKeyPair, PublicKey pubkey) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException {

        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", "SC");
        keyAgreement.init(privateKeyPair.getPrivate());
        keyAgreement.doPhase(pubkey, true);

        //hash the output to get the key for AES-256
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        return sha.digest(keyAgreement.generateSecret());
    }

    public static PublicKey getPublicKeyFromBase64Bytes(byte[] keyBytesEncoded) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {

        byte[] keyBytes = Base64.decode(keyBytesEncoded, Base64.DEFAULT);
        byte[] pubKey = new byte[keyBytes.length - 7]; // strip off the .net headers
        pubKey[0] = 0x04; // add header everything but .net is expecting
        System.arraycopy(keyBytes, 8, pubKey, 1, pubKey.length -1); //move the key over

        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec(KEY_ALGORITHM);
        KeyFactory kf = KeyFactory.getInstance("ECDH", "SC");
        ECNamedCurveSpec params = new ECNamedCurveSpec(KEY_ALGORITHM, spec.getCurve(), spec.getG(), spec.getN());

        //get the EC curve point from the key to create the Java key object
        ECPoint point =  ECPointUtil.decodePoint(params.getCurve(), pubKey);

        ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(point, params);
        ECPublicKey pk = (ECPublicKey) kf.generatePublic(pubKeySpec);
        return pk;
    }

    public static byte[] encodePublicKeyBase64String(PublicKey key) throws NoSuchProviderException, NoSuchAlgorithmException {

        //arrays coming from the key itself, may not be correct length for what .net is expecting
        byte[] keyX = ((java.security.interfaces.ECPublicKey)key).getW().getAffineX().toByteArray();
        byte[] keyY =  ((java.security.interfaces.ECPublicKey)key).getW().getAffineY().toByteArray();

        //66 byte left 0 padded arrays, Java initializes to 0's
        byte[] X = new byte[66];
        byte[] Y = new byte[66];
        System.arraycopy(keyX, 0, X, 66-keyX.length, keyX.length); //using array copy to pad
        System.arraycopy(keyY, 0, Y, 66-keyY.length, keyY.length); //using array copy to pad

        byte[] returnValue = new byte[Magic.length + X.length + Y.length];

        //add the .net Magic Bytes
        System.arraycopy(Magic, 0, returnValue, 0, Magic.length);
        //append X
        System.arraycopy(X, 0, returnValue, Magic.length, X.length);
        //append Y
        System.arraycopy(Y, 0, returnValue, Magic.length + X.length, Y.length);

        return Base64.encode(returnValue, Base64.DEFAULT);
    }

    //TODO: do we still need this?
    public static int[] toUnsigned(byte[] signedArray){
        int[] unsignedArray = new int[signedArray.length];
        for(int i = 0; i< signedArray.length; i++){
            unsignedArray[i] = (signedArray[i]  & 0xFF);
        }
        return unsignedArray;
    }
*/
}
