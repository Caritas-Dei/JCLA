/*
 * Copyright (c) 2018 Andrew Porter.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 *  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 *  conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jcla.test;

import jcla.ClassDefinition;
import jcla.ClassPool;
import jcla.classfile.ClassFile;
import jcla.classfile.util.ClassFilePool;
import jcla.compiler.analyzer.LexemeAnalyzer;
import jcla.io.ClassInputStream;
import jcla.lang.production.Token;

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


	public static void main(String... args) {
		String input =
				"package jcla.test;\n" +
						"\n" +
						"import jcla.ClassDefinition;\n" +
						"import jcla.ClassPool;\n" +
						"import jcla.classfile.ClassFile;\n" +
						"import jcla.classfile.util.ClassFilePool;\n" +
						"import jcla.compiler.analyzer.LexemeAnalyzer;\n" +
						"import jcla.lang.production.java.token.Token;\n" +
						"import jcla.io.ClassInputStream;\n" +
						"\n" +
						"import java.io.IOException;\n" +
						"import java.nio.file.Path;\n" +
						"import java.nio.file.Paths;\n" +
						"import java.util.List;\n" +
						"\n" +
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
						"\t\tString input =\n" +
						"\t\t\t\t\"package jcla.test;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"import jcla.ClassDefinition;\\n\" +\n" +
						"\t\t\t\t\t\t\"import jcla.ClassPool;\\n\" +\n" +
						"\t\t\t\t\t\t\"import jcla.classfile.ClassFile;\\n\" +\n" +
						"\t\t\t\t\t\t\"import jcla.classfile.util.ClassFilePool;\\n\" +\n" +
						"\t\t\t\t\t\t\"import jcla.compiler.analyzer.LexemeAnalyzer;\\n\" +\n" +
						"\t\t\t\t\t\t\"import jcla.lang.production.java.token.Token;\\n\" +\n" +
						"\t\t\t\t\t\t\"import jcla.io.ClassInputStream;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"import java.io.IOException;\\n\" +\n" +
						"\t\t\t\t\t\t\"import java.nio.file.Path;\\n\" +\n" +
						"\t\t\t\t\t\t\"import java.nio.file.Paths;\\n\" +\n" +
						"\t\t\t\t\t\t\"import java.util.List;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"/**\\n\" +\n" +
						"\t\t\t\t\t\t\" * @author link\\n\" +\n" +
						"\t\t\t\t\t\t\" */\\n\" +\n" +
						"\t\t\t\t\t\t\"public class Test {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tstatic {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t// init pools for the benchmark\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tClassFilePool.getLocal();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tClassPool.getLocal();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tpublic static void main(String... args) throws IOException {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tString input =\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"/**\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\" * @author link\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\" */\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"public class Test {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tstatic {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t// init pools for the benchmark\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tClassFilePool.getLocal();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tClassPool.getLocal();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tpublic static void main(String... args) throws IOException {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tString input = \\\\\\\"\\\\\\\\\\\\\\\"\\\\\\\\\\\\\\\\u0027\\\\\\\\\\\\\\\\u0027\\\\\\\\\\\\\\\"\\\\\\\";\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\twarmup(input);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tLexemeAnalyzer analyzer = new LexemeAnalyzer();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tlong            start, end, delta, avg = 0;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tstart = System.nanoTime();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tList<Token> result = analyzer.analyze(input);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tend = System.nanoTime();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tdelta = end - start;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tavg = delta;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tfor (int i = 0; i < 50_000; i++) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tstart = System.nanoTime();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tanalyzer.analyze(input);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tend = System.nanoTime();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tdelta = end - start;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tavg = (avg + delta) / 2;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tprintf(result);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tprintf(\\\\\\\"Time (ms, ns): \\\\\\\" + avg / 1_000_000 + \\\\\\\", \\\\\\\" + avg);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tprivate static void warmup(String input) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tLexemeAnalyzer analyzer = new LexemeAnalyzer();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tfor (int i = 0; i < 10_000; i++) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tList<Token> result = analyzer.analyze(input);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tprivate static void testClassDefinition() throws IOException {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tPath classFile = Paths.get(\\\\\\\"C:\\\\\\\", \\\\\\\"Users\\\\\\\", \\\\\\\"Root-01\\\\\\\", \\\\\\\"Documents\\\\\\\", \\\\\\\"javap\\\\\\\", \\\\\\\"SourceParser.class\\\\\\\");\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tif (classFile.toFile().isFile()) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tlong start0, start1, lapse0, lapse1;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tstart0 = System.nanoTime();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tClassFile file = new ClassFile(new ClassInputStream(classFile));\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tlapse0 = System.nanoTime() - start0;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tstart1 = System.nanoTime();\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tClassDefinition classDefinition = new ClassDefinition(file);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tlapse1 = System.nanoTime() - start1;\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tprintf(\\\\\\\"ClassFile time       (ms): \\\\\\\" + lapse0 / 1_000_000d);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tprintf(\\\\\\\"ClassDefinition time (ms): \\\\\\\" + lapse1 / 1_000_000d);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tprintf(\\\\\\\"Total time           (ms): \\\\\\\" + (lapse0 + lapse1) / 1_000_000d);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t\\\\tprintf(\\\\\\\"ClassDefinition modifiers: \\\\\\\" + classDefinition.getModifiers());\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tpublic static void print(String s) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tSystem.out.print(s);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tpublic static void printf(String s) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tSystem.out.println(s);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tpublic static void print(Object o) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tSystem.out.print(o);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\tpublic static void printf(Object o) {\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t\\\\tSystem.out.println(o);\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\t}\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"\\\\n\\\" +\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\t\\t\\\"}\\\\n\\\";\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\twarmup(input);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tLexemeAnalyzer analyzer = new LexemeAnalyzer();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tlong            start, end, delta, avg = 0;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tstart = System.nanoTime();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tList<Token> result = analyzer.analyze(input);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tend = System.nanoTime();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tdelta = end - start;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tavg = delta;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tfor (int i = 0; i < 50_000; i++) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tstart = System.nanoTime();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tanalyzer.analyze(input);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tend = System.nanoTime();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tdelta = end - start;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tavg = (avg + delta) / 2;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tprintf(result);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tprintf(\\\"Time (ms, ns): \\\" + avg / 1_000_000 + \\\", \\\" + avg);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tprivate static void warmup(String input) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tLexemeAnalyzer analyzer = new LexemeAnalyzer();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tfor (int i = 0; i < 10_000; i++) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tList<Token> result = analyzer.analyze(input);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tprivate static void testClassDefinition() throws IOException {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tPath classFile = Paths.get(\\\"C:\\\", \\\"Users\\\", \\\"Root-01\\\", \\\"Documents\\\", \\\"javap\\\", \\\"SourceParser.class\\\");\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tif (classFile.toFile().isFile()) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tlong start0, start1, lapse0, lapse1;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tstart0 = System.nanoTime();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tClassFile file = new ClassFile(new ClassInputStream(classFile));\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tlapse0 = System.nanoTime() - start0;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tstart1 = System.nanoTime();\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tClassDefinition classDefinition = new ClassDefinition(file);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tlapse1 = System.nanoTime() - start1;\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tprintf(\\\"ClassFile time       (ms): \\\" + lapse0 / 1_000_000d);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tprintf(\\\"ClassDefinition time (ms): \\\" + lapse1 / 1_000_000d);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tprintf(\\\"Total time           (ms): \\\" + (lapse0 + lapse1) / 1_000_000d);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t\\tprintf(\\\"ClassDefinition modifiers: \\\" + classDefinition.getModifiers());\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tpublic static void print(String s) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tSystem.out.print(s);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tpublic static void printf(String s) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tSystem.out.println(s);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tpublic static void print(Object o) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tSystem.out.print(o);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"\\tpublic static void printf(Object o) {\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t\\tSystem.out.println(o);\\n\" +\n" +
						"\t\t\t\t\t\t\"\\t}\\n\" +\n" +
						"\t\t\t\t\t\t\"\\n\" +\n" +
						"\t\t\t\t\t\t\"}\\n\";\n" +
						"\n" +
						"\t\twarmup(input);\n" +
						"\t\t//warmup(test);\n" +
						"\t\tLexemeAnalyzer analyzer = new LexemeAnalyzer();\n" +
						"\t\tlong start, end, delta, avg;\n" +
						"\t\tstart = System.nanoTime();\n" +
						"\t\tList<Token> result = analyzer.analyze(input);\n" +
						"\t\tend = System.nanoTime();\n" +
						"\t\tdelta = end - start;\n" +
						"\t\tavg = delta;\n" +
						"\n" +
						"\t\tfor (int i = 0; i < 1_000_000; i++) {\n" +
						"\t\t\tstart = System.nanoTime();\n" +
						"\t\t\tanalyzer.analyze(input);\n" +
						"\t\t\t//result = analyzer.analyze(test);\n" +
						"\t\t\tend = System.nanoTime();\n" +
						"\t\t\tdelta = end - start;\n" +
						"\t\t\tavg = (avg + delta) / 2;\n" +
						"\t\t\t//print(i);\n" +
						"\t\t}\n" +
						"\n" +
						"\t\tprintf(result);\n" +
						"\t\tprintf(\"Time (ms, ns): \" + avg / 1_000_000 + \", \" + avg);\n" +
						"\t}\n" +
						"\n" +
						"\tprivate static void warmup(String input) {\n" +
						"\t\tLexemeAnalyzer analyzer = new LexemeAnalyzer();\n" +
						"\t\tfor (int i = 0; i < 10_000; i++) {\n" +
						"\t\t\tList<Token> result = analyzer.analyze(input);\n" +
						"\t\t}\n" +
						"\t}\n" +
						"\n" +
						"\tprivate static void testClassDefinition() throws IOException {\n" +
						"\t\tPath classFile = Paths.get(\"D:\", \"System\", \"Users\", \"linkt\", \"Projects\", \"JClassAssistant\", \"out\", \"production\", \"JCLassAssistant\", \"jcla\", \"ClassDefinition.class\");\n" +
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

		warmup(input);
		//warmup(test);
		LexemeAnalyzer analyzer = new LexemeAnalyzer();
		long start, end, delta, avg;
		start = System.nanoTime();
		List<Token> result = analyzer.analyze(input);
		end = System.nanoTime();
		delta = end - start;
		avg = delta;

		for (int i = 0; i < 1_000_000; i++) {
			start = System.nanoTime();
			analyzer.analyze(input);
			//result = analyzer.analyze(test);
			end = System.nanoTime();
			delta = end - start;
			avg = (avg + delta) / 2;
			//print(i);
		}

		printf(result);
		printf("Time (ms, ns): " + avg / 1_000_000 + ", " + avg);
	}

	private static void warmup(String input) {
		LexemeAnalyzer analyzer = new LexemeAnalyzer();
		for (int i = 0; i < 10_000; i++) {
			List<Token> result = analyzer.analyze(input);
		}
	}

	private static void testClassDefinition() throws IOException {
		Path classFile = Paths.get("D:", "System", "Users", "linkt", "Projects", "JClassAssistant", "out", "production", "JCLassAssistant", "jcla", "ClassDefinition.class");
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
