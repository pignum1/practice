package com.cloud.controller;

import com.cloud.common.OperateResult;
import com.cloud.entity.FileInfo;
import com.cloud.entity.User;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;

/**
 * @author WXY
 * @ClassName MongodbController
 * @Description T0D0
 * @Date 2019/8/16 17:16
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mongodb")
public class MongodbController {

    //简单的Collection存储对象
    @Autowired
    MongoTemplate mongotemplate;

    // 获得SpringBoot提供的mongodb的GridFS对象
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public OperateResult home() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setPhone("test");
        user.setQuestion("test");
        user.setAnswer("test");
        user.setRole(0);
        user.setId("test");
        user.setCreateTime(new Date());
        user.setCreateBy(0L);
        user.setLastModifiedTime(new Date());
        user.setLastModifiedBy("test");

        mongotemplate.save( user );
        return OperateResult.operationSuccess( "save1" );
    }


    @PostMapping("/fileSave")
    public OperateResult saveFile(@RequestParam("file")MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            // 获得文件输入流
            InputStream ins = file.getInputStream();
            // 获得文件类型
            String contentType = file.getContentType();
            GridFSFile gridFSFile = gridFsTemplate.store(ins, fileName, contentType);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName("");
            fileInfo.setFileType("");
            fileInfo.setFileId("");
            fileInfo.setCreator("");
            return OperateResult.operationSuccess( "存文件成功" );
        }catch (Exception e){
            e.printStackTrace();
            return OperateResult.operationFailure( e.getMessage() );
        }
    }


    @RequestMapping("/downFile")
    public void downloadFile(@RequestParam(name = "file_id") String fileId,HttpServletRequest request,HttpServletResponse response) throws Exception {
        Query query = Query.query( Criteria.where("_id").is(fileId));
        GridFSDBFile gfsfile = gridFsTemplate.findOne(query);
        if (gfsfile == null) {
            return ;
        }

        String fileName = gfsfile.getFilename().replace(",", "");
        //处理中文文件名乱码
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 通知浏览器进行文件下载
        response.setContentType(gfsfile.getContentType());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        gfsfile.writeTo(response.getOutputStream());
    }

    @PostMapping("/deleteFile")
    public OperateResult deleteFile(String fileId){
        Query query = Query.query(Criteria.where("_id").is(fileId));
        // 查询单个文件
        GridFSDBFile gfsfile = gridFsTemplate.findOne(query);
        if (gfsfile == null) {
            return OperateResult.operationFailure( "没有找到文件" );
        }
        gridFsTemplate.delete(query);
        return OperateResult.operationSuccess( "删除成功" );
    }

    //5d57c12f4a0336133c2c2dad

    //5d57c12f4a0336133c2c2dac



}