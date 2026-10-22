package no.hvl.dat108;

import jakarta.persistence.*;

@Entity
@Table(schema = "ansatt_avdeling")
public class Avdeling {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	
	private String navn;
	
	public Avdeling() {}
	
	@Override
	public String toString() {
		return String.format("Avdeling: id=%d, navn=%s", id, navn);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

}
