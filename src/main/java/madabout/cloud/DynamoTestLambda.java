package net.esteh;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Named;

//import org.jboss.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
/*
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import net.esteh.moadmin.exception.EstehException;
import net.esteh.moadmin.manager.RegionManager;
import net.esteh.moadmin.util.DbConnectionUtil;
import net.esteh.moadmin.util.JsonUtil;
import net.esteh.moadmin.util.MockUtil;
*/
@Named("dynamotest")
public class DynamoTestLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    /*
	private static final Logger logger = Logger.getLogger(MoAdminRegionLambda.class);
	private DbConnectionUtil dbConnection;
	
	public MoAdminRegionLambda() {
		dbConnection = new DbConnectionUtil();
	}*/
	
	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, final Context context) {
		String result = "";
		
		try {
            String requestBody = request.getBody();
            
            result = "TEST " + requestBody;
            /*

			JsonObject params = Json.createReader(new StringReader((requestBody))).readObject();
			
			params = Json.createObjectBuilder(params).add("ruser_id", getRuserId(request)).build(); //zakucati ruser
			
			String action = params.getString("action");

			switch (action) {
			
				case "createRegion":
		
//					//TEST 1
//					if (params.equals(Map.of(
//					        "ext_code"  , "1" ,
//					        "name"  , "test" ,   
//					        "short_name" , "test",
//					        "action" , "createRegion",
//					        "company_id" , "56"
//					))) {
//						result = JsonUtil.transformToJsonMap(Map.of("id","1"));
//					}else{
//						throw new EstehException("Missing params");
//					};
					
	
					//REAL CODE
					result = RegionManager.createRegion(params, dbConnection.getConnection());
					break;
				case "updateRegion":
//					
//					if ("{\"status_id\": \"1\",\"name\": \"e51\",\"short_name\": \"e51short\",\"ext_code\": \"e51code\",\"action\" : \"updateRegion\",\"id\" : \"1\"}".equals(requestBody)) {
//						result = JsonUtil.getJsonOkResponse();
//					}else {
//						throw new EstehException("Missing params");
//					}
					
					//real code
					result = RegionManager.updateRegion(params, dbConnection.getConnection());
					break;
	
				default:
					throw new EstehException("Invalid action:"+ action);
			}
			
//			conn.commit();
			*/
			return new APIGatewayProxyResponseEvent()
					.withBody(result)
					.withHeaders(getHeader())
					.withStatusCode(200);
			
		} catch (Exception e) {
            //logger.error("Error processing", e);
            
            return new APIGatewayProxyResponseEvent()
					.withBody(e.toString())
					.withStatusCode(500);

			/*
			return new APIGatewayProxyResponseEvent()
					.withBody(JsonUtil.getJsonErrorResponse())
					.withStatusCode(500);*/
		}
	}
	
	
	private Map<String, String> getHeader() {
		Map<String, String> allowOrigin = new HashMap<String, String>();
		allowOrigin.put("Access-Control-Allow-Origin", "*");
		
		return allowOrigin;
	}
	
	private String getRuserId(APIGatewayProxyRequestEvent input) {
		return "1";
	}
	
}
