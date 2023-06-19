package peaksoft.dto.cheque;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ChequeRequest{
       private int priceAverage;

        public ChequeRequest(int priceAverage) {
                this.priceAverage = priceAverage;
        }
}

