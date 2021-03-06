package com.jp.service.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.mapper.IIouLimitEntityDao;
import com.jp.mapper.IIouRecordDao;
import com.jp.po.IouLimitEntity;
import com.jp.po.IouRecord;
import com.jp.service.IIouLimitEntityService;
import com.jp.util.Utils;




@Service
public class IouLimitEntityServiceImpl implements IIouLimitEntityService {
	@Autowired
	private IIouLimitEntityDao iiouLimitEntityDao;
	@Autowired
	private IIouRecordDao iiouRecordDao;
	
	@Override
	public boolean setIouLimit(int iouLimit,String updateTime,String orgID) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		if(iouLimit<0) {
			// 确保数据的合理性
			System.out.println("数值小于0");
			return false;
		}
		//检查数据库是否存在该orgID
		IouLimitEntity tmp = iiouLimitEntityDao.queryIouLimitEntityByOrgID(orgID);
		if(tmp==null) {
			//不存在该机构
			System.out.println("机构不存在");
			return false;
		}else {
		iiouLimitEntityDao.updateIouLimitByOrgID(iouLimit, updateTime, orgID);
		
		//IOUService.setIouLimit(orgID, iouLimit);
		
		return true;
		}
	}

	@Override
	public int getIouLimit(String orgID) {
		// TODO Auto-generated method stub
		return iiouLimitEntityDao.getIouLimitByOrgID(orgID);
	}
	
	@Override
	public boolean recycleIou(String iouId,int amount) throws InterruptedException, ExecutionException {
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
			return false;
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
		//IOUService.iouRecycle(iouId, amount);
		return true;
	}

	@Override
	public boolean addIouLimitEntity(IouLimitEntity iouLimitEntity) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		IouLimitEntity tmp = iiouLimitEntityDao.queryIouLimitEntityByOrgID(iouLimitEntity.getOrgID());
		if(tmp==null) {
			// 数据库中不存在,可以新建
			System.out.println("iiouLimitEntityDao==null"+iiouLimitEntityDao==null);
			System.out.println(iiouLimitEntityDao);
			long now=System.currentTimeMillis();
			String time = Utils.sdf(now);
			iouLimitEntity.setCreateTime(time);
			iouLimitEntity.setUpdateTime(time);
			iiouLimitEntityDao.addIouLimitEntity(iouLimitEntity);
			
			// 在区块链中创建
			//IOUService.initIouLimitData(iouLimitEntity.getOrgID(), iouLimitEntity.getOrgName(), iouLimitEntity.getPassword(), iouLimitEntity.getIouLimit());
			
			return true;
		}else {
			//提示帐号已存在
			System.out.println("帐号已存在");
			return false;
		}
//		return true;
	}

	@Override
	public int checkPasswordByorgID(String password,String orgID,String Name) {
		IouLimitEntity tmp = iiouLimitEntityDao.queryIouLimitEntityByOrgID(orgID);
		if(tmp == null) {
			//不存在该机构
			System.out.println("机构不存在");
			return 1;
		}else {
			String N = iiouLimitEntityDao.checkName(orgID);
			if(N == Name){
				String P = iiouLimitEntityDao.checkPassword(orgID);
				if (P==password){
					System.out.println("密码正确");
					return 0;
				}
				else {
					System.out.println("密码错误");
					return 2;
				}
			}else {
				System.out.println("机构类型不正确");
				return 3;
			}	
		}
	}


}
