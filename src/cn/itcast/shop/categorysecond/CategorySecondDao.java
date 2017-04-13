package cn.itcast.shop.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	// 查询二级分类的总记录数
	public Integer findCount() {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from CategorySecond");
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}

	// 分页查询
	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<CategorySecond>(hql, null , begin, limit));
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	// 保存二级分类
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return this.getHibernateTemplate().find("from CategorySecond");
	}
	//删除二级分类商品
	public void delete(Integer cid) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(cid);
	}


	public void findByCid(Integer cid) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().find("from CategorySecond where cid=?",cid);
		
	}
	//删除二级 分类，需要先查出二级分类这个对象
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecond=this.getHibernateTemplate().get(categorySecond.getClass(),categorySecond.getCsid());
		this.getHibernateTemplate().delete(categorySecond);
	}
	//根据对象修改二级分类。

	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	//先查出数据，然后修改
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
		
	}

	




}

/*package cn.itcast.shop.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	public Integer findCount() {
		// TODO Auto-generated method stub
		List<Long> list=this.getHibernateTemplate().find("select count(*) from CategorySecond");
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		//没有就返回null	
		return null;
	}


	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		// TODO Auto-generated method stub
		String hql="from CategorySecond";
		 List<CategorySecond> list=this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		 if(list.size() >0 ){
			 return list;
		 }
		return null;
	}
	

	


}
*/