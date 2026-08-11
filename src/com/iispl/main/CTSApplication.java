package com.iispl.main;

import java.util.Scanner;

import com.iispl.service.AdvancedStreamService;
import com.iispl.service.AdvancedStreamServiceImpl;

public class CTSApplication {
public static void main(String[] args) {
	
	AdvancedStreamService service = new AdvancedStreamServiceImpl();

    Scanner scanner = new Scanner(System.in);

    int choice;

    do {

        System.out.println();
        System.out.println("========== ADVANCED CTS STREAM REPORTS ==========");
        System.out.println("1.  Unique Branch/MICR Values");
        System.out.println("2.  Top 5 Processing Queue");
        System.out.println("3.  Paginated Cheques");
        System.out.println("4.  Record Count");
        System.out.println("5.  Highest/Lowest Cheque");
        System.out.println("6.  Average Cheque Amount");
        System.out.println("7.  Cheque Lookup Map");
        System.out.println("8.  CTS Reference String");
        System.out.println("9.  Count Per Branch");
        System.out.println("10. Total/Average Per Branch");
        System.out.println("11. Branch Statistics");
        System.out.println("12. Branch -> Cheque Numbers");
        System.out.println("13. Finalized Collection");
        System.out.println("14. Pipeline Diagnostics");
        System.out.println("15. Multi-Level Comparator");
        System.out.println("0.  Exit");
        System.out.println("=================================================");

        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();

        switch (choice) {

        case 1:
            service.displayUniqueBranchMicrValues();
            break;

        case 2:
            service.displayTopFiveProcessingQueue();
            break;

        case 3:
            System.out.print("Enter page number: ");
            int pageNumber = scanner.nextInt();

            System.out.print("Enter page size: ");
            int pageSize = scanner.nextInt();

            service.displayPaginatedCheques(pageNumber, pageSize);
            break;

        case 4:
            service.displayRecordCount();
            break;

        case 5:
            service.displayHighestLowestCheque();
            break;

        case 6:
            service.displayAverageChequeAmount();
            break;

        case 7:
            service.displayChequeLookupMap();
            break;

        case 8:
            service.displayCtsReferenceString();
            break;

        case 9:
            service.displayCountPerBranch();
            break;

        case 10:
            service.displayTotalAveragePerBranch();
            break;

        case 11:
            service.displayBranchStatistics();
            break;

        case 12:
            service.displayBranchToChequeNumbers();
            break;

        case 13:
            service.displayFinalizedCollection();
            break;

        case 14:
            service.displayPipelineDiagnostics();
            break;

        case 15:
            service.displayMultiLevelComparator();
            break;

        case 0:
            System.out.println("Exiting Advanced CTS Stream Reports...");
            break;

        default:
            System.out.println("Invalid choice. Please enter 0-15.");

        }

    } while (choice != 0);

    scanner.close();
	
}
}
