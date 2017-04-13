package cn.itcast.shop.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//购物车对象
public class Cart {
	//ID做为map的key
	private Map<Integer,CartItem> map=new HashMap<Integer,CartItem>();
	//提供一个获得map的value的集合
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	private Double total = 0d;
	//提供三个方法。
		//1,将购物项添加到购物车
	public void addCart(CartItem cartItem){
		//获得购物箱的ID
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//购物车中已经有购物项了，
			//已有的购物项信息，
			CartItem oldcartItem=map.get(pid);
			oldcartItem.setCount(oldcartItem.getCount()+cartItem.getCount());
		}else{
		//购物车中不存在购物项
			map.put(pid, cartItem);
		}
		//总计
		total=total+cartItem.getSubtotal();
	}
	//2,将购物项移除购物车
	public void removeCart(Integer pid){
		//将购物项从map集合中移除
		CartItem cartItem=map.remove(pid);
		//设置总计的钱数
		total=total-cartItem.getSubtotal();
	}
		//3,清空购物车
	public void clearCart(){
		//清空map,总计为零
		map.clear();
		total=0d;
	}
	public Map<Integer, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
	public Double getTotal() {
		return total;
	}
	//没用 的这个方法
	public void setTotal(Double total) {
		this.total = total;
	}
}
