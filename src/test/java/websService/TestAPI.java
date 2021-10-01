package websService;

import org.apache.commons.codec.binary.Hex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.restful.constant.RestResourcePath;
import com.restful.enums.RequestType;
import com.restful.utils.DESHelper;
import com.restful.vo.request.SysGetDesKeyRequestVo;
import com.restful.vo.response.SysGetDesKeyResponseVo;

public class TestAPI {
	
	@Before
	public void before() {
		System.out.println( "before" );
		
	}

	@After
	public void after() {
		System.out.println( "after" );
		
	}
	
	@Test
	public void test() {
		try {
			String acc = "N_97162640_6284";
			String pwd = "6284";
			FtzcWebAPIAdapter adapter_1 = new FtzcWebAPIAdapter(  RestResourcePath.SECURE + RestResourcePath.SECURE_PATH.GET_DES_KEY );
			SysGetDesKeyRequestVo request_1 = new SysGetDesKeyRequestVo();
		    request_1.setReqType( RequestType.API );
			SysGetDesKeyResponseVo response_1 = adapter_1.post( SysGetDesKeyResponseVo.class, request_1 );
			response_1.setDes("dad5f8cbcb98e63d");
			byte[] desKey = Hex.decodeHex( response_1.getDes().toCharArray() );
			
			byte[] enAccHex = DESHelper.encode( desKey, acc.getBytes() );
			byte[] enPassHex = DESHelper.encode( desKey, pwd.getBytes() );
			String enAcc = new String( Hex.encodeHex(enAccHex) );
			String enPass = new String( Hex.encodeHex(enPassHex) );			
		    System.out.println(enAcc);
		    System.out.println(enPass);
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}

}
