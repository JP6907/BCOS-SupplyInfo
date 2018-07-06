package com.jp.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jp.po.Transaction;

public interface ITransactionDao {
	public void addTransaction(Transaction transaction);  // 新增交易
	public void updateTransactionStatusByConId(@Param(value="conId")String conId,@Param(value="status")String status);  // 更新交易状态 --U,未发货--A,已发货
	public Transaction getTransactionByConId(@Param(value="conId")String conId);  // 根据合同号ID查询交易
	public List<Transaction> getAllTransaction();
}
