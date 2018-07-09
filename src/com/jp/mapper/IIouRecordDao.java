package com.jp.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jp.po.IouRecord;



public interface IIouRecordDao {
	public void addIouRecord(@Param("iouRecord")IouRecord iouRecord);  // 新增白条记录
	public void updateIouStatusByIouId(@Param(value="iouId")String iouId, @Param(value="iouStatus")String iouStatus);  // 更新白条状态 --C,已还清--P,未还清
	public IouRecord queryIouRecordByIouId(@Param(value="iouId")String iouId);  // 根据白条id查询白条
	public List<IouRecord> queryAllIouRecordByFromOrg(@Param(value="fromOrg")String fromOrg);  //根据发行机构查询所有白条
	public List<IouRecord> queryAllIouRecord();  // 获取所有 iouRecord

}
