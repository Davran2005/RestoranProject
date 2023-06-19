package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Cheque;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
//    @Query("select concat(u.lastName + ' ' + u.) from User u")
//    SimpleResponse saveCheque(Cheque cheque);
}