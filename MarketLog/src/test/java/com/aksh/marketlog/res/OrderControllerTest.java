package com.aksh.marketlog.res;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.aksh.marketlog.dto.NewOrder;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {


	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void pingtest() {
		ResponseEntity<String> pingResponse=restTemplate.getForEntity("/orders/ping", String.class);
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
		exec.setRefId(6);
		
		HttpEntity<NewOrder> request = new HttpEntity<>(exec);
		
		ResponseEntity<NewOrder> res=restTemplate.postForEntity("/orders/save", request, NewOrder.class);
		assertEquals(HttpStatus.OK,res.getStatusCode());

		NewOrder fromDb=res.getBody();
		Map< String, Object> temp=new HashMap<>();
		temp.put("refId", exec.getRefId());
		ResponseEntity<NewOrder> pingResponse=restTemplate.getForEntity("/orders/byref?refId="+exec.getRefId(),NewOrder.class);
		assertEquals(HttpStatus.OK,pingResponse.getStatusCode());
		NewOrder lastFromDB=pingResponse.getBody();
		
		assertEquals(fromDb.getId(), lastFromDB.getId());
		System.out.println(pingResponse.getBody());
	}
	
	


}
