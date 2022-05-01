package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseService {
	
	public void addPurchase(Purchase purchase) throws Exception;  //���Ÿ����� ����Ͻ�����
	
	public Purchase getPurchase(int prodNo) throws Exception;  //���Ÿ�Ϻ���

	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;  //���Ÿ�Ϻ���(user)
	
	//public HashMap<String, Object> getSaleList(Search search) throws Exception;  //�ǸŸ�Ϻ���(admin)

	public void updatePurchase(Purchase purchaseVO) throws Exception; //������������
	
	public void updateTranCode(Purchase purchaseVO) throws Exception; //���Ż����ڵ����
		
}
