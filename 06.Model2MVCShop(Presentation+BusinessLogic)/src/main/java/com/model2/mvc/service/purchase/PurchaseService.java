package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseService {
	
	public void addPurchase(Purchase purchase) throws Exception;  //구매를위한 비즈니스로직
	
	public Purchase getPurchase(int prodNo) throws Exception;  //구매목록보기

	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;  //구매목록보기(user)
	
	//public HashMap<String, Object> getSaleList(Search search) throws Exception;  //판매목록보기(admin)

	public void updatePurchase(Purchase purchaseVO) throws Exception; //구매정보수정
	
	public void updateTranCode(Purchase purchaseVO) throws Exception; //구매상태코드수정
		
}
