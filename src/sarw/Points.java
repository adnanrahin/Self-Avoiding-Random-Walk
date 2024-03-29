package sarw;

public class Points {

	int x, y;

	public Points(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return Integer.toString(x) + "," + Integer.toString(y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Points other = (Points) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}