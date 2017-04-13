package cn.itcast.shop.categorysecond;

import java.util.List;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.category.CategoryService;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	// 接收页数参数:
	private Integer page;
	
	// 模型驱动:
	private CategorySecond categorySecond = new CategorySecond();
	
	// 注入Service
	private CategorySecondService categorySecondService;
	// 注入一级分类的Service
	private CategoryService categoryService;
	// 接收cid
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	
	
	public String adminFindAll(){
		//调用service查询
		PageBean<CategorySecond> pageBean =categorySecondService.findByPage(page);
		//压栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	//查询出所有二级分类的商品，供后台查询使用
	public String adminFindCategorySecondAll(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return NONE;
	}
	public String addPage(){
		// 查询一级分类的列表:
		List<Category> cList = categoryService.findAll();
		// 压栈:
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	//二级分类删除
	public String delete(){
		//接收cid
		categorySecondService.delete(categorySecond);
		//因为跟保存跳转的是同一个页面，所以我就使用的是保存成功的跳转语句。但是有一个问题，就是如果我使用不一样的，他就不会成功跳转，在struts文件中不能跳转到同一效果
		return "categorySecondDeleteSuccess";
	}
	//修改二级分裂
	public String edit(){
		categorySecond=categorySecondService.finbByCsid(categorySecond.getCsid());
		categorySecondService.update(categorySecond);
		return "categorySecondEditSuccess";
	}
	
	
	public String save(){
		Category category = new Category();
		category.setCid(cid);
		// 二级分类对象:
		categorySecond.setCategory(category);
		// 调用Serviec保存
		categorySecondService.save(categorySecond);
		return "saveSuccess";
		
	}

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	

}
/*
package cn.itcast.shop.categorysecond;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.utils.PageBean;

public class CategorySecondAction extends ActionSupport{
	//查询二级分类
	private Integer page;
	//注入service
	private Category category;
	
	public void setCategory(Category category) {
		this.category = category;
	}

	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String adminFindAll(){
		//调用service查询
		PageBean<CategorySecond> pageBean =categorySecondService.findByPage(page);
		//压栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
}
*/