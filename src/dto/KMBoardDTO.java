package dto;

import java.sql.Date;

public class KMBoardDTO {
	
	private int    kmbNum;
	private String kmbId;
	private String kmbCategory;
	private String kmbTitle;
	private String kmbContents;
	private Date   kmbDate;
	private int	   kmbHits;
	private String kmbFile;
	private int    kmbPoint;
	private int    kmbProblem;
	
	public int getkmbNum() {
		return kmbNum;
	}
	public void setkmbNum(int kmbNum) {
		this.kmbNum = kmbNum;
	}
	public String getkmbId() {
		return kmbId;
	}
	public void setkmbId(String kmbId) {
		this.kmbId = kmbId;
	}
	public String getkmbCategory() {
		return kmbCategory;
	}
	public void setkmbCategory(String kmbCategory) {
		this.kmbCategory = kmbCategory;
	}
	public String getkmbTitle() {
		return kmbTitle;
	}
	public void setkmbTitle(String kmbTitle) {
		this.kmbTitle = kmbTitle;
	}
	public String getkmbContents() {
		return kmbContents;
	}
	public void setkmbContents(String kmbContents) {
		this.kmbContents = kmbContents;
	}
	public Date getkmbDate() {
		return kmbDate;
	}
	public void setkmbDate(Date kmbDate) {
		this.kmbDate = kmbDate;
	}
	public int getkmbHits() {
		return kmbHits;
	}
	public void setkmbHits(int kmbHits) {
		this.kmbHits = kmbHits;
	}
	public String getkmbFile() {
		return kmbFile;
	}
	public void setkmbFile(String kmbFile) {
		this.kmbFile = kmbFile;
	}
	public int getkmbPoint() {
		return kmbPoint;
	}
	public void setkmbPoint(int kmbPoint) {
		this.kmbPoint = kmbPoint;
	}
	public int getkmbProblem() {
		return kmbProblem;
	}
	public void setkmbProblem(int kmbProblem) {
		this.kmbProblem = kmbProblem;
	}

	@Override
	public String toString() {
		return "KMBoardDTO [kmbNum=" + kmbNum + ", kmbId=" + kmbId + ", kmbCategory=" + kmbCategory + ", kmbTitle="
				+ kmbTitle + ", kmbContents=" + kmbContents + ", kmbDate=" + kmbDate + ", kmbHits=" + kmbHits
				+ ", kmbFile=" + kmbFile + ", kmbPoint=" + kmbPoint + ", kmbProblem=" + kmbProblem + "]";
	}
	public KMBoardDTO() {
		super();
	}
	public KMBoardDTO(int kmbNum, String kmbId, String kmbCategory, String kmbTitle, String kmbContents, Date kmbDate, int kmbHits,
			String kmbFile, int kmbPoint, int kmbProblem) {
		super();
		this.kmbNum = kmbNum;
		this.kmbId = kmbId;
		this.kmbCategory = kmbCategory;
		this.kmbTitle = kmbTitle;
		this.kmbContents = kmbContents;
		this.kmbDate = kmbDate;
		this.kmbHits = kmbHits;
		this.kmbFile = kmbFile;
		this.kmbPoint = kmbPoint;
		this.kmbProblem = kmbProblem;
	}

	

}
