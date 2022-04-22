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
import com.model2.mvc.service.purchase.PurchaseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

   //==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
   @Autowired
   @Qualifier("purchaseServiceImpl")
   private PurchaseService purchaseService;

   //@Test
   public void testAddPurchase() throws Exception {
      
      Purchase purchase = new Purchase();
      Product product=new Product();
      User user=new User();
      
      product.setProdNo(10001);
      user.setUserId("user13");
      
      purchase.setPurchaseProd(product);
      purchase.setBuyer(user);
      purchase.setPaymentOption("1");
      purchase.setReceiverName("testName");
      purchase.setReceiverPhone("testPhone");
      purchase.setDivyAddr("testAddr");
      purchase.setDivyRequest("testRequest");
      purchase.setTranCode("1");
      purchase.setDivyDate("2022/04/20");
      
      purchaseService.addPurchase(purchase);
      
      //product= productService.getProduct("testUserId");

      //==> console 확인
      //System.out.println(user);
      
      //==> API 확인
      Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
      Assert.assertEquals("user13", purchase.getBuyer().getUserId());
      Assert.assertEquals("1", purchase.getPaymentOption());
      Assert.assertEquals("testName", purchase.getReceiverName());
      Assert.assertEquals("testPhone", purchase.getReceiverPhone());
      Assert.assertEquals("testAddr", purchase.getDivyAddr());
      Assert.assertEquals("testRequest",purchase.getDivyRequest());
      Assert.assertEquals("1", purchase.getTranCode());
      Assert.assertEquals("2022/04/20", purchase.getDivyDate());
   }
   
   //@Test
    public void testGetPurchase() throws Exception {
      
      Purchase purchase = new Purchase();

      purchase = purchaseService.getPurchase(10000);

      //==> console 확인
      System.out.println(purchase);
      
      //==> API 확인
      Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
      Assert.assertEquals("user13", purchase.getBuyer().getUserId());
      Assert.assertEquals("1", purchase.getPaymentOption());
      Assert.assertEquals("testName", purchase.getReceiverName());
      Assert.assertEquals("testPhone", purchase.getReceiverPhone());
      Assert.assertEquals("testAddr", purchase.getDivyAddr());
      Assert.assertEquals("testRequest",purchase.getDivyRequest());
      Assert.assertEquals("1", purchase.getTranCode());
      Assert.assertEquals("2022-04-20 00:00:00", purchase.getDivyDate());

      //Assert.assertNotNull(userService.getUser("user02"));
   }
   
   //@Test
    public void testUpdatePurchase() throws Exception{
       
       Purchase purchase= purchaseService.getPurchase(10000);
      //Assert.assertNotNull(purchase);

      purchase.setPaymentOption("2");
      purchase.setReceiverName("updateName");
      purchase.setReceiverPhone("01022222222");
      purchase.setDivyAddr("updateAddr");
      purchase.setDivyRequest("updateRequest");
      purchase.setDivyDate("2022/04/21");
      
      purchaseService.updatePurchase(purchase);
      
      purchase= purchaseService.getPurchase(10000);
     // Assert.assertNotNull(purchase);
      
      //==> console 확인
      System.out.println(purchase);
         
      //==> API 확인
      Assert.assertEquals(10001, purchase.getPurchaseProd().getProdNo());
      Assert.assertEquals("user13", purchase.getBuyer().getUserId());
      Assert.assertEquals("2", purchase.getPaymentOption());
      Assert.assertEquals("updateName", purchase.getReceiverName());
      Assert.assertEquals("01022222222", purchase.getReceiverPhone());
      Assert.assertEquals("updateAddr", purchase.getDivyAddr());
      Assert.assertEquals("updateRequest",purchase.getDivyRequest());
      Assert.assertEquals("1", purchase.getTranCode());
      Assert.assertEquals("2022-04-21 00:00:00.0", purchase.getDivyDate());

    }
    
    //@Test
    public void testUpdateTranCode() throws Exception{
       
       Purchase purchase= purchaseService.getPurchase(10000);
      //Assert.assertNotNull(purchase);

      purchase.setTranCode("2");
      
      purchaseService.updateTranCode(purchase);
      
      purchase= purchaseService.getPurchase(10000);
     // Assert.assertNotNull(purchase);
      
      //==> console 확인
      System.out.println(purchase);
         
      //==> API 확인
      Assert.assertEquals("2", purchase.getTranCode());

    }

    @Test
    public void testGetPurchaseList() throws Exception{
       
       Search search = new Search();
       search.setCurrentPage(1);
       search.setPageSize(3);
       Map<String,Object> map = purchaseService.getPurchaseList(search, "user13");
       
       List<Object> list = (List<Object>)map.get("list");
       Assert.assertEquals(1, list.size());
       
      //==> console 확인
       System.out.println("list:"+list);
       
       Integer totalCount = (Integer)map.get("totalCount");
       System.out.println("totalCount:"+totalCount);
       
       //==> console 확인
       System.out.println(list);
       
       totalCount = (Integer)map.get("totalCount");
       System.out.println(totalCount);
    }


}