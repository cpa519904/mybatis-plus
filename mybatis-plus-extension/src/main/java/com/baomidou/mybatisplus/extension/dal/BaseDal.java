package com.baomidou.mybatisplus.extension.dal;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author pingan.cui
 * @create 2020-05-19 -下午8:00
 */
public class BaseDal<M extends BaseMapper<T>, T> {
    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected M baseMapper;

    public M getBaseMapper() {
        return baseMapper;
    }

    protected Class<?> entityClass = currentModelClass();


    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }


    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    protected int insert(T entity) {
        return baseMapper.insert(entity);
    }

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    protected int deleteById(Serializable id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    protected int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap) {
        return baseMapper.deleteByMap(columnMap);
    }

    /**
     * 根据 entity 条件，删除记录
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     */
    protected int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper) {
        return baseMapper.delete(wrapper);
    }

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    protected int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList) {
        return baseMapper.deleteBatchIds(idList);
    }

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    protected int updateById(@Param(Constants.ENTITY) T entity) {
        return baseMapper.updateById(entity);
    }

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    protected int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper) {
        return baseMapper.update(entity, updateWrapper);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    protected T selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    protected List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    protected List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap) {
        return baseMapper.selectByMap(columnMap);
    }

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    protected T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    protected Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    protected List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    protected List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return baseMapper.selectMaps(queryWrapper);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    protected List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return baseMapper.selectObjs(queryWrapper);
    }

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    protected <E extends IPage<T>> E selectPage(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类
     */
    protected <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return baseMapper.selectMapsPage(page, queryWrapper);
    }
}
