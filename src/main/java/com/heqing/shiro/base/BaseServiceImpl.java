package com.heqing.shiro.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	public void save(T entity) {
		baseDao.save(entity);
	}

	public void delete(Long id) {
		baseDao.delete(id);
	}

	public void deleteBatch(Long[] ids) {
		baseDao.deleteBatch(ids);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public T getById(Long id) {
		return baseDao.getById(id);
	}

	public List<T> getByIds(Long[] ids) {
		return baseDao.getByIds(ids);
	}

	public List<T> getAllList() {
		return baseDao.getAllList();
	}

	public List<T> getListByCondition(T entity) {
		return baseDao.getListByCondition(entity);
	}

	public List<T> getPageBean(int pageNum, int pageSize) {
		return baseDao.getPageBean((pageNum-1)*pageSize, pageSize);
	}

	public List<T> getPageBeanByCondition(T entity, int pageNum, int pageSize) {
		return baseDao.getPageBeanByCondition(entity, (pageNum-1)*pageSize, pageSize);
	}

}
