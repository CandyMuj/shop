package cn.itcast.shop.adminuser;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUser adminUser = new AdminUser();
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	//后台登录的方法
	public String login(){
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser ==null ){
			//登录失败
			this.addActionError("登录失败，请检查用户名和密码的正确！！");
			return "loginFailed";
			
		}
		ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
		return "loginSuccess";	
	}
}	
