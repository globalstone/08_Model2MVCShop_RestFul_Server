package com.model2.mvc.web.product;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	//Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	@PostMapping(value = "json/addProduct")
	public Product addProduct(@RequestBody Product product)throws Exception {
		System.out.println("addProduct.Post");

		String md = product.getManuDate();
		String[] manu = md.split("-");
		String manudate = manu[0] + manu[1] + manu[2];
		product.setManuDate(manudate);

		productService.addProduct(product);

		return product;
	}

	@RequestMapping(value = "json/listProduct/{menu}")
	public Map<String,Object> listProduct(@ModelAttribute("Search") Search search,
							  @PathVariable String menu) throws Exception{

		System.out.println("/listProduct.POST / GET");

		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(pageSize);

		Map<String, Object> map = productService.getProductList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue() , pageUnit, pageSize);
		System.out.println(resultPage);

		map.put("menu", menu);
		map.put("productList", map.get("list"));
		map.put("resultPage", resultPage);
		map.put("search", search);

		return map;
	}
}
