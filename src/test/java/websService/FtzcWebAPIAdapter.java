package websService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.restful.vo.request.BaseRequestVo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class FtzcWebAPIAdapter {
	
	private static enum ServiceMod {
		LOCAL,
		VAP1B,
		PAP1A,
		PAP1B,
		PROD
	}
	
	private static final ServiceMod SERVICE_MOD = ServiceMod.LOCAL;	
	
	private static String FTZC_URL = "http://localhost:8082/ftzc/rest";
	
	static { // Initialize
		if ( SERVICE_MOD == ServiceMod.LOCAL ) { // 測試環境
			FTZC_URL = "http://localhost:8082/ftzc/rest";
			
		} else if ( SERVICE_MOD == ServiceMod.VAP1B ) { // 驗證環境
			FTZC_URL = "http://10.89.8.11:8099/APFTZC/rest";
			
		} else if ( SERVICE_MOD == ServiceMod.PAP1A ) { // 營運環境
			FTZC_URL = "http://10.88.1.11:8099/APFTZC/rest";
			
		} else if ( SERVICE_MOD == ServiceMod.PAP1B ) { // 營運環境
			FTZC_URL = "http://10.88.1.12:8099/APFTZC/rest";
			
		} else if ( SERVICE_MOD == ServiceMod.PROD ) { // 營運環境
			FTZC_URL = "https://ftzc.tradevan.com.tw/APFTZC/rest";
			
		}
		
	}

	private static ObjectMapper om = new ObjectMapper();
	
	private static Log logger = LogFactory.getLog( FtzcWebAPIAdapter.class );
	
	private String resourcePath;
	public String getResourcePath() {
		return resourcePath;
	}
	
	private Map<String, String> requestHeader = new HashMap<String, String>();
	public void setRequestHeader( Map<String, String> requestHeader ) {
		this.requestHeader = requestHeader;
	}
	
	private WebResource webResource;
	public WebResource getWebResource() {
		return webResource;
	}
	
	public void addRequestCookie( NewCookie cookie ) {
		builder.cookie(cookie);
	}
	
	private List<NewCookie> responseCookies;
	public List<NewCookie> getResponseCookies() {
		return responseCookies;
	}
	
	private Builder builder;
	
	public FtzcWebAPIAdapter( String resourcePath ) {
		this.resourcePath = resourcePath;
		/*
		Client c = Client.create();
		c.getProperties().put( ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true );
		c.setFollowRedirects(true);
		 */
		ClientConfig cc = new DefaultClientConfig();
		cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	    cc.getProperties().put( ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true );
	    Client c = Client.create(cc);	    
	    this.webResource = c.resource(  FTZC_URL + resourcePath );	
	    builder = webResource.accept( MediaType.APPLICATION_JSON_TYPE );
	}
	
	
	public <T> T post( Class<T> respType, BaseRequestVo req ) throws Exception {
		T response = null;
		ClientResponse clientResponse = null;
		try {
			
		    String reqPtyJson = om.writeValueAsString(req); 
		    logger.info( "ResourcePath : " + FTZC_URL + resourcePath );
		    logger.info( "Request : " + reqPtyJson);
		    
		    for ( String reqHeadKey : requestHeader.keySet() ) {
		    	webResource.header( reqHeadKey, requestHeader.get(reqHeadKey));
		    }
		    
		    clientResponse = builder.entity( req, MediaType.APPLICATION_JSON_TYPE ).post( ClientResponse.class );			    
		    
		    logger.info( String.format( "Http Status : %d", clientResponse.getStatus() ));
		    MultivaluedMap<String, String> headers = clientResponse.getHeaders();
		    for ( String headKey : headers.keySet() ) {
		    	List<String> headVal = headers.get(headKey);
		    	logger.info( String.format( "[Header] %s : %s", headKey, headVal ) ); 
		    }
		    
		    responseCookies = clientResponse.getCookies();
		    
		    String responseStr = clientResponse.getEntity(String.class);
		    logger.info( "Response : " + responseStr );
		    response = om.readValue(responseStr, respType);
			
		} catch (UniformInterfaceException ue) {
			clientResponse = ue.getResponse();
			
		} catch ( Exception e ) {
			e.printStackTrace();
			throw e;
		} finally {
			
		}		
		return response;
	}

}
