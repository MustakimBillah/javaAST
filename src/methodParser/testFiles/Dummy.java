package methodParser.testFiles;

public class Dummy {

	private Test test;
	private int id;
	
	
	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public static void main(String[] args) {
		Dummy d1 =new Dummy();
		d1.setId(1);
		d1.setTest(new Test(5,"dummy test"));
		System.out.println(d1.getTest().getName());
	}
}
