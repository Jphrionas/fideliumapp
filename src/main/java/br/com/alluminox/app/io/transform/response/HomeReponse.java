package br.com.alluminox.app.io.transform.response;

import java.io.Serializable;
import java.util.List;

import br.com.alluminox.app.data.model.Reward;
import br.com.alluminox.app.data.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HomeReponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int totalOfAdmins = 0;
	private int totalOfUsers = 0;
	private int totalOfPoints = 0;
	private int usersActives = 0;
	
	private List<Reward> rewardOfUser;
	private List<User> usersDisabled;
}
