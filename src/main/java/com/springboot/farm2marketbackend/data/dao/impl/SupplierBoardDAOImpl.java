package com.springboot.farm2marketbackend.data.dao.impl;

import com.springboot.farm2marketbackend.data.dao.SupplierBoardDAO;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.repository.SupplierBoardRepository;
import com.springboot.farm2marketbackend.service.impl.SupplierBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class SupplierBoardDAOImpl implements SupplierBoardDAO {
    private SupplierBoardRepository supplierBoardRepository;

    @Autowired
    public SupplierBoardDAOImpl(SupplierBoardRepository supplierBoardRepository){
        this.supplierBoardRepository = supplierBoardRepository;
    }

    @Override
    public SupplierBoard insertSupplierBoard(SupplierBoard supplierBoard) {
        SupplierBoard savedSupplierBoard = supplierBoardRepository.save(supplierBoard);

        return savedSupplierBoard;    }

    @Override
    public SupplierBoard selectSupplierBoard(Long id) {
        SupplierBoard selectedSupplierBoard;
        if (id != null && supplierBoardRepository.existsById(id)) {
            selectedSupplierBoard = supplierBoardRepository.getById(id);
        } else {
            selectedSupplierBoard = new SupplierBoard();
        }

        return selectedSupplierBoard;    }

    @Override
    public SupplierBoard updateSupplierBoard(SupplierBoard supplierBoard) throws Exception {
        Optional<SupplierBoard> selectedSupplierBoard = supplierBoardRepository.findById(supplierBoard.getId());

        SupplierBoard updatedSupplierBoard;
        if (selectedSupplierBoard.isPresent()) {
            SupplierBoard SupplierBoard = selectedSupplierBoard.get();

            SupplierBoard.setPrice(SupplierBoard.getPrice());
            SupplierBoard.setProduct(SupplierBoard.getProduct());
            SupplierBoard.setKeyword(SupplierBoard.getKeyword());
            SupplierBoard.setName(SupplierBoard.getName());
            SupplierBoard.setModifiedDate(LocalDateTime.now());

            updatedSupplierBoard = supplierBoardRepository.save(SupplierBoard);
        } else {
            throw new Exception();
        }

        return updatedSupplierBoard;    }

    @Override
    public void deleteSupplierBoard(Long id) throws Exception {
        Optional<SupplierBoard> selectedSupplierBoard = supplierBoardRepository.findById(id);

        if (selectedSupplierBoard.isPresent()) {
            SupplierBoard SupplierBoard = selectedSupplierBoard.get();

            supplierBoardRepository.delete(SupplierBoard);
        } else {
            throw new Exception();
        }
    }
    @Override
    public List<SupplierBoard> getAllSupplierBoard() {
        return supplierBoardRepository.findAll();
    }

}
