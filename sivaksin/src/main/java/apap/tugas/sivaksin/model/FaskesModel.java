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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
@Entity
@Table(name="faskes")
public class FaskesModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaskes;

    @NotNull
    @Size(max=255)
    @Column(name="nama_faskes", nullable = false)
    private String namaFaskes;

    @NotNull
    @Column(name="kuota", nullable = false)
    private int kuota;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime jamMulai;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime jamTutup;

    @NotNull
    @Size(max=255)
    @Column(name="provinsi", nullable = false)
    private String provinsi;

    @NotNull
    @Size(max=255)
    @Column(name="kabupaten", nullable = false)
    private String kabupaten;

    

//  Relasi dengan VaksinModel
    @ManyToOne
    @JoinColumn(name="vaksin_id")
    private VaksinModel vaksinId;

//      Relasi degan PasienModel
    @ManyToMany
    @JoinTable(
            name = "faskes_pasien",
            joinColumns =  @JoinColumn(name = "id_faskes"),
            inverseJoinColumns = @JoinColumn(name = "id_pasien"))
    List<PasienModel> listPasien;

}
