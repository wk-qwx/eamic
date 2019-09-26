package com.qwx.dao;

import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.entity.UserEntity;

public interface DeviceDao extends BasePagingAndSortingRepository<UserEntity, String> {}
