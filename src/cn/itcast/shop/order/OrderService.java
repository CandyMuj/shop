package cn.itcast.shop.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.User;
import cn.itcast.shop.utils.PageBean;

//实例
@Transactional
public class OrderService {
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//保存订单
	public Integer save(Order order) {
		return orderDao.save(order);
	}

	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	//更新订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	public List<Order> findByUid(User existUser) {

		return orderDao.findByUid(existUser);
	}

	public PageBean<Order> findByPage(Integer page) {
		//查询所有订单
		PageBean<Order> pageBean=new PageBean<Order>();
		//封装数据
		
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalCount=0;
		totalCount=orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		Integer totalPage=0;
		pageBean.setTotalPage(totalPage);
		if(totalPage%limit==0){
			totalPage=totalPage/limit;
		}else{
			totalPage=totalPage/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		// TODO Auto-generated method stub
		return pageBean;
	}

	public PageBean<Order> findByPage(Integer state, Integer page) {
		//查询所有订单
		PageBean<Order> pageBean=new PageBean<Order>();
		//封装数据
		
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalCount=0;
		totalCount=orderDao.findCount(state);
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		Integer totalPage=0;
		pageBean.setTotalPage(totalPage);
		if(totalPage%limit==0){
			totalPage=totalPage/limit;
		}else{
			totalPage=totalPage/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(state,begin,limit);
		pageBean.setList(list);
		// TODO Auto-generated method stub
		return pageBean;
	}
}
