package blockchain;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jp.po.IouLimitEntity;
import com.jp.po.IouRecord;
import com.jp.po.Transaction;
import com.jp.service.IIouLimitEntityService;
import com.jp.service.IIouRecordService;
import com.jp.service.ITransactionService;
import com.jp.service.impl.IOUService;


public class DBTest {

/*
	@Test
	public void testInitIouLimitData() throws Exception {
		// 初始化机构1的白条额度信息
//		IOUService.initObj();
		IouLimitEntity iouLimitEntity = new IouLimitEntity();
		iouLimitEntity.setOrgID("Org1");
		iouLimitEntity.setOrgName("OrgA");
		iouLimitEntity.setIouLimit(10000);
		iouLimitEntity.setCreateTime("2018-07-02");
		iouLimitEntity.setUpdateTime("2018-07-02");
		System.out.println(IOUService.initIouLimitData(iouLimitEntity));
//		
//		// 初始化机构2的白条额度信息
		IouLimitEntity iouLimitEntity2 = new IouLimitEntity();
		iouLimitEntity2.setOrgID("Org2");
		iouLimitEntity2.setOrgName("OrgB");
		iouLimitEntity2.setIouLimit(5000);
		iouLimitEntity2.setCreateTime("2017-07-02");
		iouLimitEntity2.setUpdateTime("2017-07-02");
		System.out.println(IOUService.initIouLimitData(iouLimitEntity2));
		
		//获取机构1白条额度
		System.out.println(IOUService.getIouLimit("Org1"));
		//获取机构2白条额度
		System.out.println(IOUService.getIouLimit("Org2"));
		
		//修改机构1的白条额度
		System.out.println(IOUService.setIouLimit("Org1", 10001));
		//修改机构2的白条额度
		System.out.println(IOUService.setIouLimit("Org2", 5001));
		//获取机构1白条额度
		System.out.println(IOUService.getIouLimit("Org1"));
		//获取机构2白条额度
		System.out.println(IOUService.getIouLimit("Org2"));

		//交易
		Transaction transaction = new Transaction();
		transaction.setConID("12345");//从12345开始
		transaction.setSaleOrg("Org1");
		transaction.setBuyOrg("Org2");
		transaction.setTransType("XX");
		transaction.setAmount(1000L);
		transaction.setConHash("0x483e58985e9a80a215944fdaabe8sdfge23g3df8ba90741f");
		transaction.setLastestStatus("P");
		transaction.setTransTime("2018-06-30");
		transaction.setUpdateTime("2018-07-02");
		System.out.println("add transaction is: "+IOUService.addTransaction(transaction));
		// 通过conId查询交易
		System.out.println("query transaction by conId"+IOUService.queryTransactionByConId("12345"));
		// 更新交易
		System.out.println(IOUService.updateTransStatus("12345","C"));
		// 回收白条
		System.out.println(IOUService.iouRecycle("12345",1000));
		System.out.println();

	}
*/	
	@Test
	public void testDatabase() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		IIouLimitEntityService iouLimitEntityService = (IIouLimitEntityService)ac.getBean("IIouLimitEntityService");
		IIouRecordService iouRecordService = (IIouRecordService)ac.getBean("IIouRecordService");
		ITransactionService transactionService = (ITransactionService)ac.getBean("ITransactionService");
		
		IouLimitEntity iouLimitEntity = new IouLimitEntity();
		iouLimitEntity.setOrgID("Org1");
		iouLimitEntity.setOrgName("OrgA");
		iouLimitEntity.setIouLimit(10000);
		iouLimitEntity.setCreateTime("2018-07-02");
		iouLimitEntity.setUpdateTime("2018-07-02");
		
		iouLimitEntityService.addIouLimitEntity(iouLimitEntity);
		
		IouLimitEntity iouLimitEntity2 = new IouLimitEntity();
		iouLimitEntity2.setOrgID("Org2");
		iouLimitEntity2.setOrgName("OrgB");
		iouLimitEntity2.setIouLimit(5000);
		iouLimitEntity2.setCreateTime("2017-07-02");
		iouLimitEntity2.setUpdateTime("2017-07-02");
		iouLimitEntityService.addIouLimitEntity(iouLimitEntity2);
		
		//获取机构1白条额度
		System.out.println(iouLimitEntityService.getIouLimit("Org1"));
		//获取机构2白条额度
		System.out.println(iouLimitEntityService.getIouLimit("Org2"));
				
		//修改机构1的白条额度
		iouLimitEntityService.setIouLimit(10001,"2018-07-05","Org1");
		//修改机构2的白条额度
		iouLimitEntityService.setIouLimit(5001,"2018-07-05","Org2");
		//获取机构1白条额度
		System.out.println(iouLimitEntityService.getIouLimit("Org1"));
		//获取机构2白条额度
		System.out.println(iouLimitEntityService.getIouLimit("Org2"));
		
		Transaction transaction = new Transaction();
		transaction.setConID("12345");//从12345开始
		transaction.setSaleOrg("Org1");
		transaction.setBuyOrg("Org2");
		transaction.setTransType("XX");
		transaction.setAmount(1000L);
		transaction.setConHash("0x41f");
		transaction.setLatestStatus("P");
		transaction.setTransTime("2018-06-30");
		transaction.setUpdateTime("2018-07-02");
		transactionService.addTransactionRecord(transaction);
		IouRecord iouRecord = new IouRecord();
		iouRecord.setIouId("12345");
		iouRecord.setFromOrg("Org1");
		iouRecord.setRecvOrg("Org2");
		iouRecord.setTransTime("2018-06-30");
		iouRecord.setAmount(1000);
		iouRecord.setPaidAmt(0);
		iouRecord.setIouStatus("P");
		iouRecord.setUpdateTime("2018-07-02");
		
		iouRecordService.addIouRecord(iouRecord);
		//打完白条之后白条额度应该减少,这里还没实现
		
		// 通过conId查询交易
		transactionService.getTransactionByConId("12345");
		// 更新交易
		transactionService.updateTransactionStatusByConId("12345","C");
		// 回收白条
		iouLimitEntityService.recycleIou("12345",1000);
		System.out.println();
		
	}
}
