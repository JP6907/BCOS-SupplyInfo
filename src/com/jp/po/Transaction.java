package com.jp.po;

public class Transaction {
	private String conID;               // 合同号
    private String saleOrg;             // 销售方机构 
    private String buyOrg;              // 购买方机构
    private String transType;           // 交易类型
    private long amount;                 // 白条金额
    private String conHash;             // 合同hash
    private String latestStatus;        // 最新状态 --U,未发货--A,已发货
    private String transTime;           // 交易时间
    private String updateTime;          // 更新时间
    
	public Transaction() {
		super();
	}

	public Transaction(String conID, String saleOrg, String buyOrg, String transType, long amount, String conHash,
			String latestStatus, String transTime, String updateTime) {
		super();
		this.conID = conID;
		this.saleOrg = saleOrg;
		this.buyOrg = buyOrg;
		this.transType = transType;
		this.amount = amount;
		this.conHash = conHash;
		this.latestStatus = latestStatus;
		this.transTime = transTime;
		this.updateTime = updateTime;
	}

	public String getConID() {
		return conID;
	}

	public void setConID(String conID) {
		this.conID = conID;
	}

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getBuyOrg() {
		return buyOrg;
	}

	public void setBuyOrg(String buyOrg) {
		this.buyOrg = buyOrg;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getConHash() {
		return conHash;
	}

	public void setConHash(String conHash) {
		this.conHash = conHash;
	}

	public String getLatestStatus() {
		return latestStatus;
	}

	public void setLatestStatus(String latestStatus) {
		this.latestStatus = latestStatus;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Transaction [conID=" + conID + ", saleOrg=" + saleOrg + ", buyOrg=" + buyOrg + ", transType="
				+ transType + ", amount=" + amount + ", conHash=" + conHash + ", latestStatus=" + latestStatus
				+ ", transTime=" + transTime + ", updateTime=" + updateTime + "]";
	}
    
    

}
