import java.util.*;

public class Student {
	int numCourses;
	ArrayList<Integer> PrereqOfCourses[];
	HashMap<String, Integer> coursesByName;
	HashMap<Integer, String> coursesByIdx;
	int[] incomingEdges;

	public Student(String[] coursesNeeded) {
		this.numCourses = coursesNeeded.length;
		PrereqOfCourses = new ArrayList[numCourses];
		coursesByName = new HashMap<String, Integer>();
		coursesByIdx = new HashMap<Integer, String>();
		incomingEdges = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			PrereqOfCourses[i] = new ArrayList<Integer>();
			coursesByName.put(coursesNeeded[i], i);
			coursesByIdx.put(i, coursesNeeded[i]);

		}

	}

	public void addCoursePrerequisite(String courseName, String Prerequisite) {
		int idxCourse = coursesByName.get(courseName);
		int idxPrereq = coursesByName.get(Prerequisite);
		PrereqOfCourses[idxPrereq].add(idxCourse);
		incomingEdges[idxCourse]++;
	}

	public void TopologicalSortCourses() {
		Queue<Integer> validCoursesSoFar = new LinkedList<Integer>();
		String order = "";
		int count = 0;
		for (int i = 0; i < numCourses; i++) {
			if (incomingEdges[i] == 0) {
				validCoursesSoFar.add(i);

			}
		}
		while (!validCoursesSoFar.isEmpty()) {
			int course = validCoursesSoFar.poll();
			count++;
			order += coursesByIdx.get(course) + " \n";
			for (int x : PrereqOfCourses[course]) {
				if (--incomingEdges[x] == 0)
					validCoursesSoFar.add(x);

			}
		}
		if (count != numCourses)
			System.out.println("No way to take all courses! Graph must cycle!");
		else
			System.out.println(order);

	}

	public static void main(String[] args) {
		// testing code on sample input
		String myCourses[] = { "CS101", "DSA", "OOP", "Calc 1", "Calc 2", "GameDev" };
		Student Farida = new Student(myCourses);
		Farida.addCoursePrerequisite("DSA", "CS101");
		Farida.addCoursePrerequisite("DSA", "OOP");
		Farida.addCoursePrerequisite("GameDev", "CS101");
		Farida.addCoursePrerequisite("GameDev", "DSA");
		Farida.addCoursePrerequisite("GameDev", "OOP");
		Farida.addCoursePrerequisite("OOP", "CS101");
		Farida.addCoursePrerequisite("Calc 2", "Calc 1");

		Farida.TopologicalSortCourses();

	}

}
