package cn.itcast.shop.categorysecond;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.CategoryDao;
import cn.itcast.shop.utils.PageBean;
@Transactional
public class CategorySecondService {

	// 注入DAO
	private CategorySecondDao categorySecondDao;
	
	// 业务层带有分页查询二级分类
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}


	public PageBean<CategorySecond> findByPage(Integer page) {
		// 封装PageBean
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 封装页数
		pageBean.setPage(page);
		// 每页显示的记录数
		Integer limit = 15;
		pageBean.setLimit(limit);
		// 总记录数
		Integer totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 总页数
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据
		Integer begin = (page - 1 )* limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	// 业务层保存二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}


	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}




	//根据cid查得此商品
	public void finbByCid(Integer cid) {
		// TODO Auto-generated method stub
		categorySecondDao.findByCid(cid);
		
	}

	//根据cid删除商品
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(categorySecond);
		
	}


	public CategorySecond finbByCsid(Integer csid) {
		// TODO Auto-generated method stub
		
		return categorySecondDao.findByCsid(csid);
	}


	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
		
	}

	//业务层根据csid查询商品
	


	
}


/*package cn.itcast.shop.categorysecond;

import java.util.List;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import cn.itcast.shop.utils.PageBean;

public class CategorySecondService {
	//注入categorySecondDao
	private CategorySecondDao categorySecondDao;
	
	

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}



	public PageBean<CategorySecond> findByPage(Integer page) {
		//封装pageBean
		// TODO Auto-generated method stub
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
		int limit=10;
		pageBean.setPage(page);
		
		pageBean.setLimit(limit);
		Integer totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		Integer totalPage=0;
		if(totalCount % limit ==0){
			totalCount=totalCount/limit;
		}else{
			totalCount=totalCount/limit+1;
		}
		
		pageBean.setTotalCount(totalCount);
		Integer begin=(page-1)*limit;
		List<CategorySecond> list=CategorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

}
*/