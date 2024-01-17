package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ManagerDAO;
import futsalManager.FutsalField;
import futsalManager.Manager;
import futsalManager.Reservation;

public class ManagerAct {

	static ManagerDAO managerDAO;
	
	
	public ManagerAct(Connection connection) {
		managerDAO = new ManagerDAO(connection);
	}
	//회원 가입
	public void addManager(String id, String pw,  String phoneNum, String manager_name) {
		try {
			managerDAO.addManagerDAO(id, pw, phoneNum, manager_name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Manager loginAsManager(String id, String pw ) {
		// 로그인한 매니저 반환
		Manager loginManager = null;
		try {
			loginManager = managerDAO.loginAsManagerDAO(id, pw);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginManager;
	}

	// 예약 강제 취소 (status 변환)
	public void cancelReservation(int reservation_id) {
		try {
			managerDAO.cancelReservationDAO(reservation_id);
			System.out.println("예약 취소 완료!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 내 풋살장마다의 예약 현황
	public List<Reservation> checkReservation(int get_futsal_id) {
		List<Reservation> reserveList = new ArrayList<>();

		try {
			reserveList = managerDAO.checkReservationDAO(get_futsal_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reserveList;
	}

	public void deleteFutsalField(int get_futsal_id) {
		// 내 풋살장 관리안해!
		try {
			managerDAO.deleteFutsalFieldDAO(get_futsal_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateFutsalField(FutsalField input_futsal) {
		try {
			managerDAO.updateFutsalFieldDAO(input_futsal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addFutsalField(FutsalField input_futsal) {
		try {
			managerDAO.addFutsalFieldDAO(input_futsal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<FutsalField> showMyFutsalFields(int manager_id) {
		List<FutsalField> futsalFieldList = new ArrayList<>();

		try {
			futsalFieldList = managerDAO.showMyFutsalFieldsDAO(manager_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return futsalFieldList;
	}
}