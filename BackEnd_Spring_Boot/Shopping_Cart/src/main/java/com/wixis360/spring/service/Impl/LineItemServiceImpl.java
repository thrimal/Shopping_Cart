package com.wixis360.spring.service.Impl;

import com.wixis360.spring.dto.LineItemDTO;
import com.wixis360.spring.entity.LineItem;
import com.wixis360.spring.repo.CartRepo;
import com.wixis360.spring.repo.LineItemRepo;
import com.wixis360.spring.service.LineItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LineItemServiceImpl implements LineItemService {

    @Autowired
    private LineItemRepo lineItemRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    ModelMapper mapper;

    @Transactional
    @Override
    public boolean saveLineItems(LineItemDTO dto) {

        if (cartRepo.existsById(dto.getCartId())){
            if (!lineItemRepo.existsById(dto.getCartId())) {
                LineItem details = mapper.map(dto, LineItem.class);
                lineItemRepo.save(details);
                return true;
            } else {
                throw new RuntimeException("LineItem Details already exist..!");
            }
        }else {
            throw new  RuntimeException("Plz check your cart added....? ");
        }
    }

    @Override
    public boolean updateLineItems(LineItemDTO dto) {
        if (lineItemRepo.existsById(dto.getCartId())) {
            LineItem lineItem = mapper.map(dto, LineItem.class);
            lineItemRepo.save(lineItem);
            return true;
        } else {
            throw new RuntimeException("No such lineItem for update..!");
        }
    }

    @Override
    public LineItemDTO searchLineItems(String id) {
        Optional<LineItem> details = lineItemRepo.findById(id);
        if (details.isPresent()) {
            return mapper.map(details.get(), LineItemDTO.class);
        } else {
            throw new RuntimeException("No LineItem Details for id: " + id);
        }
    }

    @Override
    public boolean deleteLineItems(String id) {
        if (lineItemRepo.existsById(id)) {
            lineItemRepo.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("No LineItem Details for delete ID: " + id);
        }
    }

    @Override
    public List<LineItemDTO> getAllLineItems() {
        List<LineItem> all = lineItemRepo.findAll();
        return mapper.map(all, new TypeToken<List<LineItemDTO>>() {
        }.getType());
    }
}
