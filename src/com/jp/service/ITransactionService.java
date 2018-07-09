package com.jp.service;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.ibatis.annotations.Param;

import com.jp.po.Transaction;


public interface ITransactionService {
	public boolean addTransactionRecord(Transaction transaction)throws InterruptedException, ExecutionException;  // 录入交易
	
	public boolean updateTransactionStatusByConId(@Param("conId")String conId,@Param("status")String status);  // 交易状态修改
	
	public Transaction getTransactionByConId(@Param("conId")String conId);  // 根据合同号ID查询交易
	
	public List<Transaction> getAllTransaction();  // 查询所有交易信息
	
	public List<Transaction> queryTransaction(int pageNo,int pageSize);  // 查询所有交易信息

}
