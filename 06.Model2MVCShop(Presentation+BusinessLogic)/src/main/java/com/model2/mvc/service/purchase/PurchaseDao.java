package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
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
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	//public int getTotalCount(Search search) throws Exception ;
	
	public String makeCurrentPageSql(String sql , Search search) throws Exception ;
	
}