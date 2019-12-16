package com.qwx.database;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BasePagingAndSortingRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	//PagingAndSortingRepository<T, ID>这个接口提供了分页与排序功能
}
