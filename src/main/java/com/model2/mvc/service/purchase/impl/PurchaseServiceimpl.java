package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.domain.Purchase;

@Service("purchaseServiceImpl")
public class PurchaseServiceimpl implements PurchaseService {
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	public void setPurchase(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	public PurchaseServiceimpl() {
		System.out.println(getClass());
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int no) throws Exception {
		return purchaseDao.findPurchase(no);
	}

	@Override
	public Map<String, Object> getPurchaseList(Map<String,Object> params) throws Exception {

		Search search = (Search) params.get("search");
		String buyerId = (String) params.get("buyerId");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId", buyerId);

		List<Purchase> list =  purchaseDao.getPurchaseList(map);
		int totalCount = purchaseDao.getTotalCount(map);

		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}
}
