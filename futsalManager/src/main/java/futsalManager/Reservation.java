package futsalManager;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	int reservation_id;
	int member_id;
	int futsal_id;
	int status;
	LocalDate reservation_date;
	String reservateion_time;//18:00

}
