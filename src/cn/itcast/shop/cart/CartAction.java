package cn.itcast.shop.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionContext;

import org.apache.catalina.core.ApplicationContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.product.Product;
import cn.itcast.shop.product.ProductService;

//购物模块的action
public class CartAction extends ActionSupport{
	//接受pid
	private Integer pid;
	//接收数量
	private Integer count;
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * 从session范围获得购物车的方法
	 * */
	public Cart getCart(HttpServletRequest request){
		//从session 范围查找
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			//将购物车对象放入到session中，
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	//添加到购物车的方法
	public String addCart(){
		//查询商品的信息	
		Product product=productService.findByPid(pid);
		//创建一个购物项的对象
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		//获取购物车需要request对象
		HttpServletRequest request=ServletActionContext.getRequest();
		Cart cart =getCart(request);
		cart.addCart(cartItem);
		return "addCartSuccess";
	}
	//清空购物车
	public String clearCart(){
		//获取cart对象
		HttpServletRequest request=ServletActionContext.getRequest();
		Cart cart=getCart(request);
		cart.clearCart();
		return "clearCartSuccess";
	}
	//删除一个购物项
	public String removeCart(){
		//获得商品编号
		
		//获取cart对象
		HttpServletRequest request=ServletActionContext.getRequest();
		Cart cart=getCart(request);
		cart.removeCart(pid);
		return "removeCartSuccess";
	}
	//我的购物车
	public String myCart(){
		return "myCartSuccess";
	}
	
}
