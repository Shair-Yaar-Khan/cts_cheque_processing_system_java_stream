package com.iispl.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.model.Cheque;

public class AdvancedStreamServiceImpl implements AdvancedStreamService {
	
	ChequeDao chequeDao = new ChequeDaoImpl();

	@Override
	public void displayUniqueBranchMicrValues() {
		
		List<Cheque> cheques = chequeDao.getAllCheques();
		
		List<String> uniqueBranchCodes = cheques
				.stream()
				.map(Cheque::getBranchCode)
				.distinct()
				.collect(Collectors.toList());
		
		long count = cheques
				.stream()
				.map(Cheque::getMicrCode)
				.distinct()
				.count();
		
		List<String> uniqueMicrCodes = cheques
				.stream()
				.map(Cheque::getMicrCode)
				.distinct()
				.collect(Collectors.toList());
		
		System.out.println("===== UNIQUE CTS VALUES =====");

		System.out.println("Branches: " + uniqueBranchCodes);
		System.out.println("MICR Count: " + count);
		System.out.println("MICR Codes: " + uniqueMicrCodes);
	}

	@Override
	public void displayTopFiveProcessingQueue() {
		
		List<Cheque> cheques = chequeDao.getAllCheques();
		
		List<Cheque> top5 = cheques
				.stream()
				.sorted(Comparator.comparing(Cheque::getAmount).reversed())
				.limit(5)
				.collect(Collectors.toList());
		
		System.out.println("===== TOP 5 CTS PROCESSING QUEUE =====");
		top5.forEach(cheque -> System.out.println(cheque.getChequeNumber()
				+ " | " + cheque.getBranchCode() 
				+ " | " + cheque.getAmount()));
	}

	@Override
	public void displayPaginatedCheques(int pageNumber, int pageSize) {
		
		List<Cheque> cheques = chequeDao.getAllCheques();
		
		List<String> page = cheques
				.stream()
				.map(Cheque::getChequeNumber)
				.skip((pageNumber - 1) * pageSize)
				.limit(pageSize)
				.collect(Collectors.toList());
		
		System.out.println("Page Number: " + pageNumber);
		System.out.println("Page Size: " + pageSize);
		
		System.out.println("===== CHEQUE PAGE " + pageNumber + " =====");
		
		page.forEach(System.out::println);
	}

	@Override
	public void displayRecordCount() {
		List<Cheque>cheques = chequeDao.getAllCheques();
		long count = cheques.stream()
				.count();
		System.out.println("========CTS RECORD COUNT========");
		System.out.println("Total Cheque records:" + count);
	}

	@Override
	public void displayHighestLowestCheque() {
		

	}

	@Override
	public void displayAverageChequeAmount() {
		

	}

	@Override
	public void displayChequeLookupMap() {
		

	}

	@Override
	public void displayCtsReferenceString() {
		
	}

	@Override
	public void displayCountPerBranch() {
		

	}

	@Override
	public void displayTotalAveragePerBranch() {
		

	}

	@Override
	public void displayBranchStatistics() {
		

	}

	@Override
	public void displayBranchToChequeNumbers() {
		

	}

	@Override
	public void displayFinalizedCollection() {
		

	}

	@Override
	public void displayPipelineDiagnostics() {
		

	}

	@Override
	public void displayMultiLevelComparator() {
		

	}

}
