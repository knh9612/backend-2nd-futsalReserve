package managerModel;

import java.io.Console;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
	
	public String toString() {
		return "" +futsal_name + "(id: " + futsal_id + ") 풋살장은 " + capacity + "명 수용 가능합니다.";
	}
}