package main;

public enum TestIds {

	CalUser("nvqa_4tc023", "qalab123", "");
	
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
