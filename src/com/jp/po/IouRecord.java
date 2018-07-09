package com.jp.po;

public class IouRecord {
	private String iouId;               // 白条ID
    private String fromOrg;             // 发行机构 
    private String recvOrg;             // 接收机构
    private String transTime;           // 交易时间
    private int amount;                 // 交易金额
    private int paidAmt;                // 已还金额
    private String iouStatus;           // 白条状态 --C,已还清--P,未还清
    private String updateTime;          // 更新时间
    
    
    
	public IouRecord() {
		super();
	}

	public IouRecord(String iouId, String fromOrg, String recvOrg, String transTime, int amount, int paidAmt,
			String iouStatus, String updateTime) {
		super();
		this.iouId = iouId;
		this.fromOrg = fromOrg;
		this.recvOrg = recvOrg;
		this.transTime = transTime;
		this.amount = amount;
		this.paidAmt = paidAmt;
		this.iouStatus = iouStatus;
		this.updateTime = updateTime;
	}

	public String getIouId() {
		return iouId;
	}

	public void setIouId(String iouId) {
		this.iouId = iouId;
	}

	public String getFromOrg() {
		return fromOrg;
	}

	public void setFromOrg(String fromOrg) {
		this.fromOrg = fromOrg;
	}

	public String getRecvOrg() {
		return recvOrg;
	}

	public void setRecvOrg(String recvOrg) {
		this.recvOrg = recvOrg;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(int paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getIouStatus() {
		return iouStatus;
	}

	public void setIouStatus(String iouStatus) {
		this.iouStatus = iouStatus;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "IouRecord [iouId=" + iouId + ", fromOrg=" + fromOrg + ", recvOrg=" + recvOrg + ", transTime="
				+ transTime + ", amount=" + amount + ", paidAmt=" + paidAmt + ", iouStatus=" + iouStatus
				+ ", updateTime=" + updateTime + "]";
	}
    


}
