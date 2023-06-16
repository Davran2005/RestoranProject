package peaksoft.dto.subcategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SubcategoryResponse {
    private Long id;
    private String name;
    private String categoryName;

    public SubcategoryResponse(Long id, String name, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
    }
}
