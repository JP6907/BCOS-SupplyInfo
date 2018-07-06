package com.jp.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jp.po.IouLimitEntity;

public interface IIouLimitEntityDao {
	public void addIouLimitEntity(IouLimitEntity iouLimitEntity);  // 新增机构
	public void updateIouLimitEntity(IouLimitEntity iouLimitEntity);  // 设置机构额度
	public IouLimitEntity queryEntityByOrgName(@Param(value="orgName")String orgName);  // 通过机构名查询机构实体 废弃
	public Integer getIouLimitByOrgID(@Param(value="orgID")String orgID);
	public void updateIouLimitByOrgID(@Param(value="iouLimit")int iouLimit, @Param(value="updateTime")String updateTime, @Param(value="orgID")String orgID);  // 更新机构的白条额度
	public IouLimitEntity queryIouLimitEntityByOrgID(@Param(value="orgID")String orgID);  // 通过orgID查询机构
}

