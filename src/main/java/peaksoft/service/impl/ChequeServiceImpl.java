package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.WaiterRequest;
import peaksoft.dto.WaiterResponseOfDay;
import peaksoft.dto.cheque.ChequeRequest;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.dto.restoran.AvmSumResResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ChequeService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
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
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cheque with id:%s is not present", id)));
        Restaurant restaurant = restaurantRepository.findById(1L).orElseThrow(() -> new NotFoundException(String.format("Restaurant with id:%s is not present", 1L)));
        return ChequeResponse.builder()
                .id(cheque.getId())
                .fullName(cheque.getUser().getFirstName() + " " + cheque.getUser().getLastName())
                .itemResponseList(cheque.getMenuItems())
                .services(restaurant.getServices())
                .avaPrice(cheque.getPriceAverage())
                .grandTotal((int) (cheque.getPriceAverage() * 0.15))
                .build();
    }

    @Override
    public SimpleResponse update(Long id, ChequeRequest request) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cheque with id: " + id + "is not found")));
        cheque.setPriceAverage(request.getPriceAverage());
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        if (chequeRepository.existsById(id)) {
            chequeRepository.deleteById(id);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Успешно")
                    .build();
        }else {
            throw new NotFoundException(String.format("Cheque with id: " + id + "is not found"));
        }
    }

    @Override    public AvmSumResResponse totalDaily(LocalDate date) {
        return null;
    }

    @Override
    public WaiterResponseOfDay totalPriceWalter(WaiterRequest waiterRequest) {
        User user = userRepository.findById(waiterRequest.getWaiterId())
                .orElseThrow(() ->
                        new NotFoundException(String.format("User with email :%s already exists", waiterRequest.getWaiterId())));
        List<Cheque> chequeList = chequeRepository.findAll().stream().filter(cheque -> cheque.getUser().getId() == waiterRequest.getWaiterId()).toList();
        List<Cheque> cheques = chequeList.stream().filter(cheque -> cheque.getCreatedAt().equals(waiterRequest.getDay())).toList();
        WaiterResponseOfDay waiterResponseOfDay = new WaiterResponseOfDay();
        waiterResponseOfDay.setFirstName(user.getFirstName());
        waiterResponseOfDay.setLastName(user.getLastName());
        waiterResponseOfDay.setId(user.getId());
        waiterResponseOfDay.setChequeResponses(cheques);
        int number = 0;
        for (ChequeResponse c : cheques) {
            number += c.getGrandTotal();
        }
        waiterResponseOfDay.setAllDayChequePrice(number);
        return waiterResponseOfDay;
    }
}

