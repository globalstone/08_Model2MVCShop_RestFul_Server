package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	//시대의 흐름,
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int no) throws Exception;

	//String buyerid 추가
	public Map<String, Object> getPurchaseList(Map<String,Object> params) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;

}
