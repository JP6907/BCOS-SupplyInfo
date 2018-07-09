package com.jp.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.jp.po.Transaction;




public interface ITransactionDao {
	public void addTransaction(@Param("transaction")Transaction transaction);  // 新增交易
	public void updateTransactionStatusByConID(@Param(value="conID")String conId,@Param(value="status")String status);  // 更新交易状态 --U,未发货--A,已发货
	public Transaction getTransactionByConID(@Param(value="conID")String conID);  // 根据合同号ID查询交易
	public List<Transaction> getAllTransaction();
}
