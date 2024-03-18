package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@Controller
@RequestMapping("/product/*")
public class ProductController {

	//Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
//	@RequestMapping("/addProduct.do")
//	public String addProduct(@ModelAttribute("ProdVO") Product product) throws Exception{
//
//		System.out.println("/addProduct.do");
//
//		String md = product.getManuDate();
//		String[] manu = md.split("-");
//		String manudate = manu[0]+manu[1]+manu[2];
//		product.setManuDate(manudate);
//
//		productService.addProduct(product);
//
//
//		return "forward:/product/readProductView.jsp";
//	}

	@GetMapping(value = "addProduct")
	public String addProduct() throws Exception{
		System.out.println("productService.GET");
		return "forward:/product/readProductView.jsp";
	}

	@PostMapping(value = "addProduct")
	public String addProduct(@ModelAttribute("ProdVO") Product product)throws Exception {
		System.out.println("addProduct.Post");

		String md = product.getManuDate();
		String[] manu = md.split("-");
		String manudate = manu[0] + manu[1] + manu[2];
		product.setManuDate(manudate);

		productService.addProduct(product);
		return "forward:/product/readProductView.jsp";
	}

//	@RequestMapping("/getProduct.do")
	@GetMapping(value = "getProduct/{prodNo}/{menu}")
	public String getProduct(@PathVariable int prodNo,
//							 @RequestParam int prodNo,
//							 @RequestParam("menu") String menu,
							 @PathVariable String menu,
							 HttpServletRequest request,
							 HttpServletResponse response,
							 Model model) throws Exception {
		System.out.println("/getProduct.do");
		Product prod = productService.getProduct(prodNo);
		model.addAttribute("prod", prod);
		model.addAttribute("menu", menu);

		// 최근 본 상품 정보를 쿠키에 저장
		// cookies 변수 저장
		Cookie[] cookies = request.getCookies();
		// prodNo를 String으로 변환 후 history에 저장
		String history = String.valueOf(prodNo);
		// cookie를 찾는 lookfor 변수를 false로 선언 false는 찾았다 라는 뜻.
		boolean lookfor = false;
		// cookies 가 null이 아니라면 아래 실행
		if (cookies != null) {
			//쿠키 리스트를 하나씩 확인 Cookie cookie : cookies == int i = 0; i < cookies.length; i++
			for (Cookie cookie : cookies) {
				//쿠키 이름이 history라면 아래 실행
				if (cookie.getName().equals("history")) {
					//쿠키 값을 value에 저장
					String value = cookie.getValue();
					//쿠키의 중복을 허용하기 싫으면 주석을 풀자 56번라인 61번라인
					//if (!value.contains(history)) {
					//상품 번호를 기존 값 뒤에 추가하고, 쿠키 값을 업데이트하고, 그 쿠키를 다시 사용자 브라우저로 보내서 저장
					value += "/" + history;
					cookie.setValue(value);
					response.addCookie(cookie);
					//}
					//lookfor 값 true로 변경하고 반복문 break로 멈춤.
					lookfor = true;
					break;
				}
			}
		}
		//쿠키를 못찾으면?? 아래 실행
		if (!lookfor) {
			//history라는 쿠키를 새로 만들고 상품번호를 값으로 넣어서 브라우저에 저장.
			Cookie cookie = new Cookie("history", history);
			response.addCookie(cookie);
		}

		return "forward:/product/updateProductView.jsp";
	}
	
//	@RequestMapping("/updateProduct.do")
	@PostMapping(value = "updateProduct")
	public String updateProduct(@ModelAttribute("update") Product prod) throws Exception{
		
		System.out.println("/updateProduct.do");
		productService.updateProduct(prod);
		
		return "forward:/product/updateReadProduct.jsp";
	}
	
//	@RequestMapping("/updateProductView.do")
	@GetMapping(value = "updateProduct/{prodNo}/{menu}")
	public String updateProduct(@PathVariable int prodNo,
								@PathVariable String menu,
//								@RequestParam int prodNo,
								Model model) throws Exception{
		
		System.out.println("/updateProductView.do");
		Product prod = productService.getProduct(prodNo);
		model.addAttribute("UpdateProdVO", prod);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping(value = "listProduct/{menu}")
	public String listProduct(@ModelAttribute("Search") Search search,
//							  @RequestParam("menu") String menu,
							  @PathVariable String menu,
							  Model model) throws Exception{
		
		System.out.println("/listProduct.POST / GET");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue() , pageUnit, pageSize);
		System.out.println(resultPage);
		
		
		model.addAttribute("menu",menu);
		model.addAttribute("list",map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		
		
		return "forward:/product/productList.jsp";
	}
}
