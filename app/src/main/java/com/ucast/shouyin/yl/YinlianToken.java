package com.ucast.shouyin.yl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pj on 2019/4/18.
 */
public class YinlianToken {

    public static String appId = "f0ec96ad2c3848b5b810e7aadf369e2f";
    public static String timestamp = "20170616093021";
    public static String nonce = "1731378";
    public static String body = "{\r\n" +
            "    \"merchantCode\": \"123456789900081\",\r\n" +
            "    \"terminalCode\": \"00810001\",\r\n" +
            "    \"systemTraceNum\": \"009455\"\r\n" +
            "}";
    public static byte[] data = body.getBytes();
    public static InputStream is = new ByteArrayInputStream(data);
    public static String str1_C = appId+timestamp+nonce+testSHA256(is);
    public static String appKey_D = "775481e2556e4564985f5439a5e6a277";

    private static String testSHA256(InputStream is){
        try {
            return DigestUtils.sha256Hex(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String testSHA256_STRING(String str){
        return DigestUtils.sha256Hex(str);
    }

    public static byte[] hmacSHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = "HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data);
    }
    public static void mainTest() throws Exception {
        byte[] data = body.getBytes();
        InputStream is = new ByteArrayInputStream(data);
        System.out.println("bodyDigest:\n" + testSHA256(is));

        System.out.println("str1_C:\n" + str1_C);

        System.out.println("appKey_D:\n" + appKey_D);

        byte[] localSignature = hmacSHA256(str1_C.getBytes(), appKey_D.getBytes());
        String localSignatureStr = Base64.encodeBase64String(localSignature);
        System.out.println("signature:\n" + localSignatureStr);

        System.out.println("Authorization:\n" + "OPEN-BODY-SIG AppId="+"\""+appId+"\""+", Timestamp="+"\""+timestamp+"\""+", Nonce="+"\""+nonce+"\""+", Signature="+"\""+localSignatureStr+"\"");
    }


}
