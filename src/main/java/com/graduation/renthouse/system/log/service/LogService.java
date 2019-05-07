package com.graduation.renthouse.system.log.service;


import com.graduation.renthouse.system.log.domain.LogDO;
import com.graduation.renthouse.system.log.domain.PageDO;
import com.graduation.renthouse.system.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
