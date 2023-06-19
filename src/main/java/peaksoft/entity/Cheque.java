package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cheques")
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_seq")
    @SequenceGenerator(name = "cheque_seq")
    private Long id;
    private int priceAverage;
    private LocalDate createdAt;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private User user;

    @ManyToMany(mappedBy = "cheques",cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
    private List<MenuItem> menuItems;


}