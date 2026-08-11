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
		List<Cheque>cheques = chequeDao.getAllCheques();
		Cheque highest = cheques.stream()
		        .max(Comparator.comparing(Cheque::getAmount))
		        .get();
		Cheque lowest = cheques.stream()
	            .min(Comparator.comparing(Cheque::getAmount))
	            .get();
		System.out.println("Highest : " + highest.getChequeNumber() + " | " + highest.getAmount());

	    System.out.println("Lowest : " + lowest.getChequeNumber() + " | " + lowest.getAmount());
	}

	@Override
	public void displayAverageChequeAmount() {
		List<Cheque> cheques = chequeDao.getAllCheques();

	    OptionalDouble average = cheques.stream()
	            .mapToDouble(Cheque::getAmount)
	            .average();

	    System.out.println("===== AVERAGE CHEQUE AMOUNT =====");

	    if (average.isPresent()) {
	        System.out.printf("Average Amount : %.2f%n",
	                average.getAsDouble());
	    } else {
	        System.out.println("Average Amount : No records available");
	    }
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
