package com.jp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jp.service.IIouLimitEntityService;
import com.jp.util.Utils;
import com.jp.mapper.IIouLimitEntityDao;
import com.jp.mapper.IIouRecordDao;
import com.jp.po.IouLimitEntity;
import com.jp.po.IouRecord;

public class IouLimitEntityServiceImpl implements IIouLimitEntityService {
	@Autowired(required=false)  
	private IIouLimitEntityDao iiouLimitEntityDao;
	@Autowired(required=false)  
	private IIouRecordDao iiouRecordDao;
	
	@Override
	public void setIouLimit(int iouLimit,String updateTime,String orgID) {
		// TODO Auto-generated method stub
		if(iouLimit<0) {
			// 确保数据的合理性
			System.out.println("机构不存在");
			return;
		}
		//检查数据库是否存在该orgID
		IouLimitEntity tmp = iiouLimitEntityDao.queryIouLimitEntityByOrgID(orgID);
		if(tmp==null) {
			//不存在该机构
			System.out.println("机构不存在");
			return;
		}else {
		iiouLimitEntityDao.updateIouLimitByOrgID(iouLimit, updateTime, orgID);
		}
	}

	@Override
	public int getIouLimit(String orgID) {
		// TODO Auto-generated method stub
		return iiouLimitEntityDao.getIouLimitByOrgID(orgID);
	}
	
	@Override
	public void recycleIou(String iouId,int amount) {
		// TODO Auto-generated method stub
		//修改两家机构的白条额度
		IouRecord iouRecord = iiouRecordDao.queryIouRecordByIouId(iouId);
		String fromOrgID = iouRecord.getFromOrg();
		String recvOrgID = iouRecord.getRecvOrg();
		IouLimitEntity fromOrg = iiouLimitEntityDao.queryIouLimitEntityByOrgID(fromOrgID);
		IouLimitEntity recvOrg = iiouLimitEntityDao.queryIouLimitEntityByOrgID(recvOrgID);
		int fromOrgLimit = fromOrg.getIouLimit();
		int recvOrgLimit = recvOrg.getIouLimit();
		//判断是否足够,有可能吗???
		if(fromOrgLimit-amount<0) {
			System.out.println("对方白条数量不够,无法回收");
			return;
		}
		long now=System.currentTimeMillis();
		String updateTime = Utils.sdf(now);
		fromOrg.setIouLimit(fromOrgLimit-amount);
		fromOrg.setUpdateTime(updateTime);
		recvOrg.setIouLimit(recvOrgLimit+amount);
		recvOrg.setUpdateTime(updateTime);
		//修改白条交易记录的已还金额
		iouRecord.setPaidAmt(iouRecord.getPaidAmt()+amount);
		//修改还清状态
		if(iouRecord.getAmount() == iouRecord.getPaidAmt()) {
			iouRecord.setIouStatus("C");
		}
		iouRecord.setUpdateTime(updateTime);
	}

	@Override
	public void addIouLimitEntity(IouLimitEntity iouLimitEntity) {
		// TODO Auto-generated method stub
		iiouLimitEntityDao.queryIouLimitEntityByOrgID(iouLimitEntity.getOrgID());
		if(true) {
			// 数据库中不存在,可以新建
//			System.out.println("iiouLimitEntityDao==null"+iiouLimitEntityDao==null);
//			System.out.println(iiouLimitEntityDao);
			iiouLimitEntityDao.addIouLimitEntity(iouLimitEntity);
		}else {
			//提示帐号已存在
			System.out.println("帐号已存在");
		}
		
	}

}
