package com.iispl.dao;

import java.util.List;

import com.iispl.enums.MicrStatus;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;

public interface ChequeDao {

	List<Cheque> getAllCheques();
	List<Cheque> getChequesByBatch(int batchId);
	Cheque getChequeByNumber(String chequeNumber);
	void updateMicrStatus(String chequeNumber, MicrStatus status);
	void updateValidationStatus(String chequeNumber, ValidationStatus status);
}
