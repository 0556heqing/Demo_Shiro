package com.heqing.shiro.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	@CacheEvict(value="shiro", key="#root.targetClass+#root.methodName", allEntries=true) 
	public void save(T entity) {
		baseDao.save(entity);
	}

	@CacheEvict(value="shiro", key="#root.targetClass+#root.methodName", allEntries=true)
	public void delete(Long id) {
		baseDao.delete(id);
	}

	@CacheEvict(value="shiro", key="#root.targetClass+#root.methodName", allEntries=true)
	public void deleteBatch(Long[] ids) {
		baseDao.deleteBatch(ids);
	}

	@CachePut(value="shiro", key="#root.targetClass+#root.methodName")  
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public T getById(Long id) {
		return baseDao.getById(id);
	}

	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<T> getByIds(Long[] ids) {
		return baseDao.getByIds(ids);
	}

	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<T> getAllList() {
		return baseDao.getAllList();
	}
	
	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<T> getListByCondition(T entity) {
		return baseDao.getListByCondition(entity);
	}

	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<T> getPageBean(int pageNum, int pageSize) {
		return baseDao.getPageBean((pageNum-1)*pageSize, pageSize);
	}

	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<T> getPageBeanByCondition(T entity, int pageNum, int pageSize) {
		return baseDao.getPageBeanByCondition(entity, (pageNum-1)*pageSize, pageSize);
	}

}
