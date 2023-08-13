package com.springboot.farm2marketbackend.data.dao;

import com.springboot.farm2marketbackend.data.entity.Question;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.service.impl.SupplierBoardServiceImpl;

import java.util.List;

public interface SupplierBoardDAO {
    SupplierBoard insertSupplierBoard(SupplierBoard supplierBoard);
    SupplierBoard selectSupplierBoard(Long id);
    SupplierBoard updateSupplierBoard(SupplierBoard supplierBoard) throws Exception;

    void deleteSupplierBoard(Long id) throws Exception;
    List<SupplierBoard> getAllSupplierBoard();
}
