import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import my.nlp.project1.tokenizer.Token;
import my.nlp.project1.tokenizer.Tokenizer;

public class Test {
	
	private static String readTextFile(String filePath) throws IOException{
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	private static void writeOutfile(String text, List<Token> tokens, String filePath) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (Token t : tokens) {
//			builder.append(t.getBegin() + " " + t.getEnd() + "\n);
			String type = null;
			switch (t.getType()) {
			case Token.TOKEN_TYPE_EMAIL		: type = "EMAIL"; break;
			case Token.TOKEN_TYPE_URL		: type = "URL"; break;
			case Token.TOKEN_TYPE_MONEY		: type = "MONEY"; break;
			case Token.TOKEN_TYPE_PERCENTAGE: type = "PERCENTAGE"; break;
			case Token.TOKEN_TYPE_DATE		: type = "DATE"; break;
			case Token.TOKEN_TYPE_TIME		: type = "TIME"; break;
			case Token.TOKEN_TYPE_PHONE		: type = "PHONE"; break;
			case Token.TOKEN_TYPE_NUMBER	: type = "NUMBER"; break;
			case Token.TOKEN_TYPE_WORD		: type = "WORD"; break;
			case Token.TOKEN_TYPE_WORD_NUM	: type = "WORD with NUMBER"; break;
			case Token.TOKEN_TYPE_PUNC		: type = "PUNCTUATION"; break;
			case Token.TOKEN_TYPE_END_PUNC	: type = "END-PUNCTUATION"; break;
			default:
				type = "UNKNOWN";
			}
			builder.append(type + "\n");
			builder.append(text.substring(t.getBegin(), t.getEnd()) + "\n\n");
		}
		
		FileWriter writer = new FileWriter(filePath);
		writer.write(builder.toString());
		writer.close();		
	}
	
	public static void main(String args[]) throws Exception{
		String inputDir = "test/input";
		String outputDir = "test/output";
		
		Tokenizer tokenizer = new Tokenizer();
		
		File inDir = new File(inputDir);
		String fileNames[] = inDir.list();
		
		for (String fileName: fileNames){
			try {
				System.out.println("Processing file " + fileName);
				String text = readTextFile(inputDir + "/" + fileName);
				tokenizer.setText(text);
				
				List<Token> tokens = tokenizer.tokenize();
				writeOutfile(text, tokens, outputDir + "/" + fileName);
				
				System.out.println("DONE\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERROR\n");
			}
		}

		
//		String text = "my gmail is ntlam.94@gmail.com from mail.google.com. this is my vnu email lamnt_570@vnu.edu.vn byebye! haha 1.000 list:  danh-sách: item 1, item 2, item 3... aaa .    0.5$ 100,000,000₫ ¥10.0";
//		String text = readTextFile("test/input/test46.txt");
//		
//		Tokenizer tokenizer = new Tokenizer(text);
//		
//		List<Token> tokens = tokenizer.tokenize();
//		for (Token t: tokens){
//			switch (t.getType()) {
//			case Token.TOKEN_TYPE_DATE:
//				System.out.println("DATE"); break;
//			case Token.TOKEN_TYPE_EMAIL:
//				System.out.println("EMAIL"); break;
//			case Token.TOKEN_TYPE_MONEY:
//				System.out.println("MONEY"); break;
//			case Token.TOKEN_TYPE_PERCENTAGE:
//				System.out.println("PERCENTAGE"); break;
//			case Token.TOKEN_TYPE_NUMBER:
//				System.out.println("NUMBER"); break;
//			case Token.TOKEN_TYPE_TIME:
//				System.out.println("TIME"); break;
//			case Token.TOKEN_TYPE_URL:
//				System.out.println("URL"); break;
//			case Token.TOKEN_TYPE_WORD:
//				System.out.println("WORD"); break;
//			case Token.TOKEN_TYPE_WORD_NUM:
//				System.out.println("WORD with NUMBER"); break;
//			case Token.TOKEN_TYPE_PUNC:
//				System.out.println("PUNCTUATION"); break;
//			case Token.TOKEN_TYPE_END_PUNC:
//				System.out.println("END-PUNCTUATION"); break;
//			case Token.TOKEN_TYPE_UNKNOWN:
//				System.out.println("UNKNOWN"); break;
//			}
//			System.out.println(text.substring(t.getBegin(), t.getEnd()));
//			System.out.println();
//		}
	}

}
