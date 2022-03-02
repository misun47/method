package domain;

import java.sql.Date;

public class SampleVO {
	
	private int num;
	private String strData;
	private Date sampleDate;
	
	public SampleVO() {
		super();
		
	}

	@Override
	public String toString() {
		return "SampleVO [num=" + num + ", strData=" + strData + ", sampleDate=" + sampleDate + "]";
	}
	
	public SampleVO(int num, String strData, Date sampleDate) {
		super();
		this.num = num;
		this.strData = strData;
		this.sampleDate = sampleDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStrData() {
		return strData;
	}

	public void setStrData(String strData) {
		this.strData = strData;
	}

	public Date getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}
	
}
