package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;


//==> 회원관리 Controller
@Controller
public class PurchaseController {
   
   ///Field
   @Autowired
   @Qualifier("purchaseServiceImpl")
   private PurchaseService purchaseService;
   //setter Method 구현 않음
      
   public PurchaseController(){
      System.out.println(this.getClass());
   }
   
   //==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
   //==> 아래의 두개를 주석을 풀어 의미를 확인 할것
   @Value("#{commonProperties['pageUnit']}")
   //@Value("#{commonProperties['pageUnit'] ?: 3}")
   int pageUnit;
   
   @Value("#{commonProperties['pageSize']}")
   //@Value("#{commonProperties['pageSize'] ?: 2}")
   int pageSize;
   
   @RequestMapping("/addPurchase.do")
   public String addProduct( @ModelAttribute("purchase") Purchase purchase) throws Exception {

      System.out.println("/addPurchase.do");
      //Business Logic
      purchaseService.addPurchase(purchase);
      
      return "redirect:/purchase/addPurchaseView.jsp";
   }
   
   @RequestMapping("/getPurchase.do")
   public String getProduct( @RequestParam("tranNo") int tranNo, @RequestParam("menu") String menu , Model model ) throws Exception {
      
      System.out.println("/getPurchase.do");
      //Business Logic
      Purchase purchase = purchaseService.getPurchase(tranNo);
      // Model 과 View 연결
      model.addAttribute("purchase", purchase);
      
      return "forward:/purchase/getPurchase.jsp";

   }
   
   @RequestMapping("/updatePurchaseView.do")
   public String updateProductView( @RequestParam("tranNo") int tranNo, Model model ) throws Exception{

      System.out.println("/updatePurchaseView.do");
      //Business Logic
      Purchase purchase = purchaseService.getPurchase(tranNo);
      // Model 과 View 연결
      model.addAttribute("purchase", purchase);
      
      return "forward:/purchase/updatePurchase.jsp";
   }
   
   @RequestMapping("/updatePurchase.do")
   public String updateProduct( @ModelAttribute("purchase") Purchase purchase , Model model , HttpSession session) throws Exception{

      System.out.println("/updatePurchase.do");
      //Business Logic
      purchaseService.updatePurchase(purchase);
      
      return "redirect:/getPurchase.do?tranNo="+purchase.getTranNo();
   }
   
   
   @RequestMapping("/listPurchase.do")
   public String listProduct( @ModelAttribute("search") Search search, @ModelAttribute("id") String userId , Model model , HttpServletRequest request) throws Exception{
      
      System.out.println("/listPurchase.do");
      
      if(search.getCurrentPage() ==0 ){
         search.setCurrentPage(1);
      }
      search.setPageSize(pageSize);
            
      // Business logic 수행
      Map<String , Object> map=purchaseService.getPurchaseList(search, userId);
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println(resultPage);
      
      // Model 과 View 연결
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
      
      return "forward:/purchase/listPurchasejsp";
   }
}