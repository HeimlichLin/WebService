package websService;

import javax.ws.rs.core.NewCookie;

import org.apache.commons.codec.binary.Hex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.restful.constant.RestResourcePath;
import com.restful.enums.RequestType;
import com.restful.utils.DESHelper;
import com.restful.vo.request.L4LogRequestVo;
import com.restful.vo.request.SysGetAccessCodeRequestVo;
import com.restful.vo.request.SysGetDesKeyRequestVo;
import com.restful.vo.response.L4LogResponseVo;
import com.restful.vo.response.SysGetAccessCodeResponseVo;
import com.restful.vo.response.SysGetDesKeyResponseVo;

import junit.framework.TestCase;
import junit.framework.TestResult;

public class LOG_TEST extends TestCase {	
	private static final FtzcWebAPIAdapter L4_API = new FtzcWebAPIAdapter( RestResourcePath.LOG + RestResourcePath.LOG_PATH.L4_LASTDAY_LOG );
	
	private static final String ACC = "N_97162640_6284";
	private static final String PWD = "6284";
	
	private String accessCode;
	
	public LOG_TEST(String testCase){
		super(testCase);
	}
	
	public static void main(String[] args){
		LOG_TEST[] tests = {				
				new LOG_TEST("testL4Log")
		};
		
		for(LOG_TEST test: tests) {
            TestResult result = test.run();
            System.out.println(test.getName());
            System.out.println("\tResult: " + result.wasSuccessful());
            System.out.println("\tError: " + result.errorCount());
        } 
		
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println( "setUpBeforeClass" );		
	}
	
	@Before
	public void setUp() throws Exception {
		System.out.println( "setUp" );
		System.out.println( "Initialize... GetAccessCode..." );
		// GET DES KEY
		FtzcWebAPIAdapter adapter_1 = new FtzcWebAPIAdapter(  RestResourcePath.SECURE + RestResourcePath.SECURE_PATH.GET_DES_KEY );
		SysGetDesKeyRequestVo request_1 = new SysGetDesKeyRequestVo();
	    request_1.setReqType( RequestType.API );		   
	    SysGetDesKeyResponseVo response_1 = adapter_1.post( SysGetDesKeyResponseVo.class, request_1 );

		byte[] desKey = Hex.decodeHex( response_1.getDes().toCharArray() );
		
		byte[] enAccHex = DESHelper.encode( desKey, ACC.getBytes() );
		byte[] enPassHex = DESHelper.encode( desKey, PWD.getBytes() );
		String enAcc = new String( Hex.encodeHex(enAccHex) );
		String enPass = new String( Hex.encodeHex(enPassHex) );

		FtzcWebAPIAdapter adapter = new FtzcWebAPIAdapter( RestResourcePath.SECURE + RestResourcePath.SECURE_PATH.GET_ACCESS_CODE );
		for ( NewCookie respCookie : adapter_1.getResponseCookies() ) {
			adapter.addRequestCookie(respCookie);
		}		
	    SysGetAccessCodeRequestVo request = new SysGetAccessCodeRequestVo();
	    request.setReqType( RequestType.API );		   
	    request.setEnAcc(enAcc);
	    request.setEnPass(enPass);	    
	    SysGetAccessCodeResponseVo response = adapter.post( SysGetAccessCodeResponseVo.class, request );	
	    this.accessCode = response.getAccessCode();
	    System.out.println( "GetAccessCode -> " + this.accessCode );
	}	
	
	@Test
	public void testL4Log() {
		System.out.println( "L4_LOG" );
		try {
			L4LogRequestVo reqL4 = new L4LogRequestVo();
			reqL4.setAccessCode(this.accessCode);
			reqL4.setReqType( RequestType.API );
			L4LogResponseVo resL4 = L4_API.post(L4LogResponseVo.class, reqL4);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println( "tearDown" );
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println( "tearDownAfterClass" );
	}

}
