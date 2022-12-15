package com.moneim.api.fileUpload;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.Properties;

@Component
public class FtpUtil {
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
    /**
     *FTP server IP address
     */
    private static String host;
    @Value("${ftp.host}")
    public void setHost(String val){
        FtpUtil.host = val;
    }
    /**
     *Port
     */
    private static int port;
    @Value("${ftp.port}")
    public void setPort(int val){
        FtpUtil.port = val;
    }
    /**
     *User name
     */
    private static String userName;
    @Value("${ftp.userName}")
    public void setUserName(String val){
        FtpUtil.userName = val;
    }
    /**
     *Code
     */
    private static String password;
    @Value("${ftp.password}")
    public void setPassword(String val){
        FtpUtil.password = val;
    }
    /**
     *The root directory where the pictures are stored
     */
    private static String rootPath;
    @Value("${ftp.rootPath}")
    public void setRootPath(String val){
        FtpUtil.rootPath = val;
    }
    /**
     *Path to store pictures
     */
    private static String imgUrl;
    @Value("${ftp.imgUrl}")
    public void setImgUrl(String val){
        FtpUtil.imgUrl = val;
    }
    /**
     *Get connection
     */
    private static ChannelSftp getChannel() throws Exception{
        JSch jsch = new JSch();
        //->ssh root@host:port
        Session sshSession = jsch.getSession(userName,host,port);
        //Code
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        return (ChannelSftp) channel;
    }
    /**
     *FTP upload picture
     *@ param InputStream picture IO stream
     *@ param imagepath path, create directory if it does not exist
     *@ param imagesname picture name
     *@ return urlstr the storage path of the picture
     */
    public static String putImages(InputStream inputStream, String imagePath, String imagesName){
        try {
            ChannelSftp sftp = getChannel();
            //Here i ll change root path to avoid error no such file
            //beacuae idk why i get error when i try to change it with value in appllication.properties
            String path = rootPath + imagePath + "/";
            createDir(path,sftp);
            //Upload file "C:\\Users\\monei\\Documents\\ngnixfiles"
            sftp.put(inputStream,path + imagesName);
            logger.info (inputStream.toString());
            logger.info ("upload succeeded!");
            sftp.quit();
            sftp.exit();
            //Path returned by processing
            String resultFile;
            resultFile = imgUrl + imagePath + imagesName;
            return resultFile;
        } catch (Exception e) {
            logger. info ("upload failed:" + e.getMessage());
        }
        return "";
    }
    /**
     *Create directory
     */
    private static void createDir(String path,ChannelSftp sftp) throws SftpException {
        String[] folders = path.split("/");
        sftp.cd("/");
        for ( String folder : folders ) {
            if ( folder.length() > 0 ) {
                try {
                    sftp.cd( folder );
                }catch ( SftpException e ) {
                    sftp.mkdir( folder );
                    sftp.cd( folder );
                }
            }
        }
    }
    /**
     *Delete picture
     */
    public static void delImages(String imagesName){
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + imagesName;
            sftp.rm(path);
            sftp.quit();
            sftp.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
