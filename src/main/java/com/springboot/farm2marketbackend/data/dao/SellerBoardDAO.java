package com.springboot.farm2marketbackend.data.dao;

import com.springboot.farm2marketbackend.data.entity.SellerBoard;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;

import java.util.List;

public interface SellerBoardDAO {
    SellerBoard insertSellerBoard(SellerBoard sellerBoard);
    SellerBoard selectSellerBoard(Long id);
    SellerBoard updateSellerBoard(SellerBoard sellerBoard) throws Exception;

    void deleteSellerBoard(Long id) throws Exception;
    List<SellerBoard> getAllSellerBoard();
}
