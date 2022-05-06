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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

//==> 회원관리 Controller
@Controller
public class PurchaseController {

	/// Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	// setter Method 구현 않음

	public PurchaseController() {
		System.out.println(this.getClass());
	}

	// ==> classpath:config/common.properties , classpath:config/commonservice.xml
	// 참조 할것
	// ==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView(@RequestParam("prodNo") int prodNo, Model model) throws Exception {

		System.out.println("/addPurchaseView.do");

		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);

		return "forward:/purchase/addPurchaseView.jsp";
	}

	@RequestMapping("/addPurchase.do")
	public String addPurchase(@ModelAttribute("user") User user, @ModelAttribute("product") Product product, @ModelAttribute("purchase") Purchase purchase) throws Exception {

		System.out.println("/addPurchase.do");
		// Business Logic
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		
		purchaseService.addPurchase(purchase);

		return "forward:/purchase/updatePurchase.jsp";
	}

	@RequestMapping("/getPurchase.do")
	public String getPurchase(@RequestParam("tranNo") int tranNo, Model model) throws Exception {

		System.out.println("/getPurchase.do");
		// Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		// Model 과 View 연결
		model.addAttribute("purchase", purchase);

		return "forward:/purchase/getPurchase.jsp";
	}

	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView(@RequestParam("tranNo") int tranNo, Model model) throws Exception {

		System.out.println("/updatePurchaseView.do");
		// Business Logic
		Purchase purchase= purchaseService.getPurchase(tranNo);
		// Model 과 View 연결
		model.addAttribute("purchase", purchase);

		return "forward:/purchase/updatePurchaseView.jsp";
	}

	@RequestMapping("/updatePurchase.do")
	public String updatePurchase(@ModelAttribute("purchase") Purchase purchase)
			throws Exception {

		System.out.println("/updatePurchase.do");
		// Business Logic
		purchaseService.updatePurchase(purchase);

		return "redirect:/getPurchase.do?tranNo=" + purchase.getTranNo();
	}

	@RequestMapping("/updateTranCode.do")
	public String updatePurchase(@RequestParam("prodNo") int prodNo, @RequestParam("tranCode") String tranCode, @ModelAttribute("purchase") Purchase purchase)
			throws Exception {

		System.out.println("/updatePurchase.do");
		// Business Logic
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);

		return "forward:/listProduct.do?menu=manage";
	}

	@RequestMapping("/listPurchase.do")
	public String listPurchase(@ModelAttribute("search") Search search, Model model, HttpSession session)
			throws Exception {

		System.out.println("/listPurchase.do");
		
		//String userId=((User)session.getAttribute("user")).getUserId();
		//System.out.println("controller:"+userId);

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String, Object> map = purchaseService.getPurchaseList(search, ((User)session.getAttribute("user")).getUserId());

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/purchase/listPurchase.jsp";
	}
	
}