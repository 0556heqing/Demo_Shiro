
package com.heqing.testShiro;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

public class TestBase {

	@Test
	public void Sha256Hash() {
		String pass = "123456";
		String password = new Sha256Hash(pass).toHex();
		System.out.println(password);
	}
}
