package com.juc.juc_13_reentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @description
 * @author: shangqj
 * @date: 2023/4/28
 * @version: 1.0
 */
public class encode {
    //@Autowired
    private static StringEncryptor encryptor;

    private static String password = "WW9g1O2uybowscSGN878BNWqfZc/Fgzv3TxDpAwp4T1Af5KGMG+kOqTWsiME5oXjQXw3lhwnu2FHH8uaa2Ep5yaZgkHFNTou";

    @Slf4j
    @RestController
    public static class EncryptorCompant {

        // @Value("${password}")
        //private String password;



        //@PostMapping("/encryptor")
        public void encryptorFunction(){
            //log.info("加密后密文,{}",encryptor.encrypt(String.valueOf(password)));
            log.info("原始明文，{}",password);
        }

    }

    public static void main(String[] args) {
        //System.out.println(encryptor.encrypt(String.valueOf(password)));
        System.out.println(password);

    }
}
