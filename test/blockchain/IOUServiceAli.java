package blockchain;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.bcos.channel.client.Service;
import org.bcos.channel.handler.ChannelConnections;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Int256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.WalletUtils;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.core.methods.response.EthBlockNumber;
import org.bcos.web3j.protocol.http.HttpService;
import org.bcos.web3j.protocol.parity.Parity;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.jp.exception.initConfigException;
import com.jp.util.PropertiesUtil;
import com.jp.wrapper.SuplInfo;



@Controller
public class IOUServiceAli {
	// 初始化交易参数
//	private static BigInteger gasPrice = new BigInteger("99999999999");
//	private static BigInteger gasLimit = new BigInteger("9999999999999");
	//初始化交易参数
	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");
	private static BigInteger initialWeiValue = new BigInteger("0");
	
	private static Web3j web3j = null;
	private static AbstractApplicationContext Context = null;
	private static Credentials credentials = null;

	private static SuplInfo contractTransaction;
	
	private static List<String> contractAddressList = new ArrayList<>();
	
	private static String subPath = null;
	
	public static void initObj() throws Exception{
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		subPath = path.substring(5, path.length());	
		
		System.out.println("开始测试...");
		System.out.println("===================================================================");

		// 读取applicationContext.xml里的配置信息
		Context = new ClassPathXmlApplicationContext("classpath:ali/applicationContext.xml");
		
		//web3j = buildWeb3jByRPC(Context);
		web3j = buildWeb3j(Context);
			
		try{
    		if(web3j == null){
    			throw new initConfigException("初始化web3j失败");
        	}
    	} catch(initConfigException e){
    		e.printStackTrace();
    	}
		
		System.out.println("调用web3的getBlockNumber接口");
		EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
		System.out.println("获取ethBlockNumber:{}" + ethBlockNumber.getBlockNumber());

		
        System.out.println("初始化交易签名私钥......");
		// 初始化交易签名私钥
		PropertiesUtil.readFile("wallet.properties");
        credentials = WalletUtils.loadCredentials(
                PropertiesUtil.readValue("wallet.password"),
                subPath+PropertiesUtil.readValue("wallet.path"));
        System.out.println("credentials address:{}"+credentials.getAddress());
        
    	System.out.println("加载已部署的合约......");
    	// 加载已部署的合约
    	PropertiesUtil.readFile("ali/config.properties");
    	
    	for(int i = 0; i<1; i++){
    		String contractAddress = PropertiesUtil.readValue("contract.address."+i);
    		System.out.println(contractAddress);
    		contractAddressList.add(contractAddress);
    		
    		Int256 number = new Int256(56); //合约编号
    		Utf8String version = new Utf8String("1.0");  //合约版本号
    		contractTransaction = SuplInfo.deploy(web3j,credentials,gasPrice,gasLimit,initialWeiValue,number,version).get();
    	}
    	System.out.println("初始化结束");
    	
    	System.out.println("调用合约数据......");
    	System.out.println("getVersion:" + contractTransaction.getVersion().get());
    	System.out.println("getNumber:" + contractTransaction.getNumber().get().toString());
    	System.out.println("getIouLength:" + contractTransaction.getIouLength().get().toString());

    	
	}
	
	private static Web3j buildWeb3jByRPC(AbstractApplicationContext context) {
		
		System.out.println("buildWeb3jByRPC.......");
		// 获取一个Service的实例
		Service service = context.getBean(Service.class);
						
		context.close();
				
		ChannelConnections fromChannelConnections = service.getAllChannelConnections().get(service.getOrgID());
		List<String> list = fromChannelConnections.getConnectionsStr();
		for(String str:list){
			System.out.println("ConnectionsStr:"+str);
		}
		String[] split1 = list.get(0).split("@");
		String targetaddress = "";
		if (split1.length > 1) {
			targetaddress = split1[1];
		}
				
		HttpService httpService = new HttpService("http://"+targetaddress);
		Web3j Web3 = Parity.build(httpService);
		
		System.out.println("buildWeb3jByRPC end ......");
		return Web3;
	}
	
	public static Web3j buildWeb3j(AbstractApplicationContext context) throws Exception{
		//初始化Service

		Service service = context.getBean(Service.class);
		service.run();

		Thread.sleep(3000);
		System.out.println("开始测试...");
		System.out.println("===================================================================");
				
		System.out.println("初始化AOMP的ChannelEthereumService");
		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
				
		//使用AMOP消息信道初始化web3j
		Web3j web3 = Web3j.build(channelEthereumService);
		
		return web3;

	}
	
	private static Web3j buildWeb3jByAMOP(AbstractApplicationContext transactionContext) throws Exception {
		Service service = transactionContext.getBean(Service.class);
		service.run();
		Thread.sleep(3000);
		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		//使用AMOP消息信道初始化web3j
		return Web3j.build(channelEthereumService);
	}

	
	public static void queryBlockNumber() throws IOException{
		System.out.println("queryBlockNumber......");
		BigInteger blockNumber = web3j.ethBlockNumber().send().getBlockNumber();
		System.out.println("blockNumber:{}"+blockNumber.intValue());
	}
	
//	public static void helloworldGet() throws InterruptedException, ExecutionException{
//		if(contractTransaction.get()!=null){
//			System.out.println("Future<Utf8String> is not null!");
//			String s = contractTransaction.get().toString();
//			System.out.println("helloworld get:" + s);
//		}else{
//			System.out.println("Future<Utf8String> is null.  helloworldGet fail!");
//		}
//		
//	}
	

//	public static String initIouLimitData(Organ org) throws InterruptedException, ExecutionException{
//		TransactionReceipt receipt = contractTransaction.initIouLimitData(new Utf8String(org.getOrgID()), new Utf8String(org.getOrgName()),new Int256(org.getIouLimit()), new Utf8String(org.getCreateTime()), new Utf8String(org.getUpdateTime())).get();
//		logger.info("initIouLimitData receipt transactionHash:{}",receipt.getTransactionHash());
//		List<InitIouLimitDataEventResponse> responses = contractTransaction.getInitIouLimitDataEvents(receipt);
//		String result=responses.get(0)._json.toString(); 
//		return result;
//	}
	
	@Test
	public void testIOUService() throws Exception {
		IOUServiceAli.initObj();
	}
	
}
