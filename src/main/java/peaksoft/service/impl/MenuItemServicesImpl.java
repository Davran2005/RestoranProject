package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.menuItmen.MenuItemRequest;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.MenuItemServices;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MenuItemServicesImpl implements MenuItemServices {
    private final MenuItemRepository repository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public MenuItemResponse save(MenuItemRequest request) {
        return null;
    }

    @Override
    public MenuItemResponse update(Long id, MenuItemRequest request) {
        return null;
    }

    @Override
    public MenuItemResponse getById(Long id) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public List<MenuItemResponse> getAllResponse() {
        return null;
    }

    @Override
    public List<MenuItemResponse> getAllOrder(Boolean descOrAsc) {
        return null;
    }

    @Override
    public List<MenuItemResponse> getAllVega(Boolean vegOrNot) {
        return null;
    }
}
