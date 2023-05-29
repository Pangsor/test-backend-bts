package com.walatech.testbackendbts.repository;

import com.walatech.testbackendbts.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {
}
