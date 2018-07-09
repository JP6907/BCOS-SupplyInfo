package com.jp.po;

public class IouLimitEntity {
	private String orgID;                 // 机构号
    private String orgName;               // 机构名称 
    private int iouLimit;                 // 白条额度
    private String createTime;            // 创建时间
    private String updateTime;            // 更新时间
    
    public IouLimitEntity() {
		super();
	}
	public IouLimitEntity(String orgID, String orgName, int iouLimit, String createTime, String updateTime) {
		super();
		this.orgID = orgID;
		this.orgName = orgName;
		this.iouLimit = iouLimit;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getIouLimit() {
		return iouLimit;
	}

	public void setIouLimit(int iouLimit) {
		this.iouLimit = iouLimit;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "IouLimitEntity [orgID=" + orgID + ", orgName=" + orgName + ", iouLimit=" + iouLimit + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

    
}
