package selfavoidingwalk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	final static int N_SAW = 1000000;
	static int directions2D[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int directions3D[][] = { { 0, -1, 1 }, { 0, 1, -1 }, { -1, 0, 1 }, { -1, 1, 0 }, { 1, -1, 0 },
			{ 1, 0, -1 } };

	static Random random = new Random();

	public static void main(String[] args) {

		ThreeDimensions twoD_ = new ThreeDimensions();

		Thread t1 = new Thread(twoD_);

		t1.start();
	}

	public static class ThreeDimensions extends Thread {

		int steps;

		public ThreeDimensions(int steps) {
			this.steps = steps;
		}

		public ThreeDimensions() {
			// Default Constructor
		}

		public static double slefAvoidingRandomWlak3D(int steps) {

			List<tuple> tupleList = new ArrayList<tuple>();

			Hashtable<Integer, List<tuple>> tupleTable3d = new Hashtable<Integer, List<tuple>>();

			for (int i = 0; i < N_SAW; i++) {

				tupleTable3d.put(i, new ArrayList<tuple>());

				Set<String> visited = new HashSet<String>();

				int x = 0;
				int y = 0;
				int z = 0;

				visited.add(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z));

				for (int j = 1; j <= steps; j++) {

					int r = ThreadLocalRandom.current().nextInt(6);

					x = x + directions3D[r][0];
					y = y + directions3D[r][1];
					z = z + directions3D[r][2];

					String coordinate = Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z);

					if (!visited.add(coordinate))
						break;

					if (visited.size() == steps)
						tupleList.add(new tuple(x, y, z));

					tupleTable3d.get(i).add(new tuple(x, y, z));

				}

			}

			double squaredDistance = 0.0;

			for (int i = 0; i < tupleList.size(); i++) {
				squaredDistance += Math.pow(tupleList.get(i).x, 2) + Math.pow(tupleList.get(i).y, 2)
						+ Math.pow(tupleList.get(i).z, 2);
			}

			double avg = (double) (squaredDistance / N_SAW);

			return avg;

		}

		public void run() {
			for (int i = 1; i <= 40; i++) {
				System.out.println(slefAvoidingRandomWlak3D(i));
			}
		}

	}

	public static class TwoDimensions extends Thread {

		int steps;

		public TwoDimensions() {
			// Default Constructor
		}

		public TwoDimensions(int steps) {
			this.steps = steps;
		}

		public static double slefAvoidingRandomWlak2D(int steps) {

			List<point> pointsList = new ArrayList<point>();

			Hashtable<Integer, List<point>> pointTable2d = new Hashtable<Integer, List<point>>();

			for (int i = 0; i < N_SAW; i++) {

				pointTable2d.put(i, new ArrayList<point>());

				Set<String> visited = new HashSet<String>();

				int x = 0;
				int y = 0;

				visited.add(Integer.toString(x) + "," + Integer.toString(y));

				for (int j = 1; j <= steps; j++) {

					int r = ThreadLocalRandom.current().nextInt(4);

					x = x + directions2D[r][0];
					y = y + directions2D[r][1];

					String coordinate = Integer.toString(x) + "," + Integer.toString(y);

					if (!visited.add(coordinate))
						break;

					if (visited.size() == steps)
						pointsList.add(new point(x, y));

					pointTable2d.get(i).add(new point(x, y)); // keep track of all the path with n steps

				}
			}

			double squaredDistance = 0.0;

			for (int i = 0; i < pointsList.size(); i++) {
				squaredDistance += Math.pow(pointsList.get(i).x, 2) + Math.pow(pointsList.get(i).y, 2);
			}

			double avg = (double) (squaredDistance / N_SAW);

			return avg;

		}

		public void run() {

			for (int i = 1; i <= 40; i++) {
				System.out.println(slefAvoidingRandomWlak2D(i));
			}

		}

	}

	public static class point {

		int x;
		int y;

		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return Integer.toString(x) + "," + Integer.toString(y);
		}

	}

	public static class tuple {

		int x;
		int y;
		int z;

		public tuple(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public tuple() {
			// Default Constructor
		}

		public String toString() {
			return Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z);
		}
	}

}
