package com.qwx.dao;

import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.entity.QrcodeEntity;
import com.qwx.entity.QrcodeView2Entity;
import com.qwx.entity.QrcodeViewEntity;

public interface QrcodeViewDao extends BasePagingAndSortingRepository<QrcodeView2Entity, String> {}
