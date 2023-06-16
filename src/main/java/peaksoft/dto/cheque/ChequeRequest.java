package peaksoft.dto.cheque;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ChequeRequest{
        private Long waiterId;
        private List<String> menuName;

        public ChequeRequest(Long waiterId, List<String> menuName) {
                this.waiterId = waiterId;
                this.menuName = menuName;
        }
}

