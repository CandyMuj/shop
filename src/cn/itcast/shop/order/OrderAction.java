 package cn.itcast.shop.order;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.cart.Cart;
import cn.itcast.shop.cart.CartItem;
import cn.itcast.shop.user.User;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PaymentUtil;

public class OrderAction extends ActionSupport{
	//后台查询时需要使用的属性。
	private Integer page;
	private Integer state;
	private Order order;
	private String pd_FrpId;
	private String r3_Amt;
	private String r6_Order;
	private Integer oid;
	 
	
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	//注入orderService
	private OrderService orderService;
	public Order getOrder() {
		return order;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	//执行的方法
	public String saveOrder() throws ParseException{
		order=new Order();
		//封装订单的数据
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTemp = temp.format(new Date());//通过模板得到一个格式化后的日期字符串
		order.setOrdertime(temp.parse(dateTemp));//再将字符串转成date类型
		order.setState(1); //1为未付款   2为付款         
		//从购物车中获取
		HttpServletRequest request=ServletActionContext.getRequest();
		//获得购物车
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			this.addActionMessage("您还未购物，请去疯狂吧");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//订单所属的用户
		User existUser=(User) request.getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionMessage("您还么有登录，请先去登录");
			return 	"msg";
		}
		//保存用户
		order.setUser(existUser);
		//封装订单项的数据
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem =new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		//清空购物车
		cart.clearCart();
		//保存订单
		Integer oid=orderService.save(order);
		order=orderService.findByOid(oid);
		return  "saveOrderSuccess";
	}
	//确认订单方法
	public String payOrder() throws IOException {
		
		//修改订单
		//查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setPhone(order.getPhone());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		orderService.update(currOrder);
		//付款
		//定义付款的参数
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";//账号
		String p2_Order = order.getOid().toString();//id
		String p3_Amt = "0.01";//金额
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://localhost:/shop/order_callBack.action";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		StringBuffer sb=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
//		System.out.println(sb.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.sendRedirect(sb.toString());
		
		//付款成功后的方法
		
		return NONE;
	}
	 // 付款成功后的回调方法
	 
	public String callBack(){
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);// 修改订单状态.
		orderService.update(currOrder);
		
		this.addActionMessage("订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
		return "msg";
	}
	//按用户Id查询
	public String findByUid(){
		//获得用户对象
		User existUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		List<Order> oList=orderService.findByUid(existUser);
		//压栈adminFindAllSuccess
		ActionContext.getContext().getValueStack().set("oList", oList);
		return "findByUidSuccess";
	}
	public String findByOid(){
		order=orderService.findByOid(oid);
		return "findByOidSuccess";
	}
	public String adminFindAll(){
		PageBean<Order> pageBean = orderService.findByPage(page);
		//压栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	//后台查询订单(安状态——
	public String adminFindByState(){
		PageBean<Order> pageBean=orderService.findByPage(state,page);
		//压栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindByStateSuccess";
	}
	//
	public String adminUpdateState(){
		//根据Id查询订单
		order=orderService.findByOid(oid);
		order.setState(3);
		orderService.update(order);
		return "adminUpdateStateSuccess";
	}
	//前台修改订单状态
	public String updateState(){
		//根据Id查询订单
		order=orderService.findByOid(oid);
		order.setState(4);
		orderService.update(order);
		return "updateStateSuccess";
	}
	
	//支付成功页面跳转
	public String payOk(){
		this.addActionMessage("支付成功");
		return "payOkSuccess";
	}
	
	
	
	
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public String getR3_Amt() {
		return r3_Amt;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public String getR6_Order() {
		return r6_Order;
	}
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	
	
	
	
	
}
