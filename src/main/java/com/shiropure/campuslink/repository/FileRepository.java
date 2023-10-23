package com.shiropure.campuslink.repository;

import com.shiropure.campuslink.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {

}
