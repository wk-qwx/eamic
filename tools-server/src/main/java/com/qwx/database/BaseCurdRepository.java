package com.qwx.database;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseCurdRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {}
//CrudRepository<T, ID>这个接口提供了最基本的对实体类的添删改查操作
