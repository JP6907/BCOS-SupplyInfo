package com.jp.service;
import com.jp.po.IouLimitEntity;

public interface IIouLimitEntityService {
	
	public void addIouLimitEntity(IouLimitEntity iouLimitEntity);  // 新增机构
	
	public void setIouLimit(int amount,String updateTime,String orgID);  // 设定白条额度
	
	public int getIouLimit(String orgID);  // 获取白条额度
	
	public void recycleIou(String iouId,int amount);  // 回收白条

}
