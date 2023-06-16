package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.ChequeRequest;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ChequeService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public SimpleResponse saveCheque(ChequeRequest request) {
        return null;
    }

    @Override
    public List<ChequeResponse> getAllCheque() {
        return null;
    }

    @Override
    public ChequeResponse getByIdCheque(Long id) {
        return null;
    }

    @Override
    public SimpleResponse update(Long id, ChequeRequest request) {
        return null;
    }

    @Override
    public SimpleResponse delete(Long id) {
        return null;
    }
}
