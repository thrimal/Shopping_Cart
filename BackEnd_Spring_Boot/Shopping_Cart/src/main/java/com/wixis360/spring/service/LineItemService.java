package com.wixis360.spring.service;

import com.wixis360.spring.dto.LineItemDTO;


import java.util.List;

public interface LineItemService {
    boolean saveLineItems(LineItemDTO dto);
    boolean updateLineItems(LineItemDTO dto);
    LineItemDTO searchLineItems(String id);
    boolean deleteLineItems(String id);
    List<LineItemDTO> getAllLineItems();
}
