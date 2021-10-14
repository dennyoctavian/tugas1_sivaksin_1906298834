package apap.tugas.sivaksin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
@Entity
@Table(name="suntik")
public class SuntikModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSuntik;

    @ManyToOne
    @JoinColumn(name = "id_pasien")
    PasienModel pasien;

    @ManyToOne
    @JoinColumn(name = "id_dokter")
    DokterModel dokter;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime waktuSuntik;

    @NotNull
    @Column(name="id_faskes", nullable = false)
    Long idFaskes;

    @NotNull
    @Size(max=13)
    @Column(name="batch_id", nullable = false)
    private String BatchId;

}
