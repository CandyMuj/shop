package cn.itcast.shop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport{
	//DAO层的查询所有
	public List<Category> findAll() {
		return this.getHibernateTemplate().find("from Category");
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
		
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
		
	}
	public void delete(Category category) {
		//级联删除必须用id,所以用来获取id
		category=this.getHibernateTemplate().get(Category.class, category.getCid());
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
		
	}
	//查询一级分类
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
		
	}

	

}
