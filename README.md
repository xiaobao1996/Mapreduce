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
	<plugins>
            <!-->mvn assembly:assembly<-->
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                  <archive>
                    <manifest>
                      <mainClass></mainClass>
                    </manifest>
                  </archive>
                  <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                  </descriptorRefs>
                </configuration>
            </plugin>
          </plugins>
	
	
mvn clean package -Dmaven.test.skip=true
mvn clean install -DskipTest
mvn assembly:assembly

hadoop jar /hadoop/data/lib/java_hadoop-1.0-SNAPSHOT-jar-with-dependencies.jar com.java.hadoop.project.LogCountAPP hdfs://192.168.124.128:8020/10000_access.log hdfs://192.168.124.128:8020/output/brower

结果
Chrome  2775
Firefox 327
MSIE    78
Safari  115
Unknown 6705
