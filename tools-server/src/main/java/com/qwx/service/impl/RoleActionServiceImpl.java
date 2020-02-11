package com.qwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.RoleActionEntity;
import com.qwx.service.RoleActionService;

/**
 * 角色权限映射信息服务
 * @author kal02
 *
 */
@Service
public class RoleActionServiceImpl extends BaseService<RoleActionEntity> implements RoleActionService{
	@Resource
	BasePagingAndSortingRepository<RoleActionEntity, String> roleactionDao;
	public RoleActionServiceImpl() {
		tableName = "role";
	}
	
	/**
	 *获取角色列表
	 */
	public List<RoleActionEntity> getRoleAction(String roleid) {
		String sql = "select * from role_action where roleid = '"+roleid+"'";
		return getBySql(sql);
	}
	/**
	 * 角色信息筛选查询分页列表
	 */
	public PageList<RoleActionEntity> getRoleActionListByFileter(String page, String limit, String whereStr){
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
	 * 删除角色权限
	 */
	public String delRoleAction(List<RoleActionEntity> entitys){
		try {
			for(RoleActionEntity entity : entitys){
				String sql = "select * from role_action where roleid = '"+entity.getRoleid()+"' and actionid = '"+entity.getActionid()+"'";
				List<RoleActionEntity> row = getBySql(sql);
				roleactionDao.delete(row);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
}
