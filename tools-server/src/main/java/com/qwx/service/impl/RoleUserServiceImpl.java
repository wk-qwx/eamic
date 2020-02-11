package com.qwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.RoleUserEntity;
import com.qwx.service.RoleUserService;

/**
 * 角色用户映射信息服务
 * @author kal02
 *
 */
@Service
public class RoleUserServiceImpl extends BaseService<RoleUserEntity> implements RoleUserService{
	@Resource
	BasePagingAndSortingRepository<RoleUserEntity, String> roleuserDao;
	public RoleUserServiceImpl() {
		tableName = "role";
	}
	
	/**
	 *获取角色列表
	 */
	public List<RoleUserEntity> getRoleUser(String roleid) {
		String sql = "select * from role_user where roleid = '"+roleid+"'";
		return getBySql(sql);
	}
	/**
	 * 角色信息筛选查询分页列表
	 */
	public PageList<RoleUserEntity> getRoleUserListByFileter(String page, String limit, String whereStr){
		String sql = "";
		if(whereStr.equals("")){
			sql = "select * from role ORDER BY createtime desc";			
		}else{
			sql = "select * from role where " + whereStr + " ORDER BY createtime desc";
		}
		try{
			//返回分页列表
			return getPageBySql(page,limit,sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	/**
	 * 删除角色用户
	 */
	public String delRoleUser(List<RoleUserEntity> entitys){
		try {
			for(RoleUserEntity entity : entitys){
				String sql = "select * from role_user where roleid = '"+entity.getRoleid()+"' and userid = '"+entity.getUserid()+"'";
				List<RoleUserEntity> row = getBySql(sql);
				roleuserDao.delete(row);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
}
