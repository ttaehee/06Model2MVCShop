package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		Product product =productService.getProduct(10000);
		User user=userService.getUser("testUser");
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("testName");
		purchase.setReceiverPhone("testPhone");
		purchase.setDivyAddr("testAddr");
		purchase.setDivyRequest("testRequest");
		purchase.setTranCode("2");
		//purchase.setDivyDate("testDate");
		
		purchaseService.addPurchase(purchase);
		
		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals(10000, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("testUser", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("testName", purchase.getReceiverName());
		Assert.assertEquals("testPhone", purchase.getReceiverPhone());
		Assert.assertEquals("testAddr", purchase.getDivyAddr());
		Assert.assertEquals("testRequest",purchase.getDivyRequest());
		Assert.assertEquals("2", purchase.getTranCode());
		//Assert.assertEquals("testDate", purchase.getDivyDate());
	}
	
	//@Test
    public void testGetPurchase() throws Exception {
		
    	Purchase purchase = new Purchase();

    	purchase = purchaseService.getPurchase(10000);

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals(10000, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("testUser", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("testName", purchase.getReceiverName());
		Assert.assertEquals("testPhone", purchase.getReceiverPhone());
		Assert.assertEquals("testAddr", purchase.getDivyAddr());
		Assert.assertEquals("testRequest",purchase.getDivyRequest());
		Assert.assertEquals("2", purchase.getTranCode());
		//Assert.assertEquals("220420", purchase.getDivyDate());

		//Assert.assertNotNull(userService.getUser("user02"));
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		 Purchase purchase= purchaseService.getPurchase(10000);
		 Product product =productService.getProduct(10000);
		User user=userService.getUser("testUser");

		purchase.setPaymentOption("2");
		purchase.setReceiverName("updateName");
		purchase.setReceiverPhone("updatePhone");
		purchase.setDivyAddr("updateAddr");
		purchase.setDivyRequest("updateRequest");
		purchase.setTranCode("2");
		//purchase.setDivyDate("upDate");
		
		purchaseService.updatePurchase(purchase);
		
		purchaseService.getPurchase(10000);
		
		//==> console 확인
		System.out.println(purchase);
			
		//==> API 확인
		Assert.assertEquals(10000, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("testUser", purchase.getBuyer().getUserId());
		Assert.assertEquals("2", purchase.getPaymentOption());
		Assert.assertEquals("updateName", purchase.getReceiverName());
		Assert.assertEquals("updatePhone", purchase.getReceiverPhone());
		Assert.assertEquals("updateAddr", purchase.getDivyAddr());
		Assert.assertEquals("updateRequest",purchase.getDivyRequest());
		Assert.assertEquals("2", purchase.getTranCode());
		//Assert.assertEquals("upDate", purchase.getDivyDate());
	 }
	 
    //@Test
    public void testUpdateTranCode() throws Exception{
			 
		Purchase purchase= purchaseService.getPurchase(10000);

		purchase.setTranCode("3");

		purchaseService.updateTranCode(purchase);
			
		purchaseService.getPurchase(10000);
			
		//==> console 확인
		System.out.println(purchase);
				
		//==> API 확인
		Assert.assertEquals(10000, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("testUser", purchase.getBuyer().getUserId());
		Assert.assertEquals("2", purchase.getPaymentOption());
		Assert.assertEquals("updateName", purchase.getReceiverName());
		Assert.assertEquals("updatePhone", purchase.getReceiverPhone());
		Assert.assertEquals("updateAddr", purchase.getDivyAddr());
		Assert.assertEquals("updateRequest",purchase.getDivyRequest());
		Assert.assertEquals("3", purchase.getTranCode());
		//Assert.assertEquals("upDate", purchase.getDivyDate());
     }

	 @Test
	 public void testGetPurchaseList() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "testUser");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	
		//==> console 확인
	 	System.out.println("list:"+list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount:"+totalCount);
	 }

}