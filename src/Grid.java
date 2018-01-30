
public interface Grid {
	
	public int getWidth();
	public int getHeight();
	public Cell get(int x, int y);
	public void set(int x, int y, String state);
	public void insert(Cell c);
}
