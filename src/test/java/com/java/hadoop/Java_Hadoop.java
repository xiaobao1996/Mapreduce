package com.java.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;


public class Java_Hadoop {
    public static final String HDFS_PATH = "hdfs://192.168.124.128:8020";
    FileSystem fileSystem = null;
    Configuration configuration =null;

    @Before
    public void setUp() throws  Exception{
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH),configuration,"root");
    }
    @After
    public  void tearDown() throws Exception{
        configuration = null;
        fileSystem = null;
    }
    /**
     * 创建文件夹
     * */
    @Test
    public void mkdir() throws  Exception{
        fileSystem.mkdirs(new  Path("/hdfsapi/test"));
    }
    /**
     * 创建文件
     * */
    @Test
    public void create() throws  Exception{
        FSDataOutputStream output = fileSystem.create(new Path("/hdfsapi/test/hello.txt"));
        output.write("hello world".getBytes());
        output.flush();
        output.close();
    }
    /**
     * 查看HDFS内容
     * */
    @Test
    public void cat() throws Exception{
        FSDataInputStream in =  fileSystem.open(new Path("/hdfsapi/test/hello.txt"));
        IOUtils.copyBytes(in,System.out,1024);
        in.close();
    }

    /**
     * 重命名
     * @throws Exception
     */
    @Test
    public void rename() throws Exception{
        Path oldPath = new Path("/hdfsapi/test/hello.txt");
        Path newPath = new Path("/hdfsapi/test/hi.txt");
        fileSystem.rename(oldPath,newPath);
    }

    /**
     * 上传本地文件至HDFS
     * @throws Exception
     */
    @Test
    public void copyFromLocalFile() throws Exception {
        Path localPath = new Path("F:\\demo.txt");
        Path hdfsPath = new Path("/hdfsapi/test");
        fileSystem.copyFromLocalFile(localPath, hdfsPath);
    }
    /**
     * 下载HDFS文件
     */
    @Test
    public void copyToLocalFile() throws Exception {
        Path localPath = new Path("/opt/h.txt");
        Path hdfsPath = new Path("/hdfsapi/test/hi.txt");
        fileSystem.copyToLocalFile(hdfsPath, localPath);
    }
    /**
     * 查看某个目录下的所有文件
     */
    @Test
    public void listFiles() throws Exception {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));

        for(FileStatus fileStatus : fileStatuses) {
            String isDir = fileStatus.isDirectory() ? "文件夹" : "文件";
            short replication = fileStatus.getReplication();
            long len = fileStatus.getLen();
            String path = fileStatus.getPath().toString();

            System.out.println(isDir + "\t" + replication + "\t" + len + "\t" + path);
        }

    }

    /**
     * 删除
     */
    @Test
    public void delete() throws Exception{
        fileSystem.delete(new Path("/"), true);
    }

}
