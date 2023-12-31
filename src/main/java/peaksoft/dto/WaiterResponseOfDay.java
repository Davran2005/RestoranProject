package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.dto.cheque.ChequeResponse;

import java.util.List;

/**
 * name : kutman
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaiterResponseOfDay {
    private Long id;
    private String firstName;
    private String lastName;
    private List<ChequeResponse> chequeResponses;
    private int allDayChequePrice;
}
