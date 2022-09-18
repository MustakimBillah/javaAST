package methodParser.testFiles;

import java.io.IOException;

public class Test {
	private int id;
	private String name;

	
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setId(double id) {
		this.id = (int) id;
	}
	
	public void setId(int id,int offset) {
		this.id = id-offset;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void printTest(Test t) {
		System.out.println("id: "+t.getId()+" "+"name: "+t.getName());
	}
	
	public static void main(String[] args) throws IOException {
		Test t1 = new Test();
		t1.setId(10.2);
		t1.setName("make");
		//t1.printTest(t1);
		Test t2 = new Test();
		t2.setId(10, 5);
	}
}
