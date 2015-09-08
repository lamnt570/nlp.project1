package my.nlp.project1.regex;

public final class Regex {
	
	public static final String EMAIL_REGEX = "[\\w-]+(\\.[\\w-]+)*@(([\\w-]+)\\.)*([\\w-]+)";
	
	public static final String URL_REGEX = "(https?://)?([a-z][\\w-~]*(\\.[\\w-~]+)+|\\d{1,3}(\\.\\d{1,3}){3})(:\\d{1,5})?(/[\\w-~=\\?]*)*";
	
	public static final String NUMBER_REGEX = "[-–+]?\\d+([\\.,]\\d+)*";

	public static final String MONEY_REGEX = "\\p{Sc}\\d+([\\.,]\\d+)*|\\d+([\\.,]\\d+)*\\p{Sc}";
	
	public static final String PHONE_REGEX = "(\\(+\\d+\\))?\\d+([\\.-]\\d+)+";
	
	public static final String DATE_REGEX = "";
	
	public static final String TIME_REGEX = "";
	
	public static final String PERCENTAGE_REGEX = "\\d+([\\.,]\\d+)*\\%";
	
	public static final String WORD_REGEX = "[\\p{L}]+([-–]\\p{L}+)*";
	
	public static final String WORD_WITH_NUMBER_REGEX = "[\\p{L}\\d]+([-–][\\p{L}\\d]+)*";
		
	// ... . , ; ( ) [ ] { } " ' ! ? : | \ / ‘ ’ “ ”
	public static final String PUNCTUATION_REGEX = "(\\.{3}|\\.|,|;|\\(|\\)|\\[|\\]|\\{|\\}|\"|'|!|\\?|:|\\||\\\\|/|\\‘|\\’|\\“|\\”)";
	
	// . ! ?
	public static final String END_PUNCTUATION_REGEX = "(\\.|!|\\?)";
	
	public static final String PUNCTUATION_THEN_WORD_REGEX = PUNCTUATION_REGEX + "[\\w\\p{L}]+";
	
	public static final String WORD_THEN_PUNCTUATION_REGEX = "[\\w\\p{L}]+" + PUNCTUATION_REGEX;
	
	public static final String PUNC_SEQUENCE_REGEX = PUNCTUATION_REGEX + "{2,}";
}
