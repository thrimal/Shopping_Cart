package com.wixis360.spring.repo;

import com.wixis360.spring.entity.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LineItemRepo extends JpaRepository<LineItem,String> {
     @Modifying
     @Query(value = "delete from line_item where order_id=?", nativeQuery = true)
     void deleteByOrderId(@Param("id") String id);
//     boolean deleteByOrderId(String id);
}
