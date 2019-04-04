package com.hik.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hik.Exception.SftpuploadException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTP {
	protected Log log = LogFactory.getLog(this.getClass());
	/**
	* 连接sftp服务器
	* @param host 主机
	* @param port 端口
	* @param username 用户名
	* @param password 密码
	* @return
	*/
	public ChannelSftp connect(String host, int port, String username,
		String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			log.info("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			log.info("Session connected.");
			log.info("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			log.info("Connected to " + host + ".");
		} catch (Exception e) {
	
		}
		return sftp;
	}

	/**
	* 上传文件
	* @param directory 上传的目录
	* @param uploadFile 要上传的文件
	* @param sftp
	*/
	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file=new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* 下载文件
	* @param directory 下载目录
	* @param downloadFile 下载的文件
	* @param saveFile 存在本地的路径
	* @param sftp
	*/
	public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file=new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* 删除文件
	* @param directory 要删除文件所在目录
	* @param deleteFile 要删除的文件
	* @param sftp
	*/
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 、windows同一个系统内部 复制文件夹
	 */
    public static void copyFile(String oldPath, String newPath) { 
        try { 
            int bytesum = 0; 
            int byteread = 0; 
            File oldfile = new File(oldPath); 
            if (oldfile.exists()) {                  //文件存在时 
                InputStream inStream = new FileInputStream(oldPath);      //读入原文件 
                FileOutputStream fs = new FileOutputStream(newPath); 
                byte[] buffer = new byte[1444]; 
                int length; 
                while ( (byteread = inStream.read(buffer)) != -1) { 
                    bytesum += byteread;            //字节数 文件大小 
                    System.out.println(bytesum); 
                    fs.write(buffer, 0, byteread); 
                } 
                inStream.close(); 
            } 
        }  catch (Exception e) { 
            System.out.println("复制单个文件操作出错"); 
            e.printStackTrace(); 
        } 
    }  
    public static void main(String[] args) {
		String from = "http://218.108.10.25:8080/upload/dog1.jpg";
		String to="http://218.108.10.25.8081/resource/dog1.jpg";
		copyFile(from,to);
		System.out.println("sucess");
	}
}
