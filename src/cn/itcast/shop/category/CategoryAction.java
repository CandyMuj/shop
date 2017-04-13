package cn.itcast.shop.category;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.categorysecond.CategorySecond;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	private CategoryService categoryService;
	//创建category对象
	private Category category=new Category();
	
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//后台查询方法
	public String adminFindAll(){
		List<Category> cList=categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "adminFindAllSuccess";
	}
	/**
	 * 后台:保存一级分类:
	 */
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	public String edit(){
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
}
