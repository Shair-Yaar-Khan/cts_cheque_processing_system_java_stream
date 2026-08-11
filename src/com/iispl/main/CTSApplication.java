package com.iispl.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.model.Cheque;
import com.iispl.service.AdvancedStreamServiceImpl;

public class CTSApplication {
public static void main(String[] args) {
	
	new AdvancedStreamServiceImpl().displayTotalAveragePerBranch();
	
}
}
