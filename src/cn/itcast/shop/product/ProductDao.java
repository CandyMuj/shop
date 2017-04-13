package cn.itcast.shop.product;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cn.itcast.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{
	//查询热门商品，只显示十个。
	public List<Product> findHot() {
		/*DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		List<Product> hotlist=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return hotlist;*/
		//此方法调用了一个老师写的工具类来传递参数
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>("from Product where is_hot=?", new Object[]{1}, 0, 10));
		return list;
	}
	//查询最新
	public List<Product> findNew() {
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>("from Product order by pdate desc", null, 0, 10));
		return list;
	}
	/*public List<Product> findNew(){
		return this.getSessionFactory().getCurrentSession()
				.createQuery("from Product where order by pdate desc",Product.class)
				.setFristResults(0).setMaxResults(10).getResultsList();
	}*/
	//统计某个分裂的总数
	public Integer findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		System.out.println("list:============="+list.get(0).intValue());
		return list.get(0).intValue();
	}
	public List<Product> findByPage(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list=this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		return list;
	}
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
		
	}
	//统计某个二级分类的数量
	public Integer findCountByCsid(Integer csid) {
		String hql="select count(*) from Product p join p.categorySecond cs where cs.csid=?";
		List<Long> 	list= this.getHibernateTemplate().find(hql,csid); 
		return list.get(0).intValue();
		
	}
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql="select p from  Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		// TODO Auto-generated method stub
		return list;
	}
	public Integer findCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list.size() >0){
			return list.get(0).intValue();
		}
		return null;
	}
	public List<Product> findByPage(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="from Product";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list.size()>0){
			return list;
		}
		return null;
	}
	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
		
	}
	//根据pid删除商品
	public void delete(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(product);
		
	}
	

}
