package com.iispl.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
		
		List<Cheque> cheques = chequeDao.getAllCheques();
		
		Map<String,Double> totalPerBranch = cheques.stream().collect(Collectors.groupingBy(Cheque::getBranchCode,TreeMap::new,
				Collectors.summingDouble(x->x.getAmount())));
		
		
		Map<String,Double> averagePerBranch = cheques.stream().collect(Collectors.groupingBy(Cheque::getBranchCode,TreeMap::new,
				Collectors.averagingDouble(x->x.getAmount())));
		
		System.out.println("=============== BRANCH AMOUNT SUMMARY ==============");
		
		for(String branch : totalPerBranch.keySet()) {
			
			 System.out.printf("%s | Total: %10.2f | Average: %.2f%n",branch,totalPerBranch.get(branch),averagePerBranch.get(branch));
		}

	}

	@Override
	public void displayBranchStatistics() {
		 List<Cheque> cheques = chequeDao.getAllCheques();

		    Map<String, DoubleSummaryStatistics> statistics =
		            cheques.stream()
		                    .collect(Collectors.groupingBy(Cheque::getBranchCode,TreeMap::new,
		                            Collectors.summarizingDouble(cheque -> cheque.getAmount())));

		    System.out.println("===== BRANCH STATISTICS =====");

		    for (Map.Entry<String, DoubleSummaryStatistics> entry : statistics.entrySet()) {

		        DoubleSummaryStatistics stats = entry.getValue();

		        System.out.printf(
		                "%s -> Count=%d, Sum=%.2f, Avg=%.2f, Min=%.2f, Max=%.2f%n",
		                entry.getKey(),
		                stats.getCount(),
		                stats.getSum(),
		                stats.getAverage(),
		                stats.getMin(),
		                stats.getMax()
		        );
		    }

	}

	@Override
	public void displayBranchToChequeNumbers() {
		
		  List<Cheque> cheques = chequeDao.getAllCheques();

		    Map<String, List<String>> branchToChequeNumbers =
		            cheques.stream()
		                    .collect(Collectors.groupingBy(
		                            Cheque::getBranchCode,
		                            TreeMap::new,
		                            Collectors.mapping(
		                                    Cheque::getChequeNumber,
		                                    Collectors.toList()
		                            )
		                    ));

		    System.out.println("===== BRANCH -> CHEQUE NUMBERS =====");

		    for (Map.Entry<String, List<String>> entry
		            : branchToChequeNumbers.entrySet()) {

		        System.out.println(
		                entry.getKey() + " -> " + entry.getValue()
		        );
		    }

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
