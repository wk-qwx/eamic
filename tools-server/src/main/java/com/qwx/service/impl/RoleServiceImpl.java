package com.qwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.RoleActionEntity;
import com.qwx.entity.RoleEntity;
import com.qwx.entity.RoleUserEntity;
import com.qwx.service.RoleService;

/**
 * 角色信息服务
 * @author kal02
 *
 */
@Service
public class RoleServiceImpl extends BaseService<RoleEntity> implements RoleService{
	@Resource
	BasePagingAndSortingRepository<RoleEntity, String> roleDao;
	@Resource
	BasePagingAndSortingRepository<RoleUserEntity, String> roleuserDao;
	@Resource	
	BasePagingAndSortingRepository<RoleActionEntity, String> roleactionDao;
	public RoleServiceImpl() {
		tableName = "role";
	}
	
	/**
	 *获取角色列表
	 */
	public PageList<RoleEntity> getRoleList(String page, String limit) {
		String sql = "select * from role order by createtime desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 角色信息筛选查询分页列表
	 */
	public PageList<RoleEntity> getRoleListByFileter(String page, String limit, String whereStr){
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
	 * 删除Role
	 */
	public String delRole(RoleEntity entity){
		try {
			roleDao.delete(entity);
			String sql = "select * from role_user where roleid = '"+entity.getId()+"'";
			List<RoleUserEntity> rows = getBySql(sql,RoleUserEntity.class);
			roleuserDao.delete(rows);
			
			sql = "select * from role_action where roleid = '"+entity.getId()+"'";
			List<RoleActionEntity> rows2 = getBySql(sql,RoleActionEntity.class);
			roleactionDao.delete(rows2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return entity.getId();
	}
}
