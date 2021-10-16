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
@Table(name="dokter")
public class DokterModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull
    @Size(max=18)
    @Column(name="nip", nullable = false)
    private String nip;

    @NotNull
    @Size(max=255)
    @Column(name="nama_dokter", nullable = false)
    private String nama_dokter;

    @NotNull
    @Size(max=13)
    @Column(name="no_telepon", nullable = false)
    private String noTelepon;

//      Relasi degan PasienModel
    // @ManyToMany
    // @JoinTable(
    //         name = "dokter_pasien",
    //         joinColumns =  @JoinColumn(name = "id_dokter"),
    //         inverseJoinColumns = @JoinColumn(name = "id_pasien"))
    // List<PasienModel> listPasien;

    @OneToMany(mappedBy = "dokter")
    List<SuntikModel> suntik;

}
