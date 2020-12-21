package objects;

public class msgRes {	
	
	private String result ;
	private String msg ;
	
	
	public msgRes(String result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
	
	
	public msgRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
