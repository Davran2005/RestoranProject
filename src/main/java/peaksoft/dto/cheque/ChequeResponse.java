package peaksoft.dto.cheque;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.dto.menuItmen.MenuItemResponse;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class ChequeResponse {
    private Long id;
    private String fullName;
    private List<MenuItemResponse> itemResponseList;
    private int avaPrice;
    private int services;
    private int grandTotal;
    private LocalDate date;

    public ChequeResponse(Long id, String fullName, List<MenuItemResponse> itemResponseList, int avaPrice, int services, int grandTotal, LocalDate date) {
        this.id = id;
        this.fullName = fullName;
        this.itemResponseList = itemResponseList;
        this.avaPrice = avaPrice;
        this.services = services;
        this.grandTotal = grandTotal;
        this.date = date;
    }
}
