package com.cloud.controller;

import com.cloud.common.OperateResult;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;

/**
 * @author WXY
 * @ClassName MailSendController
 * @Description T0D0
 * @Date 2019/8/3 21:47
 * @Version 1.0
 **/
@RestController
@RequestMapping("/email")
public class MailSendController {

    @Value("${spring.mail.username}")
    private String fromAccount ;

    /**
     * 根据配置文件创建发送邮件的实例
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;


    /**
     * 简单文字邮件发送
     * @return
     */
    @PostMapping("/sendEmail")
    public OperateResult sendEmail(){
        try {
            //邮件内容
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom( "xxxxx@qq.com");
            message.setTo( "xxxxx@qq.com" );
            message.setSubject( "主题：简单邮件" );
            message.setText( "测试邮件内容" );
            javaMailSender.send( message );
            return OperateResult.operationSuccess( "发送邮件成功" );
        }catch (Exception e){
            e.printStackTrace();
            return OperateResult.operationFailure( e.getMessage() );
        }
    }

    /**
     * 发送带附件的邮件
     */
    @PostMapping("/sendEmailFile")
    public OperateResult sendEmailFile(){
        try {
            //邮件内容

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom( "xxxxx@qq.com" );
            helper.setTo( "xxxxx@qq.com" );
            helper.setSubject( "主题：附件邮件" );
            helper.setText( "附件邮件内容" );
            FileSystemResource file = new FileSystemResource(new File(Thread.currentThread().getContextClassLoader().getResource("test.jpg").getFile()));
            helper.addAttachment("附件-1.jpg", file);
            helper.addAttachment("附件-2.jpg", file);


            javaMailSender.send( mimeMessage );
            return OperateResult.operationSuccess( "发送邮件成功" );
        }catch (Exception e){
            e.printStackTrace();
            return OperateResult.operationFailure( e.getMessage() );
        }
    }


    /**
     * 发送模板化的邮件
     */
    @PostMapping("/sendEmailModel")
    public OperateResult sendEmailModel(){
        try {
            //邮件内容
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom( "xxxxx@qq.com" );
            helper.setTo( "xxxxx@qq.com" );
            helper.setSubject( "主题：模板邮件" );
            helper.setText( "模板邮件内容" );

            VelocityContext context = new VelocityContext();
            context.put("userName", "胖虎");
            StringWriter stringWriter = new StringWriter();
            // 需要注意第1个参数要全路径，否则会抛异常
            velocityEngine.mergeTemplate("/templates/template.vm", "UTF-8", context, stringWriter);
            helper.setText( stringWriter.toString(),true );
            javaMailSender.send( mimeMessage );
            return OperateResult.operationSuccess( "发送邮件成功" );
        }catch (Exception e){
            e.printStackTrace();
            return OperateResult.operationFailure( e.getMessage() );
        }
    }


    @PostMapping("/sendEmailQuiet")
    public OperateResult sendEmailQuiet(){
        try {
            //邮件内容
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom( "xxxxx@qq.com" );
            helper.setTo( "xxxxx@qq.com" );
            helper.setSubject( "主题：嵌入静态资源邮件" );
            helper.setText( "嵌入静态资源邮件邮件内容" );
//            helper.setText("<html><body><img src=\"cid:test\" ></body></html>", true);

            FileSystemResource file = new FileSystemResource(new File(Thread.currentThread().getContextClassLoader().getResource("test.jpg").getFile()));
            helper.addInline("test", file);
            javaMailSender.send( mimeMessage );
            return OperateResult.operationSuccess( "发送邮件成功" );
        }catch (Exception e){
            e.printStackTrace();
            return OperateResult.operationFailure( e.getMessage() );
        }
    }


}