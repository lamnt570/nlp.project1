package my.nlp.project1.tokenizer;

public class Token {

	public static final int TOKEN_TYPE_UNKNOWN 	= 0;
	
	public static final int TOKEN_TYPE_EMAIL 	= 1;
	public static final int TOKEN_TYPE_URL 		= 2;
	
	public static final int TOKEN_TYPE_MONEY 	= 3;
	public static final int TOKEN_TYPE_DATE 	= 4;
	public static final int TOKEN_TYPE_TIME 	= 5;
	public static final int TOKEN_TYPE_PHONE 	= 6;
	public static final int TOKEN_TYPE_NUMBER 	= 7;
	
	public static final int TOKEN_TYPE_WORD 	= 8;
	
	public static final int TOKEN_TYPE_PUNC 	= 9;  // punctuation
	public static final int TOKEN_TYPE_END_PUNC	= 10; // end-sentence-punctuation
	
	
	private int begin, end, type;

	public Token() {
	}

	public Token(int b, int e, int t) {
		setBegin(b);
		setEnd(e);
		setType(t);
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getType() {
		return type;
	}

	protected void setBegin(int begin) {
		this.begin = begin;
	}

	protected void setEnd(int end) {
		this.end = end;
	}

	protected void setType(int type) {
		this.type = type;
	}

}
