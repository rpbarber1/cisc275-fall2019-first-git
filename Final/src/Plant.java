  
import java.io.Serializable;

/**
 * This class contains information on the plants used during the barrier game
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class Plant implements Serializable{
	private String name;
	private boolean invasive;
	
	public Plant(String name, boolean invasive) {
		this.name=name;
		this.invasive=invasive;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getInvasive() {
		return invasive;
	}
	
}