package com.aksh.marketlog.res.it;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.aksh.marketlog.dto.NewOrder;
public class OrderControllerIT {


	private RestTemplate restTemplate=new RestTemplate();
	private String serverUrl="http://192.168.99.100:8080";

	public void init(){
	}

	@Test
	public void pingtest() {
		ResponseEntity<String> pingResponse=restTemplate.getForEntity(serverUrl+"/orders/ping", String.class);
		assertEquals(HttpStatus.OK,pingResponse.getStatusCode());
	}

	
	
	@Test
	public void lastExecutionTest() {
		Date executionTime=new Date();
		NewOrder exec=new NewOrder();
		exec.setEntryTime(executionTime);
		exec.setStatus('N');
		exec.setType('B');
		exec.setPrice(10.0F);
		exec.setQty(10);
		exec.setStock("AAPL");
		exec.setRefId(10);
		
		HttpEntity<NewOrder> request = new HttpEntity<>(exec);
		
		ResponseEntity<NewOrder> res=restTemplate.postForEntity(serverUrl+"/orders/save", request, NewOrder.class);
		assertEquals(HttpStatus.OK,res.getStatusCode());

		NewOrder fromDb=res.getBody();
		Map< String, Object> temp=new HashMap<>();
		temp.put("refId", exec.getRefId());
		ResponseEntity<NewOrder> pingResponse=restTemplate.getForEntity(serverUrl+"/orders/byref?refId="+exec.getRefId(),NewOrder.class);
		assertEquals(HttpStatus.OK,pingResponse.getStatusCode());
		NewOrder lastFromDB=pingResponse.getBody();
		
		assertEquals(fromDb.getId(), lastFromDB.getId());
		System.out.println(pingResponse.getBody());
	}
	
	


}
