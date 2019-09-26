package com.qwx.database;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
/**
 * JpaSpecification基础类
 * 
 * @author zoujing
 *
 * @param <T> 对应的实体对象
 * @param <ID> 主键
 */
@NoRepositoryBean
public interface BaseJpaSpecificationExecutorRepository<T, ID extends Serializable>
		extends BasePagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {
		//JpaSpecificationExecutor<T>这个接口提供了JPA的相关功能
}
