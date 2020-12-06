package com.cg.attendance.exception;

public class AttendanceIdExceptionResponse {

	private String attendanceId;
	
	public AttendanceIdExceptionResponse(String attendanceId) {
		super();
		this.attendanceId=attendanceId;
	}

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}


}