package com.restful.utils;

import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.DESKeyGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DESHelper {
	
	private static final String CIPHER_TYPE = "DES";
	private DESHelper() {
		
	}

	static {
		Security.addProvider( new BouncyCastleProvider() );	
	}
	
	/**
	 * 
	 * @return
	 */
	public static byte[] generateKey() {
		DESKeyGenerator keyGen = new DESKeyGenerator();
		long stime = System.currentTimeMillis();
		byte[] seed = new byte[8];
		for ( int i =  0; i < 8; ++i ) {
			seed[i] = (byte)( stime >> ( i * 8 ) );
		}				
		SecureRandom srnd = new SecureRandom( seed );
		KeyGenerationParameters param = new KeyGenerationParameters( srnd, 64 );
		keyGen.init(param);
		return keyGen.generateKey();		
	}
	
	/**
	 * 
	 * @param key
	 * @param data
	 * @return
	 */
	public final static byte[] encode( byte[] key, byte[] data ) {
		return doFinal( Cipher.ENCRYPT_MODE, key, data );
	}
	
	public final static byte[] decode( byte[] key, byte[] data ) {
		return doFinal( Cipher.DECRYPT_MODE, key, data );
	}
	
	private static byte[] doFinal( int mode, byte[] key, byte[] data ) {
		byte[] result = null;
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec( key, CIPHER_TYPE );
			Cipher cipher = Cipher.getInstance( CIPHER_TYPE );
			cipher.init( mode, secretKeySpec );
			result = cipher.doFinal( data );			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return result;
	}

}
