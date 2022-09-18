package methodParser.customParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class AstParser {

	
	public static void parse(String str) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setBindingsRecovery(true);

		String unitName = "Test";
		parser.setUnitName(unitName);

		String[] sources = { "D:\\eclipse-workspace\\methodParser" };
		String[] classpath = { "D:\\eclipse-workspace\\methodParser\\bin" };

		parser.setEnvironment(classpath, sources, new String[] { "UTF-8" }, true);
		parser.setSource(str.toCharArray());

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		cu.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation node) {

				ITypeBinding typeBinding = node.getExpression().resolveTypeBinding();
				String result = typeBinding.toString();
				int indx = result.indexOf("/");
				result = result.substring(0, indx);

				if (node.resolveMethodBinding() != null) {
					IMethodBinding declarationOfInvokedMethod = node.resolveMethodBinding().getMethodDeclaration();

					System.out.println("# Line Number: " + cu.getLineNumber(node.getStartPosition()));
					System.out.println();
					System.out.println("<------- Method Called ------->");
					System.out.println(node);
					System.out.println();
					System.out.println("<------- Method definition ------->");
					System.out.println(declarationOfInvokedMethod);
					System.out.println();
					System.out.println("<------- Type/Class ------->");
					System.out.println(result);
					System.out.println();
					

				}
				return super.visit(node);

			}

		});

	}

	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();

		return fileData.toString();
	}


	public static void ParseFilesInDir() throws IOException {
		File dirs = new File(".");
		String dirPath = dirs.getCanonicalPath() + File.separator + "src" + File.separator + "methodParser"
				+ File.separator + "testFiles" + File.separator + "";

		File root = new File(dirPath);
		File[] files = root.listFiles();
		String filePath = null;

		for (File f : files) {
			filePath = f.getAbsolutePath();
			if (f.isFile()) {
				System.out.println("<<<<<<<<<------------------------------------------->>>>>>>>");
				System.out.println("File Name: "+f.getName());
				System.out.println("<<<<<<<<<------------------------------------------->>>>>>>>");
				System.out.println();
				parse(readFileToString(filePath));
				System.out.println("<<<<<<<<<------------------------------------------->>>>>>>>");
				System.out.println("<<<<<<<<<------------------------------------------->>>>>>>>");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		ParseFilesInDir();
	}
}
