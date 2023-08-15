package com.springboot.farm2marketbackend.data.dao.impl;

import com.springboot.farm2marketbackend.data.dao.SellerBoardDAO;
import com.springboot.farm2marketbackend.data.entity.SellerBoard;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.repository.SellerBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class SellerBoardDAOImpl implements SellerBoardDAO {
    private SellerBoardRepository sellerBoardRepository;

    @Autowired
    public SellerBoardDAOImpl(SellerBoardRepository sellerBoardRepository){
        this.sellerBoardRepository = sellerBoardRepository;
    }

    @Override
    public SellerBoard insertSellerBoard(SellerBoard sellerBoard) {
        SellerBoard savedSellerBoard = sellerBoardRepository.save(sellerBoard);

        return savedSellerBoard;    }

    @Override
    public SellerBoard selectSellerBoard(Long id) {
        SellerBoard selectedSellerBoard;
        if (id != null && sellerBoardRepository.existsById(id)) {
            selectedSellerBoard = sellerBoardRepository.getById(id);
        } else {
            selectedSellerBoard = new SellerBoard();
        }

        return selectedSellerBoard;    }

    @Override
    public SellerBoard updateSellerBoard(SellerBoard sellerBoard) throws Exception {
        Optional<SellerBoard> selectedSellerBoard = sellerBoardRepository.findById(sellerBoard.getId());

        if (selectedSellerBoard.isPresent()) {
            SellerBoard existingSellerBoard = selectedSellerBoard.get();
        if (sellerBoard.getImage() != null) {
            existingSellerBoard.setImage(sellerBoard.getImage());
        }

        existingSellerBoard.setPrice(sellerBoard.getPrice());
        existingSellerBoard.setProduct(sellerBoard.getProduct());
        existingSellerBoard.setKeyword(sellerBoard.getKeyword());
        existingSellerBoard.setName(sellerBoard.getName());
        existingSellerBoard.setModifiedDate(LocalDateTime.now());

        SellerBoard updatedSellerBoard = sellerBoardRepository.save(existingSellerBoard);

        return updatedSellerBoard;
    } else {
        throw new Exception("해당 id에 해당하는 데이터가 존재하지 않습니다.");
     }}

    @Override
    public void deleteSellerBoard(Long id) throws Exception {
        Optional<SellerBoard> selectedSellerBoard = sellerBoardRepository.findById(id);

        if (selectedSellerBoard.isPresent()) {
            SellerBoard sellerBoard = selectedSellerBoard.get();

            sellerBoardRepository.delete(sellerBoard);
        } else {
            throw new Exception();
        }
    }
    @Override
    public List<SellerBoard> getAllSellerBoard() {
        return sellerBoardRepository.findAll();
    }

}
