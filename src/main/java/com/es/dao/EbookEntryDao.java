package com.es.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.es.entity.EbookEntry;
import org.apache.ibatis.annotations.Mapper;

/**
 * (EbookEntry)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-05 11:46:02
 */
@Mapper
public interface EbookEntryDao extends BaseMapper<EbookEntry> {

}

