package com.jp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.mapper.IIouLimitEntityDao;
import com.jp.mapper.IIouRecordDao;
import com.jp.mapper.ITransactionDao;
import com.jp.po.IouLimitEntity;
import com.jp.po.IouRecord;
import com.jp.po.Transaction;
import com.jp.service.ITransactionService;
import com.jp.util.Utils;



@Service
public class TransactionServiceImpl implements ITransactionService {
	@Autowired(required=false)
	private ITransactionDao itransactionDao;
	@Autowired(required=false)  
	private IIouLimitEntityDao iiouLimitEntityDao;
	@Autowired(required=false)
	private IIouRecordDao iiouRecordDao;
	
	
	@Override
	public boolean addTransactionRecord(Transaction transaction)throws InterruptedException, ExecutionException{  // 录入交易
		// TODO Auto-generated method stub
		
		long now=System.currentTimeMillis();
		String transTime = Utils.sdf(now);
		String conID="conID"+transTime;               // 合同号
	    String conHash="conHash";
	    
		transaction.setConHash(conHash);
		transaction.setTransTime(transTime);
		transaction.setUpdateTime(transTime);
		
		if(transaction.getAmount()<= 0) {
			// 确保数据的合理性
			System.out.println("白条金额不可小于0");
			return false;
		}
		IouLimitEntity tmpBuy = iiouLimitEntityDao.queryIouLimitEntityByOrgID(transaction.getBuyOrg());
		IouLimitEntity tmpSale = iiouLimitEntityDao.queryIouLimitEntityByOrgID(transaction.getSaleOrg());
		if(tmpBuy==null||tmpSale==null) {
			//不存在该机构
			System.out.println("机构不存在");
			return false;
		}else {
			itransactionDao.addTransaction(transaction);
			System.out.println("创建交易");
			
			
			//IOUService.addTransaction(transaction.getSaleOrg(), transaction.getBuyOrg(), transaction.getTransType(), 
			//		transaction.getAmount(), transaction.getLatestStatus());
			
			IouRecord iouRecord =new IouRecord();
			iouRecord.setAmount((int)transaction.getAmount());
			iouRecord.setIouId(conID.substring(0, 10));
			iouRecord.setFromOrg(transaction.getBuyOrg());
			iouRecord.setRecvOrg(transaction.getSaleOrg());
			iouRecord.setTransTime(transTime);
			iouRecord.setPaidAmt(0);
			iouRecord.setIouStatus("P");
			iouRecord.setUpdateTime(transTime);
			
			
			
			iiouRecordDao.addIouRecord(iouRecord);
			
			IouLimitEntity iouLimitEntity=iiouLimitEntityDao.queryIouLimitEntityByOrgID(transaction.getBuyOrg());//queryEntityByOrgName(buyOrg);
			if(iouLimitEntity != null) {
				
				int tem = (int) (iouLimitEntity.getIouLimit()-transaction.getAmount());
				
				iiouLimitEntityDao.updateIouLimitByOrgID(tem, transTime, transaction.getBuyOrg());
			}
			else {
				return false;
			}
			return true;
		}
	}

	@Override
	public boolean updateTransactionStatusByConId(String conId,String status) {
		// TODO Auto-generated method stub
		Transaction tmp = itransactionDao.getTransactionByConID(conId);
		if(tmp==null) {
			//不存在该机构
			System.out.println("交易不存在");
			return false;
		}else {
			tmp.setLatestStatus(status);
			long now=System.currentTimeMillis();
			String updateTime = Utils.sdf(now);
			tmp.setUpdateTime(updateTime);
			
//			try {
//				IOUService.updateTransStatus(conId, status);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			return true;
		}
	}

	@Override
	public Transaction getTransactionByConId(String conId) {
		// TODO Auto-generated method stub
		Transaction tmp = itransactionDao.getTransactionByConID(conId);
		if(tmp==null) {
			System.out.println("不存在该交易");
			return null;
		}
		return tmp;
	}

	@Override
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return itransactionDao.getAllTransaction();
	}

	@Override
	public List<Transaction> queryTransaction(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		pageNo--;
		List<Transaction> allTransaction = itransactionDao.getAllTransaction();
//		System.out.println("读取所有transaction...");
		List<Transaction> result = new ArrayList<>();
		for(int i=pageNo*pageSize;i<allTransaction.size()&&i<(pageNo+1)*pageSize;i++) {
			result.add(allTransaction.get(i));
		}
//		System.out.println("复制transaction...得到的size为");
		return result;
	}

}
