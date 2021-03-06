package com.heqing.shiro.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T> {

	/**
	 * 保存实体
	 * @param entity 实体
	 */
	void save(T entity);

	/**
	 * 根据ID删除实体
	 * @param id  实体ID
	 */
	void delete(Long id);
	
	/**
	 * 根据多个ID批量删除实体
	 * @param ids  多个实体ID
	 */
	void deleteBatch(Long[] ids);

	/**
	 * 更新实体
	 * @param entity 实体
	 */
	void update(T entity);

	/**
	 * 查询实体，如果id为null，则返回null，并不会抛异常。
	 * @param id 实体ID
	 * @return T 实体
	 */
	T getById(Long id);

	/**
	 * 根据多个ID查询实体
	 * @param ids 多个实体ID集合
	 * @return List<T> 多个实体集合
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有
	 * @return List<T> 多个实体集合
	 */
	List<T> getAllList();
	
	/**
	 * 根据条件查询所有
	 * @param entity 实体:做条件查询使用
	 * @return List<T> 多个实体集合
	 */
	List<T> getListByCondition(T entity);

	/**
	 * 公共的查询分页信息的方法
	 * @param pageNum 哪一页
	 * @param pageSize 一个几条信息
	 * @return List<T> 多个实体集合
	 */
	List<T> getPageBean(@Param(value="pageNum")int pageNum, @Param(value="pageSize")int pageSize);
	
	/**
	 * 公共的查询分页信息的方法
	 * @param entity 实体:做条件查询使用
	 * @param pageNum 哪一页
	 * @param pageSize 一个几条信息
	 * @return List<T> 多个实体集合
	 */
	List<T> getPageBeanByCondition(@Param(value="entity")T entity, @Param(value="pageNum")int pageNum, @Param(value="pageSize")int pageSize);
}
