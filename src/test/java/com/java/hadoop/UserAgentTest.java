package com.java.hadoop;


import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentTest {

	@Test
	public void testReadFile() throws Exception {
		String path = "F:\\data\\100_access.log";
		int i =0;
		UserAgentParser userAgentParser  = new UserAgentParser();
		BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(new File(path))));
		String line = "";
		while (line!=null){
			line = reader.readLine();
			i++;
			if(StringUtils.isNotBlank(line)){
				String source = line.substring(getCharacterPosition(line,"\"",7))+1;
				UserAgent agent = userAgentParser.parse(source);
				String brower = agent.getBrowser();
				String engine = agent.getEngine();
				String engineVersion = agent.getEngineVersion();
				String os = agent.getOs();
				String  plarform = agent.getPlatform();
				System.out.println(brower+" "+engine+" "+engineVersion+" "+os +" " +plarform+" ");
			}
		}
		System.out.println("*****************"+i);
	}

	@Test
    public  void userAgentTest() {
        String source = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.3\n" +
                "6";
        UserAgentParser userAgentParser  = new UserAgentParser();
	    UserAgent agent = userAgentParser.parse(source);
	    String brower = agent.getBrowser();
	    String engine = agent.getEngine();
	    String engineVersion = agent.getEngineVersion();
	    String os = agent.getOs();
	    String  plarform = agent.getPlatform();
        System.out.println(brower+" "+engine+" "+engineVersion+" "+os +" " +plarform+" ");
    }
	@Test
    public void testGetCharracterPosition(){
		String str = "112.26.237.61 - - [10/Nov/2016:00:01:02 +0800] \"POST /api3/getinvitemeuserlist HTTP/1.1\" 200 115 \"www.imooc.com\" \"-\" cid=0&timestamp=1478707262893&uid=228174&secrect=2028f216719bcd959d9c613b99f5fee8&uuid=63feb33bcf15b08c33f027c62738b85e&token=a374b981def84d763fa6365838ff5ca9 \"mukewang/5.0.0 (Android 5.1.1; Xiaomi Redmi Note 3 Build/LMY47V),Network WIFI\" \"-\" 10.100.136.65:80 200 0.055 0.055\n" +
				"10.100.0.1 - - [10/Nov/2016:00:01:02 +0800] \"HEAD / HTTP/1.1\" 301 0 \"117.121.101.40\" \"-\" - \"curl/7.19.7 (x86_64-redhat-linux-gnu) libcurl/7.19.7 NSS/3.16.2.3 Basic ECC zlib/1.2.3 libidn/1.18 libssh2/1.4.2\" \"-\" - - - 0.000";
		int index = getCharacterPosition(str,"\"",7);
		System.out.println(index);
	}

	/**
	 * 获取指定字符串中指定标识的字符出现的索引位置
	 * @param value
	 * @param operator
	 * @param index
	 * @return
	 */
    private int getCharacterPosition(String value,String operator,int index){
		Matcher slashMatcher = Pattern.compile(operator).matcher(value);
		int mIdx = 0;
		while (slashMatcher.find()){
			mIdx++;
			if(mIdx == index){
				break;
			}
		}
		return slashMatcher.start();
	}
}
