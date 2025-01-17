package com.example;
import com.example.jdbc.Masina;
import com.example.jdbc.MasinaJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Lab9ex1Application implements CommandLineRunner {

	@Autowired
	private MasinaJdbcDao masinaDao;

	public static void main(String[] args) {
		SpringApplication.run(Lab9ex1Application.class, args);
	}

	@Override
	public void run(String... args) {

		Logger log = LoggerFactory.getLogger(this.getClass());
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("1. Afișare toate mașinile");
			System.out.println("2. Adăugare mașină nouă");
			System.out.println("3. Ștergere mașină");
			System.out.println("4. Căutare mașină după număr de înmatriculare");
			System.out.println("5. Listă completă de mașini");
			System.out.println("6. Număr mașini după marcă");
			System.out.println("7. Număr mașini cu kilometraj sub 100.000 km");
			System.out.println("8. Mașini fabricate în ultimii 5 ani");
			System.out.println("0. Ieșire");
			System.out.print("Selectați o opțiune: ");

			int optiune = scanner.nextInt();
			scanner.nextLine(); // curățare buffer

			switch (optiune) {
				case 0:
					scanner.close();
					System.exit(0);
					break;
				case 1:
					log.info("Lista completă a mașinilor: " + masinaDao.findAll());
					break;
				case 2:
					System.out.print("Introduceți numărul de înmatriculare: ");
					String numarInmatriculare = scanner.next();
					System.out.print("Introduceți marca: ");
					String marca = scanner.next();
					System.out.print("Introduceți anul fabricației: ");
					int anFabricatie = scanner.nextInt();
					System.out.print("Introduceți culoarea: ");
					String culoare = scanner.next();
					System.out.print("Introduceți numărul de kilometri: ");
					long kilometraj = scanner.nextLong();

					Masina masinaNoua = new Masina(numarInmatriculare, marca, anFabricatie, culoare, kilometraj);
					System.out.println("Mașină adăugată, rânduri afectate: " + masinaDao.insert(masinaNoua));
					break;
				case 3:
					System.out.print("Introduceți numărul de înmatriculare al mașinii de șters: ");
					String nrInmatriculareStergere = scanner.next();
					System.out.println("Mașină ștearsă, rânduri afectate: " + masinaDao.deleteById(nrInmatriculareStergere));
					break;
				case 4:
					System.out.print("Introduceți numărul de înmatriculare al mașinii de căutat: ");
					String nrInmatriculareCautare = scanner.next();
					System.out.println("Detalii mașină: " + masinaDao.findById(nrInmatriculareCautare));
					break;
				case 5:
					masinaDao.findAll().forEach(System.out::println);
					break;
				case 6:
					System.out.print("Introduceți marca pentru filtrare: ");
					String marcaFiltru = scanner.next();
					long nrMasini = masinaDao.findAll().stream()
							.filter(m -> m.getMarca().equalsIgnoreCase(marcaFiltru))
							.count();
					System.out.println("Număr mașini marca " + marcaFiltru + ": " + nrMasini);
					break;
				case 7:
					long masiniKilometri = masinaDao.findAll().stream()
							.filter(m -> m.getNrKm() < 100000)
							.count();
					System.out.println("Număr mașini cu kilometraj sub 100.000 km: " + masiniKilometri);
					break;
				case 8:
					System.out.println("Mașini fabricate în ultimii 5 ani:");
					masinaDao.findAll().stream()
							.filter(m -> LocalDate.now().getYear() - m.getAnulFabricatiei() <= 5)
							.forEach(System.out::println);
					break;
				default:
					System.out.println("Opțiune invalidă. Încercați din nou.");
			}
		}
	}
}