package dto;

import java.sql.Date;

public class KMMemberDTO {
	private String kmId;
	private String kmPw;
	private String kmName;
	private String kmBirth;
	private String kmGender;
	private String kmEmail;
	private String kmFile;
	private int kmPoint;
	public String getkmId() {
		return kmId;
	}
	public void setkmId(String kmId) {
		this.kmId = kmId;
	}
	public String getkmPw() {
		return kmPw;
	}
	public void setkmPw(String kmPw) {
		this.kmPw = kmPw;
	}
	public String getkmName() {
		return kmName;
	}
	public void setkmName(String kmName) {
		this.kmName = kmName;
	}
	public String getkmBirth() {
		return kmBirth;
	}
	public void setkmBirth(String kmBirth) {
		this.kmBirth = kmBirth;
	}
	public String getkmGender() {
		return kmGender;
	}
	public void setkmGender(String kmGender) {
		this.kmGender = kmGender;
	}
	public String getkmEmail() {
		return kmEmail;
	}
	public void setkmEmail(String kmEmail) {
		this.kmEmail = kmEmail;
	}
	public String getkmFile() {
		return kmFile;
	}
	public void setkmFile(String kmFile) {
		this.kmFile = kmFile;
	}
	public int getkmPoint() {
		return kmPoint;
	}
	public void setkmPoint(int kmPoint) {
		this.kmPoint = kmPoint;
	}
	@Override
	public String toString() {
		return "MemberDTO [kmId=" + kmId + ", kmPw=" + kmPw + ", kmName=" + kmName + ", kmBirth=" + kmBirth
				+ ", kmGender=" + kmGender + ", kmEmail=" + kmEmail + ", kmFile=" + kmFile + ", kmPoint=" + kmPoint
				+ "]";
	}
	public KMMemberDTO() {
		super();
	}
	public KMMemberDTO(String kmId, String kmPw, String kmName, String kmBirth, String kmGender, String kmEmail,
			String kmFile, int kmPoint) {
		super();
		this.kmId = kmId;
		this.kmPw = kmPw;
		this.kmName = kmName;
		this.kmBirth = kmBirth;
		this.kmGender = kmGender;
		this.kmEmail = kmEmail;
		this.kmFile = kmFile;
		this.kmPoint = kmPoint;
	}

	
	
	
	
	
}
