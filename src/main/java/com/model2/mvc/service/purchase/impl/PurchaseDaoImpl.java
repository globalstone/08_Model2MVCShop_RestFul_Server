package com.model2.mvc.service.purchase.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
	}

	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("PurchaseMapper.insertPurchase",purchase);
	}

	@Override
	public Purchase findPurchase(int pordNo) throws Exception {
		// TODO Auto-generated method stub
		return (Purchase)sqlSession.selectOne("PurchaseMapper.findPurchase",pordNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updatePurchase",purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updateTranCode",purchase);
	}

	@Override
	public List<Purchase> getPurchaseList(Map<String,Object> params ) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("PurchaseMapper.getPurchaseList",params);
	}

	@Override
	public int getTotalCount(Map<String,Object> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getTotalCount",params);
	}
	
	

}
