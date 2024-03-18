package com.model2.mvc.service.product.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
	}

	@Override
	public void insertProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("ProductMapper.insertProduct",product);
	}

	@Override
	public Product findProduct(int productNo) throws Exception {
		// TODO Auto-generated method stub
		return (Product)sqlSession.selectOne("ProductMapper.findProduct",productNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("ProductMapper.updateProduct",product);
	}

	@Override
	public List<Product> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ProductMapper.getProductList",search);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount",search);
	}
	
}
