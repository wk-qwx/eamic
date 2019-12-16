package com.qwx.database;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
	//Repository<T, ID>所有继承这个接口的interface都被spring所管理，此接口作为标识接口，功能就是用来控制domain模型的。
}