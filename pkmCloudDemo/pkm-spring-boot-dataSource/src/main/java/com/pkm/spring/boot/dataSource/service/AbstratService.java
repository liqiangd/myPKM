package com.pkm.spring.boot.dataSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.page.PageMethod;
import com.pkm.spring.boot.dataSource.common.pojo.AjaxResult;
import com.pkm.spring.boot.dataSource.common.pojo.PageAjax;
import com.pkm.spring.boot.dataSource.common.utils.AppUtil;

import tk.mybatis.mapper.common.Mapper;

/**
 * 通用接口
 * @param <T>
 */
@Service
public abstract class AbstratService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    public T queryByID(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
    
    public List<T> queryList(T entity){
    	return mapper.select(entity);
    }
    
    public T queryOne(T entity){
    	return mapper.selectOne(entity);
    }
    
    public int queryCount(T entity){
    	return mapper.selectCount(entity);
    }

    public List<T> queryByParams(Object example) {
        return mapper.selectByExample(example);
    }
    
    public List<T> queryAll(){
    	return mapper.selectAll();
    }

    public PageAjax<T> queryPage(PageAjax<T> page, T entity){
    	PageMethod.startPage(page.getPageNo(), page.getPageSize());
    	List<T> list = queryList(entity);
        return new PageAjax<T>(list);
    }

    public AjaxResult save(T entity) {
        int ret = insert(entity);
        String result = null;
        if(ret <= 0){
        	result = "添加失败";
        }
    	return AppUtil.returnObj(result);
    }
    
    public int insert(T entity){
    	return mapper.insert(entity);
    }

    public AjaxResult saveNotNull(T entity) {
    	int ret = mapper.insertSelective(entity);
        String result = null;
        if(ret <= 0){
        	result = "更新失败";
        }
    	return AppUtil.returnObj(result);
    }

    public AjaxResult update(T entity) {
    	int ret = updateByID(entity);
        String result = null;
        if(ret <= 0){
        	result = "更新失败";
        }
    	return AppUtil.returnObj(result);
    }
    
    public int updateByID(T entity){
    	return mapper.updateByPrimaryKeySelective(entity);
    }

    public AjaxResult delete(Object key) {
        int ret = deleteByID(key);
        String result = null;
        if(ret <= 0){
        	result = "删除失败";
        }
    	return AppUtil.returnObj(result);
    }
    
    public int deleteByID(Object key) {
    	return mapper.deleteByPrimaryKey(key);
    }

}
