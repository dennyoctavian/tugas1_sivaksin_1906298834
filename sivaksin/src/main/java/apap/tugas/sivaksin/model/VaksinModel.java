package apap.tugas.sivaksin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
@Entity
@Table(name="vaksin")
public class VaksinModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVaksin;

    @NotNull
    @Size(max=255)
    @Column(name="asal_negara", nullable = false)
    private String asalNegara;

    @NotNull
    @Column(name="efikasi", nullable = false)
    private double efikasi;

    @NotNull
    @Size(max=255)
    @Column(name="jenis_vaksin", nullable = false)
    private String jenisVaksin;

    // @OneToMany(mappedBy = "vaksin")
    // List<FaskesModel> faskes;
}
