package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


//==> ȸ���������� CRUD �߻�ȭ/ĸ��ȭ�� DAO Interface Definition
public interface PurchaseDao {
	
	// INSERT
	public void addPurchase(Purchase purchase) throws Exception ;

	// SELECT ONE
	public Purchase getPurchase(int prodNo) throws Exception ;

	// SELECT LIST
	public java.util.Map<String, Object> getPurchaseList(Search search, String userId) throws Exception ;

	// UPDATE
	public void updatePurchase(Purchase purchase) throws Exception ;
	
	public void updateTranCode(Purchase purchase) throws Exception ;
	
	// �Խ��� Page ó���� ���� ��üRow(totalCount)  return
	//public int getTotalCount(Search search) throws Exception ;
	
	public String makeCurrentPageSql(String sql , Search search) throws Exception ;
	
}