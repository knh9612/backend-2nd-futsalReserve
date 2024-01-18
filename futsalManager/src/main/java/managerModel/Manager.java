package managerModel;

import futsal_maanger_user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Manager implements User{
	int member_id;
	String login_id;
	String login_pw;
	String phoneNum;
	String name;
	

}
