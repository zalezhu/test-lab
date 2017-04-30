package com.zale.tools.clawer.repository.mongo;

import com.zale.tools.clawer.entity.RoughInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Zale on 2017/4/29.
 */
public interface RoughInfoRepo extends MongoRepository<RoughInfo,String>{
}
