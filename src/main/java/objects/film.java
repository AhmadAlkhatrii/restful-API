package objects;

public class film {
	private String title ;
	private int FID =0 ;
	private String dirName;
	private String genre ; 
	private String year  ;
	private String a1Name=null  ;
	private String a2Name=null ;
	private String a3Name=null ;
	private double rate=0;
	
		public film(String title, int FID ,String dirName, String genre, String year, String a1Name, String a2Name, String a3Name,double rate) {
		super();
		this.title = title;
		this.FID=FID;
		this.dirName = dirName;
		this.genre = genre;
		this.year = year;
		this.a1Name = a1Name;
		this.a2Name = a2Name;
		this.a3Name = a3Name;
		this.rate=rate;
	}
		
		
	public int getFID() {
			return FID;
		}

		public void setFID(int fID) {
			FID = fID;
		}

	public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public String getA1Name() {
		return a1Name;
	}
	public void setA1Name(String a1Name) {
		this.a1Name = a1Name;
	}
	public String getA2Name() {
		return a2Name;
	}
	public void setA2Name(String a2Name) {
		this.a2Name = a2Name;
	}
	public String getA3Name() {
		return a3Name;
	}
	public void setA3Name(String a3Name) {
		this.a3Name = a3Name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}