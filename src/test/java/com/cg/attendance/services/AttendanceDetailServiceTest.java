package com.cg.attendance.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

class AttendanceDetailServiceTest {
@Autowired
MockMvc mockMvc;

@MockBean
AttendanceDetailService attendanceService;
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
