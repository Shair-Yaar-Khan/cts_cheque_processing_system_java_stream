package com.iispl.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;

public class AdvancedStreamServiceImpl implements AdvancedStreamService {
	
	ChequeDao chequeDao = new ChequeDaoImpl();

	@Override
	public void displayUniqueBranchMicrValues() {
		

	}

	@Override
	public void displayTopFiveProcessingQueue() {
		

	}

	@Override
	public void displayPaginatedCheques(int pageNumber, int pageSize) {
		

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
		
		Scanner scanner=new Scanner(System.in);
		List<Cheque> chequeList=chequeDao.getAllCheques();
		System.out.println("Enter Cheque Number");
		String chequeNumber=scanner.next();
		Map<String,Cheque> chequeMap=chequeList.stream().filter(cheque->cheque.getChequeNumber().equals(chequeNumber))
			.collect(Collectors.toMap(cheque->cheque.getChequeNumber(),cheque->cheque));
		
		System.out.println("===== CHEQUE LOOKUP =====");
		Cheque cheque = chequeMap.get(chequeNumber);
		System.out.println("Key : " + cheque.getChequeNumber());
		System.out.println("Customer : " + cheque.getCustomerName());
		System.out.println("Amount : " + cheque.getAmount());
		System.out.println("Branch : " + cheque.getBranchCode());

	}

	@Override
	public void displayCtsReferenceString() {
		
		List<Cheque> chequeList=chequeDao.getAllCheques();
		String approvedChequeNumbers = chequeList.stream()
				.filter(cheque -> cheque.getValidationStatus()
		                .compareTo(ValidationStatus.APPROVED) == 0)
		        .map(cheque -> cheque.getChequeNumber())
		        .collect(Collectors.joining(", "));

		System.out.println("===== APPROVED CTS REFERENCES =====");
		System.out.println(approvedChequeNumbers);
	}

	@Override
	public void displayCountPerBranch() {
		
		List<Cheque> chequeList=chequeDao.getAllCheques();
		Map<String, Long> branchCount = chequeList.stream()
		        .collect(Collectors.groupingBy(
		                cheque -> cheque.getBranchCode(),
		                Collectors.counting()
		        ));

		System.out.println("===== CHEQUE COUNT BY BRANCH =====");
		branchCount.forEach((branch, count) ->
		        System.out.println(branch + " -> " + count)
		);

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
