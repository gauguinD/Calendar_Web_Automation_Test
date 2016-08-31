package main;

public enum TestIds {

	CalUser("nvqa_4tc023", "qalab123", ""),
	CalUser2("nvqa_4tc022", "qalab123", ""),
	CalUser3("nvqa_4tc025", "qalab123", ""),
	WorksCalUser1("cal02@nwetest.com", "naver!23",""),
	WorksCalUser2("cal05@nwetest.com", "naver!23",""),
	WorksCalUser3("cal07@nwetest.com", "naver!23",""),
	WorksCalUser4("cal08@nwetest.com", "naver!23","");


	private String id;
	private String pw;
	private String nick;

	private TestIds(String id, String pw, String nick) {
		this.id = id;
		this.pw = pw;
		this.nick = nick;
	}
	
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}
	
	public String getNick() {
		return nick;
	}
}
