package com.cg.attendance.controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.attendance.entities.AttendanceDetail;
import com.cg.attendance.exception.CustomResponseEntityExceptionHandler;
import com.cg.attendance.services.AttendanceDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AttendanceDetailController.class)
class AttendanceDetailControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AttendanceDetailService attendanceService;
	@InjectMocks
    private AttendanceDetail attendanceController;
	private JacksonTester<AttendanceDetail> attendanceDetail;
	@BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
               mockMvc = MockMvcBuilders.standaloneSetup(attendanceController)
                .setControllerAdvice(new CustomResponseEntityExceptionHandler())
                 .build();
    }
//    @Test
//	void testAddNewAttendance() throws Exception {
//    	MockHttpServletResponse response = mockMvc.perform(
//                post("/api/attendanceapplication/add-attendance").contentType(MediaType.APPLICATION_JSON).content(
//                        attendanceDetail.write(new AttendanceDetail("1","8:30","18:00","2020-12-01","")).getJson()
//                )).andReturn().getResponse();
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//	}

}
