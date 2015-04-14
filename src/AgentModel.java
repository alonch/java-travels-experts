import java.util.List;


public interface AgentModel {
	public void add(Agent agent);
	public void save(Agent agent);
	public Agent get(int id);
	public List<Agent> get();
	public void delete(int id);
	//public void reset();
}
