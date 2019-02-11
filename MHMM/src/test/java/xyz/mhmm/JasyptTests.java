package xyz.mhmm;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTests {

	@Test
	public void test() {
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword("mhmm2019"); // 암호화 키(password)
		jasypt.setAlgorithm("PBEWithMD5AndDES");

		String dbIdEnc = jasypt.encrypt("hong");
		String dbIdText = jasypt.decrypt(dbIdEnc);
		String dbPwEnc = jasypt.encrypt("mhmm2019");
		String dbPwText = jasypt.decrypt(dbPwEnc);

		System.out.println("encryptedText:  " + dbIdEnc); // 암호화된 값
		System.out.println("plainText:  " + dbIdText); // 복호화된 값

		System.out.println("encryptedText:  " + dbPwEnc); // 암호화된 값
		System.out.println("plainText:  " + dbPwText); // 복호화된 값
	}

}
