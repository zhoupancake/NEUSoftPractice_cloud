package com.system.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Meta Object Handler of MyBatis Plus
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * auto-insert when inserting to database
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "date", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "submittedTime", LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * auto-update when updating to database
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "lastUpdatedBy", UserMessage.getUsername());
        this.fillStrategy(metaObject, "lastUpdateDate", LocalDateTime.now());
    }
}
