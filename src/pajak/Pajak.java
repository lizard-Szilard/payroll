package pajak;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pajak {

	public enum jabatan {
		GENERALMANAGER, MANAGER, ASSISTANTMANAGER,
		SENIORMANAGER, JUNIORMANAGER, TEKNISI, DRIVER
	}

	public double hitungBiayaJabatan(jabatan namaJabatan, String tanggalPengangkatan, String tanggalPerhitungan) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");

		LocalDate localDatePengangkatan = LocalDate.parse(tanggalPengangkatan, formatter);
		LocalDate localDatePerhitungan = LocalDate.parse(tanggalPerhitungan, formatter);

		int hasilPerbandinganTanggal = localDatePerhitungan.compareTo(localDatePengangkatan);

		int tahunPengangkatan = 0;
		int tahunPerhitungan = 0;
		int bulanPengangkatan = 0;
		int bulanPerhitungan = 0;

		if (hasilPerbandinganTanggal > 0) {
			tahunPengangkatan = localDatePengangkatan.getYear();
			tahunPerhitungan = localDatePerhitungan.getYear();
			bulanPengangkatan = localDatePengangkatan.getMonthValue();
			bulanPerhitungan = localDatePerhitungan.getMonthValue();
		} else {
			System.out.println("penghitungan tanggal error");
		}

		double gajiPokok;
		double gajiPokokPerTahun;
		double tunjanganJabatan;
		double tunjanganJabatanPerTahun;
		double JKK;
		double JKM;
		double JHT;
		double BPJSKesehatan;
		double totalPenghasilanBruto;

		if (namaJabatan == jabatan.GENERALMANAGER) {
			gajiPokok = 9000000;
			tunjanganJabatan = 5000000;
		} else if (namaJabatan == jabatan.MANAGER) {
			gajiPokok = 8000000;
			tunjanganJabatan = 4000000;
		} else if (namaJabatan == jabatan.ASSISTANTMANAGER) {
			gajiPokok = 6000000;
			tunjanganJabatan = 3000000;
		} else if (namaJabatan == jabatan.SENIORMANAGER) {
			gajiPokok = 3500000;
			tunjanganJabatan = 2000000;
		} else if (namaJabatan == jabatan.JUNIORMANAGER) {
			gajiPokok = 3000000;
			tunjanganJabatan = 1500000;
		} else if (namaJabatan == jabatan.TEKNISI) {
			gajiPokok = 2000000;
			tunjanganJabatan = 1000000;
		} else if (namaJabatan == jabatan.DRIVER) {
			gajiPokok = 1500000;
			tunjanganJabatan = 750000;
		} else {
			gajiPokok = 0;
			tunjanganJabatan = 0;
		}

		gajiPokokPerTahun = 12 * gajiPokok;
		tunjanganJabatanPerTahun = 12 * tunjanganJabatan;

		JKK = (double) ((0.24 / 100) * gajiPokokPerTahun);
		JKM = (double) ((0.30 / 100) * gajiPokokPerTahun);
		JHT = (double) ((3.7 / 100) * gajiPokokPerTahun);
		BPJSKesehatan = (double) ((4 / 100) * gajiPokokPerTahun);
		totalPenghasilanBruto = (double) (gajiPokokPerTahun + tunjanganJabatanPerTahun + JKK + JKM + JHT + BPJSKesehatan);

		double biayaJabatanPerTahun;
		double biayaJabatan;

		int jumlahTahun;
		int jumlahBulan;

		jumlahTahun = tahunPerhitungan - tahunPengangkatan;
		jumlahBulan = bulanPerhitungan - bulanPengangkatan;

		biayaJabatanPerTahun = (double) (0.05 * totalPenghasilanBruto);

		if (biayaJabatanPerTahun >= 6000000) {
			biayaJabatanPerTahun = 6000000;
		}

		if (tahunPerhitungan > tahunPengangkatan) {
			biayaJabatan = (double) (((jumlahTahun - 1) + (jumlahBulan / 12)) * biayaJabatanPerTahun);
		} else if (tahunPerhitungan == tahunPengangkatan) {
			biayaJabatan = (double) ((jumlahBulan / 12) * biayaJabatanPerTahun);
		} else {
			biayaJabatan = 0;
		}

		return biayaJabatan;
	}
}
