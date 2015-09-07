package my.nlp.project1.tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.nlp.project1.regex.Regex;

public class Tokenizer {

	private StringBuilder text;
	private List<Token> extractedToken;
	private boolean tokenized;

	public Tokenizer() {
		extractedToken = new ArrayList<>();
		tokenized = false;
	}

	public Tokenizer(String s) {
		this();
		setText(s);
	}

	public void setText(String s) {
		text = new StringBuilder(s);
		tokenized = false;
	}

	public List<Token> tokenize() {
		if (tokenized)
			return extractedToken;

		extractedToken.clear();

		if (text != null) {
			extractedToken.addAll(findAndRemoveSpecialToken());
			extractedToken.addAll(findTheRestToken());
		}

		extractedToken.sort(new Comparator<Token>() {

			@Override
			public int compare(Token t1, Token t2) {
				int b1 = t1.getBegin(), b2 = t2.getBegin();
				if (b1 < b2)
					return -1;
				else if (b1 == b2)
					return 0;
				else
					return 1;
			}

		});

		tokenized = true;
		return extractedToken;
	}

	public List<Token> findTheRestToken() {
		Integer[] insertPos = findInsertSpacePosition();
		for (int i = 0; i < insertPos.length; i++)
			text.insert(i + insertPos[i], ' ');
		
		List<Token> tokens = findToken("\\S+", Token.TOKEN_TYPE_UNKNOWN);
		for (Token token: tokens){
			// check token type
			String s = text.substring(token.getBegin(), token.getEnd());
			token.setType(checkTokenType(s));
			
			// correct begin and end
			int i = 0;
			for (; i < insertPos.length; i++)
				if (i + insertPos[i] >= token.getBegin())
					break;
			token.setBegin(token.getBegin() - i);
			token.setEnd(token.getEnd() - i);
		}
		
		return tokens;
	}

	private int checkTokenType(String s) {
		if (isWord(s))
			return Token.TOKEN_TYPE_WORD;
		if (s.matches(Regex.PUNCTUATION_REGEX)){
			if (s.matches(Regex.END_PUNCTUATION_REGEX))
				return Token.TOKEN_TYPE_END_PUNC;
			else
				return Token.TOKEN_TYPE_PUNC;
		}
		return Token.TOKEN_TYPE_UNKNOWN;
	}

	private boolean isWord(String s) {
		for (int i = 0; i < s.length(); i++){
			char ch = s.charAt(i);
			if (!Character.isLetter(ch) && ch != '-')
				return false;
		}
		return true;
	}

	private Integer[] findInsertSpacePosition() {
		// need insert space where 2 token are not split
		List<Integer> insertPos = new ArrayList<>();
		
		// case 1: punctuation then word	eg.: (like "quote 
		Pattern p1 = Pattern.compile(Regex.PUNCTUATION_THEN_WORD_REGEX);
		Matcher m1 = p1.matcher(text);
		while (m1.find()){
			insertPos.add(m1.start() + m1.group(1).length());
		}
		
		// case 2: word then punctuation	eg.: end-of-sentence. item1, item2...
		Pattern p2 = Pattern.compile(Regex.WORD_THEN_PUNCTUATION_REGEX);
		Matcher m2 = p2.matcher(text);
		while (m2.find()){
			insertPos.add(m2.end() - m2.group(1).length());
		}
		
		insertPos.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return i1.compareTo(i2);
			}
		});
		
		Integer res[] = insertPos.toArray(new Integer[insertPos.size()]);
		Arrays.sort(res);
		return res;
	}

	private List<Token> findAndRemoveSpecialToken() {
		List<Token> res = new ArrayList<>();
		
		res.addAll(findAndRemoveToken(Regex.EMAIL_REGEX, Token.TOKEN_TYPE_EMAIL));
		res.addAll(findAndRemoveToken(Regex.URL_REGEX, Token.TOKEN_TYPE_URL));
		res.addAll(findAndRemoveToken(Regex.MONEY_REGEX, Token.TOKEN_TYPE_MONEY));
		res.addAll(findAndRemoveToken(Regex.NUMBER_REGEX, Token.TOKEN_TYPE_NUMBER));
		
		return res;
	}

	private List<Token> findAndRemoveToken(String regex, int tokenType) {
		List<Token> tokens = findToken(regex, tokenType);
		removeToken(tokens);
		return tokens;
	}

	private void removeToken(List<Token> tokens) {
		for (Token token : tokens) {
			try {
				for (int i = token.getBegin(); i < token.getEnd(); i++)
					text.setCharAt(i, ' ');
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private List<Token> findToken(String regex, int tokenType) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);

		List<Token> res = new ArrayList<>();

		while (m.find()) {
			int begin = m.start();
			int end = m.end();

			// add the token to result list
			res.add(new Token(begin, end, tokenType));
		}

		return res;
	}

	public List<Token> getExtractedToken() {
		return extractedToken;
	}

}
