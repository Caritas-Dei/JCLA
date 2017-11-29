package jcla.test;

import jcla.ClassDefinition;
import jcla.ClassPool;
import jcla.classfile.ClassFile;
import jcla.classfile.util.ClassFilePool;
import jcla.compiler.token.LexicalAnalyzer;
import jcla.compiler.token.Token;
import jcla.io.ClassInputStream;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author link
 */
public class Test {

	static {
		// init pools for the benchmark
		ClassFilePool.getLocal();
		ClassPool.getLocal();
	}

	public static void main(String... args) throws IOException {
		String input =
				"/**\n" +
				" * @author link\n" +
				" */\n" +
				"public class Test {\n" +
				"\n" +
				"\tstatic {\n" +
				"\t\t// init pools for the benchmark\n" +
				"\t\tClassFilePool.getLocal();\n" +
				"\t\tClassPool.getLocal();\n" +
				"\t}\n" +
				"\n" +
				"\tpublic static void main(String... args) throws IOException {\n" +
				"\t\tString input = \"\\\"\\\\u0027\\\\u0027\\\"\";\n" +
				"\n" +
				"\t\twarmup(input);\n" +
				"\t\tLexicalAnalyzer analyzer = new LexicalAnalyzer();\n" +
				"\t\tlong            start, end, delta, avg = 0;\n" +
				"\t\tstart = System.nanoTime();\n" +
				"\t\tList<Token> result = analyzer.analyze(input);\n" +
				"\t\tend = System.nanoTime();\n" +
				"\t\tdelta = end - start;\n" +
				"\t\tavg = delta;\n" +
				"\n" +
				"\t\tfor (int i = 0; i < 50_000; i++) {\n" +
				"\t\t\tstart = System.nanoTime();\n" +
				"\t\t\tanalyzer.analyze(input);\n" +
				"\t\t\tend = System.nanoTime();\n" +
				"\t\t\tdelta = end - start;\n" +
				"\t\t\tavg = (avg + delta) / 2;\n" +
				"\t\t}\n" +
				"\n" +
				"\t\tprintf(result);\n" +
				"\t\tprintf(\"Time (ms, ns): \" + avg / 1_000_000 + \", \" + avg);\n" +
				"\t}\n" +
				"\n" +
				"\tprivate static void warmup(String input) {\n" +
				"\t\tLexicalAnalyzer analyzer = new LexicalAnalyzer();\n" +
				"\t\tfor (int i = 0; i < 10_000; i++) {\n" +
				"\t\t\tList<Token> result = analyzer.analyze(input);\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\tprivate static void testClassDefinition() throws IOException {\n" +
				"\t\tPath classFile = Paths.get(\"C:\", \"Users\", \"Root-01\", \"Documents\", \"javap\", \"SourceParser.class\");\n" +
				"\t\tif (classFile.toFile().isFile()) {\n" +
				"\t\t\tlong start0, start1, lapse0, lapse1;\n" +
				"\n" +
				"\t\t\tstart0 = System.nanoTime();\n" +
				"\t\t\tClassFile file = new ClassFile(new ClassInputStream(classFile));\n" +
				"\t\t\tlapse0 = System.nanoTime() - start0;\n" +
				"\n" +
				"\t\t\tstart1 = System.nanoTime();\n" +
				"\t\t\tClassDefinition classDefinition = new ClassDefinition(file);\n" +
				"\t\t\tlapse1 = System.nanoTime() - start1;\n" +
				"\n" +
				"\t\t\tprintf(\"ClassFile time       (ms): \" + lapse0 / 1_000_000d);\n" +
				"\t\t\tprintf(\"ClassDefinition time (ms): \" + lapse1 / 1_000_000d);\n" +
				"\t\t\tprintf(\"Total time           (ms): \" + (lapse0 + lapse1) / 1_000_000d);\n" +
				"\t\t\tprintf(\"ClassDefinition modifiers: \" + classDefinition.getModifiers());\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\tpublic static void print(String s) {\n" +
				"\t\tSystem.out.print(s);\n" +
				"\t}\n" +
				"\n" +
				"\tpublic static void printf(String s) {\n" +
				"\t\tSystem.out.println(s);\n" +
				"\t}\n" +
				"\n" +
				"\tpublic static void print(Object o) {\n" +
				"\t\tSystem.out.print(o);\n" +
				"\t}\n" +
				"\n" +
				"\tpublic static void printf(Object o) {\n" +
				"\t\tSystem.out.println(o);\n" +
				"\t}\n" +
				"\n" +
				"}\n";

//		warmup(input);
		LexicalAnalyzer analyzer = new LexicalAnalyzer();
//		long            start, end, delta, avg = 0;
//		start = System.nanoTime();
		List<Token> result = analyzer.analyze(input);
//		end = System.nanoTime();
//		delta = end - start;
//		avg = delta;

//		for (int i = 0; i < 50_000; i++) {
//			start = System.nanoTime();
//			analyzer.analyze(input);
//			end = System.nanoTime();
//			delta = end - start;
//			avg = (avg + delta) / 2;
//		}

		printf(result);
//		printf("Time (ms, ns): " + avg / 1_000_000 + ", " + avg);
	}

	private static void warmup(String input) {
		LexicalAnalyzer analyzer = new LexicalAnalyzer();
		for (int i = 0; i < 10_000; i++) {
			List<Token> result = analyzer.analyze(input);
		}
	}

	private static void testClassDefinition() throws IOException {
		Path classFile = Paths.get("C:", "Users", "Root-01", "Documents", "javap", "SourceParser.class");
		if (classFile.toFile().isFile()) {
			long start0, start1, lapse0, lapse1;

			start0 = System.nanoTime();
			ClassFile file = new ClassFile(new ClassInputStream(classFile));
			lapse0 = System.nanoTime() - start0;

			start1 = System.nanoTime();
			ClassDefinition classDefinition = new ClassDefinition(file);
			lapse1 = System.nanoTime() - start1;

			printf("ClassFile time       (ms): " + lapse0 / 1_000_000d);
			printf("ClassDefinition time (ms): " + lapse1 / 1_000_000d);
			printf("Total time           (ms): " + (lapse0 + lapse1) / 1_000_000d);
			printf("ClassDefinition modifiers: " + classDefinition.getModifiers());
		}
	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void printf(String s) {
		System.out.println(s);
	}

	public static void print(Object o) {
		System.out.print(o);
	}

	public static void printf(Object o) {
		System.out.println(o);
	}

}
