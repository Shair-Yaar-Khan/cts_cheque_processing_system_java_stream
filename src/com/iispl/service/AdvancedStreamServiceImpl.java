package com.iispl.service;

import java.util.Collections;
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
		

	}

	@Override
	public void displayTopFiveProcessingQueue() {
		

	}

	@Override
	public void displayPaginatedCheques(int pageNumber, int pageSize) {
		

	}

	@Override
	public void displayRecordCount() {
		
		

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
		List<Cheque> cheques=chequeDao.getAllCheques();
		List<Cheque> finalizedResult=cheques.stream()
				.collect(Collectors
						.collectingAndThen(Collectors.toList(),Collections::unmodifiableList));
		 System.out.println("===== FINALIZED CTS RESULT =====");
		    System.out.println("Records Collected : " + finalizedResult.size());

		    try {
		        finalizedResult.add(cheques.get(0));
		    } catch (UnsupportedOperationException e) {
		        System.out.println("Modification Test : UnsupportedOperationException");
		        System.out.println("Result : Collection remains unchanged");
		    }

	}

	@Override
	public void displayPipelineDiagnostics() {
		
		List<Cheque> cheques = chequeDao.getAllCheques();

	    System.out.println("===== STREAM TRACE =====");

	    List<Cheque> result = cheques.stream()
	    		.peek(c -> System.out.println(
	                    "TRACE -> " +
	                    c.getChequeNumber() +
	                    " entered pipeline"))
	    		.filter(c -> c.getAmount() >= 50000)
	            .peek(c -> System.out.println("Passed: "+c.getChequeNumber()))
	            .toList();

	    System.out.println("Final result produced successfully.");
		

	}

	@Override
	public void displayMultiLevelComparator() {
		
		 List<Cheque> cheques = chequeDao.getAllCheques();

		    Comparator<Cheque> ctsComparator =
		            Comparator.comparing(Cheque::getBranchCode)
		                    .thenComparing(
		                            Comparator.comparingDouble(
		                                    Cheque::getAmount
		                            ).reversed()
		                    )
		                    .thenComparing(
		                            Cheque::getChequeNumber
		                    );

		    List<Cheque> sortedCheques = cheques.stream()
		            .sorted(ctsComparator)
		            .toList();

		    System.out.println("===== MULTI-LEVEL ORDER =====");
		    
		    sortedCheques.forEach(c ->
		            System.out.printf(
		                    "%s | %s | %.2f%n",
		                    c.getBranchCode(),
		                    c.getChequeNumber(),
		                    c.getAmount()
		            ));
		

	}

}
