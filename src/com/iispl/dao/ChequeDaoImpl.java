package com.iispl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iispl.enums.AccountStatus;
import com.iispl.enums.ChequeType;
import com.iispl.enums.MicrStatus;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;
import com.iispl.util.DBUtil;

public class ChequeDaoImpl implements ChequeDao {
	
	@Override
	public List<Cheque> getAllCheques() {
		
		List<Cheque> cheques = new ArrayList<Cheque>();
		
		String sql = "SELECT * FROM CTS_CHEQUE;";
		
		try (Connection connection = DBUtil.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
      	
				cheques.add(new Cheque(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getDouble(7),rs.getDouble(8),rs.getDate(9).toLocalDate(),
						AccountStatus.valueOf(rs.getString(10)),ChequeType.valueOf(rs.getString(11)),
						MicrStatus.valueOf(rs.getString(12)),ValidationStatus.valueOf(rs.getString(13)),rs.getInt(14)));
	}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return cheques;
	}

	@Override
	public List<Cheque> getChequesByBatch(int batchId) {
		
        List<Cheque> cheques = new ArrayList<Cheque>();
		
		String sql = "SELECT * FROM CTS_CHEQUE WHERE batch_id = ?;";
		
		try (Connection connection = DBUtil.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setInt(1, batchId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
      	
				cheques.add(new Cheque(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getDouble(7),rs.getDouble(8),rs.getDate(9).toLocalDate(),
						AccountStatus.valueOf(rs.getString(10)),ChequeType.valueOf(rs.getString(11)),
						MicrStatus.valueOf(rs.getString(12)),ValidationStatus.valueOf(rs.getString(13)),rs.getInt(14)));
	}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return cheques;
		
	}

	@Override
	public Cheque getChequeByNumber(String chequeNumber) {
			
			String sql = "SELECT * FROM CTS_CHEQUE WHERE cheque_number = ?;";
			Cheque cheque=null;
			try (Connection connection = DBUtil.getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				
				preparedStatement.setString(1, chequeNumber);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				if (rs.next()) {
	      	
			 cheque = new Cheque(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
							rs.getString(6),rs.getDouble(7),rs.getDouble(8),rs.getDate(9).toLocalDate(),
							AccountStatus.valueOf(rs.getString(10)),ChequeType.valueOf(rs.getString(11)),
							MicrStatus.valueOf(rs.getString(12)),ValidationStatus.valueOf(rs.getString(13)),rs.getInt(14));
		}
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			return cheque;
	}

	@Override
	public void updateMicrStatus(String chequeNumber, MicrStatus status) {
		
		String sql = "UPDATE CTS_CHEQUE SET micr_status = ? WHERE cheque_number = ?;";
		
       try (Connection connection = DBUtil.getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				
				preparedStatement.setString(1, status.toString());
				preparedStatement.setString(2, chequeNumber);
				
				int rs = preparedStatement.executeUpdate();
				
				if(rs>0) {
					System.out.println(rs+" Micr Status updated");
		}
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}		
	}

	@Override
	public void updateValidationStatus(String chequeNumber, ValidationStatus status) {
		String sql = "- 6. Update validation status UPDATE CTS_CHEQUE SET validation_status = ? WHERE cheque_number = ?;";
		
	       try (Connection connection = DBUtil.getConnection();
					
					PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					
					preparedStatement.setString(1, status.toString());
					preparedStatement.setString(2, chequeNumber);
					
					int rs = preparedStatement.executeUpdate();
					
					if(rs>0) {
						System.out.println(rs+" validation Status updated");
			}
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
	}

}
