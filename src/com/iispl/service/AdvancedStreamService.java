package com.iispl.service;

public interface AdvancedStreamService {

	void displayUniqueBranchMicrValues();

    void displayTopFiveProcessingQueue();

    void displayPaginatedCheques(int pageNumber, int pageSize);

    void displayRecordCount();

    void displayHighestLowestCheque();

    void displayAverageChequeAmount();

    void displayChequeLookupMap();

    void displayCtsReferenceString();

    void displayCountPerBranch();

    void displayTotalAveragePerBranch();

    void displayBranchStatistics();

    void displayBranchToChequeNumbers();

    void displayFinalizedCollection();

    void displayPipelineDiagnostics();

    void displayMultiLevelComparator();

}
