package com.iispl.service;

import java.util.List;

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
