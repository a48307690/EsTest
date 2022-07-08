package com.es.controller;



import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.entity.EbookEntry;
import com.es.service.EbookEntryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (EbookEntry)表控制层
 *
 * @author makejava
 * @since 2022-07-05 11:46:01
 */
@RestController
@RequestMapping("ebookEntry")

public class EbookEntryController {

    /**
     * 服务对象
     */
    @Resource
    private EbookEntryService ebookEntryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param ebookEntry 查询实体
     * @return 所有数据
     */
    @GetMapping
//    @Cacheable(value = "a",key ="#page+''+#ebookEntry")
    @Cached(name ="book_",key ="#page+''+#ebookEntry",expire = 3600,cacheType =CacheType.REMOTE) //远程缓存
    @Scheduled(cron = "0/5 * * * * ?")
    public R selectAll(Page<EbookEntry> page, EbookEntry ebookEntry) {
        return new R(this.ebookEntryService.page(page, new QueryWrapper<>(ebookEntry)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
//    @Cacheable(value ="name",key = "#id")
 @Cached(name = "cx_",key = "#id")
    public R selectOne(@PathVariable Serializable id) {
        return new R(this.ebookEntryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param ebookEntry 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EbookEntry ebookEntry) {
        return new R(this.ebookEntryService.save(ebookEntry));
    }

    /**
     * 修改数据
     *
     * @param ebookEntry 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EbookEntry ebookEntry) {
        return new R(this.ebookEntryService.updateById(ebookEntry));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return new R(this.ebookEntryService.removeByIds(idList));
    }
}

