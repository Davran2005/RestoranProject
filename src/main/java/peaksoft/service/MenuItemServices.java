package peaksoft.service;

import peaksoft.dto.menuItmen.MenuItemRequest;
import peaksoft.dto.menuItmen.MenuItemResponse;


import java.util.List;

public interface MenuItemServices {
    MenuItemResponse save(MenuItemRequest request);

    MenuItemResponse update(Long id, MenuItemRequest request);

    MenuItemResponse getById(Long id);

    String delete(Long id);

    List<MenuItemResponse> getAllResponse();

    List<MenuItemResponse> getAllOrder(Boolean descOrAsc);

    List<MenuItemResponse> getAllVega(Boolean vegOrNot);



}
