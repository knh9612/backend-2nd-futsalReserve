package futsalManager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

//@RequiredArgsConstructor
public class FutsalField {
	
	int futsal_id;
	int manager_id;
	int capacity;
	String futsal_name;
	
	public FutsalField(int manager_id, int capacity, String futsal_name){
		
		this.manager_id = manager_id;
		this.capacity = capacity;
		this.futsal_name = futsal_name;
	}
}