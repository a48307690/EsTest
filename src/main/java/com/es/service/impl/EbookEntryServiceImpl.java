package com.es.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.dao.EbookEntryDao;
import com.es.entity.EbookEntry;
import com.es.service.EbookEntryService;
import org.springframework.stereotype.Service;

/**
 * (EbookEntry)表服务实现类
 *
 * @author makejava
 * @since 2022-07-05 11:46:03
 */
@Service("ebookEntryService")
public class EbookEntryServiceImpl extends ServiceImpl<EbookEntryDao, EbookEntry> implements EbookEntryService {

}

