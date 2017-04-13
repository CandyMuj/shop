package cn.itcast.shop.index;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.category.CategoryService;
import cn.itcast.shop.product.Product;
import cn.itcast.shop.product.ProductService;

/**
 * 首页访问的Action
 * @author 传智.郭嘉
 *
 */
public class IndexAction extends ActionSupport{
	//注入一级分类的service
	private CategoryService categoryService;	
	//商品的service注入到indexAction
	private ProductService productService;
	
	private List<Product> hotList;
	public List<Product> getHotList() {
		return hotList;
	}
	//最新商品
	private List<Product> newList;
	public List<Product> getNewList() {
		return newList;
	}



	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	
	@Override
	/**
	 * 执行首页访问的方法
	 */
	public String execute() throws Exception {
		//查询一级分类
		List<Category> categoryList=categoryService.findAll();
		//存储到session中
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//查询热门商品
		hotList=productService.findHot(); 
		//查询最新商品信息。
		newList=productService.findNew();
//		System.out.println(hotList);
		return "indexSuccess";
	}
}
