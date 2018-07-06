package com.jp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jp.mapper.IIouLimitEntityDao;
import com.jp.mapper.ITransactionDao;
import com.jp.po.IouLimitEntity;
import com.jp.po.Transaction;
import com.jp.service.ITransactionService;

public class TransactionServiceImpl implements ITransactionService {
	@Autowired(required=false)
	private ITransactionDao itransactionDao;
	@Autowired(required=false)  
	private IIouLimitEntityDao iiouLimitEntityDao;
	@Override
	public void addTransactionRecord(Transaction transaction) {
		// TODO Auto-generated method stub
		if(transaction.getAmount()<= 0) {
			// 确保数据的合理性
			System.out.println("白条金额不可小于0");
			return;
		}
		IouLimitEntity tmpBuy = iiouLimitEntityDao.queryIouLimitEntityByOrgID(transaction.getBuyOrg());
		IouLimitEntity tmpSale = iiouLimitEntityDao.queryIouLimitEntityByOrgID(transaction.getSaleOrg());
		if(tmpBuy==null||tmpSale==null) {
			//不存在该机构
			System.out.println("机构不存在");
			return;
		}else {
			itransactionDao.addTransaction(transaction);
			System.out.println("创建交易");
			return;
		}
	}

	@Override
	public void updateTransactionStatusByConId(String conId,String status) {
		// TODO Auto-generated method stub
		Transaction tmp = itransactionDao.getTransactionByConId(conId);
		if(tmp==null) {
			//不存在该机构
			System.out.println("交易不存在");
			return;
		}else {
			tmp.setLatestStatus(status);
			return;
		}
	}

	@Override
	public Transaction getTransactionByConId(String conId) {
		// TODO Auto-generated method stub
		Transaction tmp = itransactionDao.getTransactionByConId(conId);
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
		List<Transaction> allTransaction = itransactionDao.getAllTransaction();
		List<Transaction> result = new ArrayList<>();
		for(int i=pageNo*pageSize;i<pageSize;i++) {
			result.add(allTransaction.get(i));
		}
		return result;
	}

}
