package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import managerModel.FutsalField;
import managerModel.Manager;
import managerModel.Reservation;

public class ManagerDAO {
	public Connection connection;
	
	public ManagerDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	public Manager loginAsManagerDAO(String id, String pw) throws SQLException {
		Manager manager = null;
		PreparedStatement statement = connection
				.prepareStatement("SELECT * from Manager WHERE login_id =? && login_pw=?;");
		statement.setString(1, id);
		statement.setString(2, pw);
		ResultSet resultSet = statement.executeQuery();
		try (statement; resultSet;) {
			// resultSet.next() -> 값이 들어오면, true
			resultSet.next(); // resultSet을 가지고 원하는 값들을 넣어줘서 보냄
			int member_id = resultSet.getInt("manager_id");
			String login_id = resultSet.getString("login_id");
			String login_pw = resultSet.getString("login_pw");
			String phoneNum = resultSet.getString("phoneNum");
			String name = resultSet.getString("manager_name");

			manager = new Manager(member_id, login_id, login_pw, phoneNum, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return manager;
	}

	public void cancelReservationDAO(int reservation_id) throws SQLException {
		// 해당 id를 가진 예약을 강제 취소시키는 메서드
		PreparedStatement statement = connection
				.prepareStatement("UPDATE Reservation SET status = 1 WHERE reservation_id= ?;");
		statement.setInt(1, reservation_id);
		statement.executeUpdate();
		
	}

	public List<Reservation> checkReservationDAO(int get_futsal_id) throws SQLException {
		// 내 풋살장마다의 예약 현황
		List<Reservation> reserveList = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reservation WHERE futsal_id = ?;");
		statement.setInt(1, get_futsal_id);
		ResultSet resultSet = statement.executeQuery();
		try (statement;) {

			while (resultSet.next()) {
				// resultSet을 가지고 원하는 값들을 넣어줘서 보냄
				int reservation_id = resultSet.getInt("reservation_id");
				int member_id = resultSet.getInt("member_id");
				int futsal_id = resultSet.getInt("futsal_id");
				int status = resultSet.getInt("status");
				LocalDate reserve_date = (resultSet.getDate("reservation_date")).toLocalDate();
				String reserve_time = resultSet.getString("reservation_time");

				reserveList
						.add(new Reservation(reservation_id, member_id, futsal_id, status, reserve_date, reserve_time));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return reserveList;

		// TODO Auto-generated method stub

	}

	public void deleteFutsalFieldDAO(int get_futsal_id) throws SQLException {

		PreparedStatement statement = connection.prepareStatement("DELETE FROM Futsal WHERE futsal_id = ?;");
		statement.setInt(1, get_futsal_id);
		statement.executeUpdate();
		try (statement;) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	// 내가 관리하고 있는 풋살장의 이름, 수용 인원 등 변경
	public void updateFutsalFieldDAO(FutsalField futsal_field) throws SQLException {
		int futsal_id = futsal_field.getFutsal_id();
		String futsal_name = futsal_field.getFutsal_name();
		int capacity = futsal_field.getCapacity();

		PreparedStatement statement = connection
				.prepareStatement("UPDATE Futsal SET capacity = ?, futsal_name =? WHERE futsal_id = ?;");
		try (statement;) {
			statement.setInt(1, capacity);
			statement.setString(2, futsal_name);
			statement.setInt(3, futsal_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void addFutsalFieldDAO(FutsalField futsal_field) throws SQLException {

		int manager_id = futsal_field.getManager_id();
		int capacity = futsal_field.getCapacity();
		String futsal_name = futsal_field.getFutsal_name();
		
		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO Futsal (manager_id, capacity, futsal_name) " + "Values(?,?,?);");
		statement.setInt(1, manager_id);
		statement.setInt(2, capacity);
		statement.setString(3, futsal_name);

		statement.executeUpdate();
		
		
	}

	public List<FutsalField> showMyFutsalFieldsDAO(int manager_id) throws SQLException {
		List<FutsalField> futsalFieldList = new ArrayList<>();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Futsal WHERE manager_id = ?;");
		statement.setInt(1, manager_id);
		ResultSet resultSet = statement.executeQuery();
		try (statement; resultSet;) {
			while (resultSet.next()) {

				int capacity = resultSet.getInt("capacity");
				String futsal_name = resultSet.getString("futsal_name");
				int futsal_id = resultSet.getInt("futsal_id");
				futsalFieldList.add(new FutsalField(futsal_id,manager_id, capacity, futsal_name)); //여기에 futsal_id를 넣어주지 않으면
				// 생성자가 호출될 때 마다 futsal_id가 0으로 초기화 됨. 그래서 반환한 테이블에서 futsal_id를 뽑아서 생성자에 넣어 줌.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return futsalFieldList;
	}

	public void addManagerDAO(String id, String pw, String phoneNum, String manager_name) throws SQLException {

		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO manager (login_id, login_pw, phoneNum, manager_name) " + "Values(?,?,?,?);");
		statement.setString(1, id);
		statement.setString(2, pw);
		statement.setString(3, phoneNum);
		statement.setString(4, manager_name);
		statement.executeUpdate();
	}

}
