package com.es.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mail {
    @Autowired
    private JavaMailSender javaMailSender;
    //发送人
    String from ="1150389709@qq.com";
    //接受人
    String  to="15274104875@163.com";
    //标题
    String subject="测试";
    //正文
    String  context="你在干嘛";
  public  void  wc(){
SimpleMailMessage simpleMailMessage =new       SimpleMailMessage();
simpleMailMessage.setFrom(from+"(肖军杰)");
simpleMailMessage.setTo(to);
simpleMailMessage.setSubject(subject);
simpleMailMessage.setText(context);
javaMailSender.send(simpleMailMessage);
  }
}
