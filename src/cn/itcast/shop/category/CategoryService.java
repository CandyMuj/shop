package cn.itcast.shop.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategoryService {
	
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	//业务层查询所有一级分类的方法
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}
	//业务层保存一级分类
	public void save(Category category) {
		categoryDao.save(category);
	}
	public void update(Category category) {
		categoryDao.update(category);
		
	}
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
		
	}
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	
}
