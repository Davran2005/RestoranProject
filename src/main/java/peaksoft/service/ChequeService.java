package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.WaiterRequest;
import peaksoft.dto.WaiterResponseOfDay;
import peaksoft.dto.cheque.ChequeRequest;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.dto.restoran.AvmSumResResponse;

import java.time.LocalDate;
import java.util.List;

public interface ChequeService {
    SimpleResponse saveCheque(ChequeRequest request);
    List<ChequeResponse> getAllCheque();
    ChequeResponse getByIdCheque(Long id);
    SimpleResponse update(Long id,ChequeRequest request);
    SimpleResponse delete(Long id);
    AvmSumResResponse totalDaily(LocalDate date);
    WaiterResponseOfDay totalPriceWalter(WaiterRequest waiterRequest);

}
