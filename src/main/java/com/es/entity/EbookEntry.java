package com.es.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.*;
/**
 * (EbookEntry)表实体类
 *
 * @author makejava
 * @since 2022-07-05 11:46:02
 */
@SuppressWarnings("serial")
@Component
@Data
@TableName("ebook_entry")
@AllArgsConstructor
@NoArgsConstructor
public class EbookEntry extends Model<EbookEntry> implements Serializable {
    //编号
    private Integer id;
    //对应分类表的主键
    private Integer cateid;
    //图书名称
    private String title;
    //载要
    private String summary;
    //上传人
    private String upload;
    //日期
    @DateTimeFormat(pattern ="yyyy-mm-dd")
    private Date date;
    //第二次提交



    }

