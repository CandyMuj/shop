package cn.itcast.shop.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.category.CategoryService;
import cn.itcast.shop.categorysecond.CategorySecond;
import cn.itcast.shop.categorysecond.CategorySecondService;
import cn.itcast.shop.utils.PageBean;
	//本页知识点
	//实现了modelDriven接口，自己所设置的pid的接收方法就不起作用，应为modelDriven接口会在栈顶，必须通过product对象的方式获得pid的值
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//文件上传的三个属性
	private File upload;//文件
	private String uploadContentType;//mime类型
	private String uploadFileName;//上传名称
	//接收cid
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	//接收pid
	//这里的pid是不起作用的，因为栈顶已被modelDriven接口占用。
	private Integer pid;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	private Integer cid;
	//接受二机分类的ID
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//接受当前页数。
	private Integer page;
	private PageBean<Product> pageBean;
	private Product product=new Product();
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public PageBean<Product> getPageBean() {
		return pageBean;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	//注入一级分类的service
	private CategoryService categoryService; 
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//查询商品的方法
	public String findByCid(){
	//查询分类
	//查询所有一级分类
		List<Category>  categoryList=categoryService.findAll();
		//获的值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		//查询商品
		pageBean=productService.findByPage(cid, page);
		return "findByCidSuccess";
	}
	//查詢商品詳情
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		List<Category>  categoryList=categoryService.findAll();
		//获的值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "findByPidSuccess";
	}
	//查询二级分类的商品
	public String findByCsid(){
		//查询所有一级分类
		List<Category>  categoryList=categoryService.findAll();
		//获的值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		pageBean=productService.findByCsid(csid,page);
		return "findByCsidSuccess";
	}
	//跳转到添加页面
	public String addPage(){
		//查询所有二级分类
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	//后台插讯所有的方法
	public String adminFindAll(){
		pageBean =productService.findByPage(page);
		return "adminFindAllSuccess";
	}
	//保存添加商品，上传图片
	public String save() throws IOException{
		// 文件上传的操作:
		// 获得上传的路径:
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String realPath = path+"/"+csid+"/"+uploadFileName;
		File diskFile = new File(realPath);
		// 文件上传:
		FileUtils.copyFile(upload, diskFile);
		// 保存到数据库:
		// 设置二级分类
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		// 设置时间:
		product.setPdate(new Date());
		// 设置图片上传路径:
		product.setImage("products/"+csid+"/"+uploadFileName);
		
		// 调用Serviec保存商品:
		productService.save(product);
		return "saveSuccess";
		
	}
	//删除商品
	//删除商品要先从数据库获得商品这个对象，然后根据对象去删除商品才行，不然要报错。
	public String delete(){
		//接收pid
		product=productService.findByPid(product.getPid());
		productService.delete(product);
//		System.out.println(product.getPid());
		return "deleteSuccess";
	}
	//商品的修改
	public String edit(){
		product=productService.findByPid(product.getPid());
		//压栈
		ActionContext.getContext().getValueStack().set("product", product);
		return "editSuccess";
	}
	
	
}
