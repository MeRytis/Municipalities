package com.task.programming.municipalities.municipality;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.programming.municipalities.schedule.Schedule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MunicipalityController.class)
class MunicipalityControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    MunicipalityRepository municipalityRepository;

    Municipality municipalityRecord1 = new Municipality("Kaunas", Schedule.YEARLY, LocalDate.of(2020, Month.JANUARY, 01), LocalDate.of(2020, Month.DECEMBER, 31), new BigDecimal(0.3));
    Municipality municipalityRecord2 = new Municipality("Klaipeda", Schedule.DAILY, LocalDate.of(2020, Month.JANUARY, 11), LocalDate.of(2020, Month.JANUARY, 11), new BigDecimal(0.4));
    Municipality municipalityRecord3 = new Municipality("Vilnius", Schedule.MONTHLY, LocalDate.of(2020, Month.NOVEMBER, 01), LocalDate.of(2020, Month.NOVEMBER, 30), new BigDecimal(0.5));

    @Test
    public void testFindAllRecords() throws Exception {
        List<Municipality> records = new ArrayList<>(Arrays.asList(municipalityRecord1, municipalityRecord2, municipalityRecord3));

        Mockito.when(municipalityRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/municipalities").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3))).andExpect((ResultMatcher) jsonPath("$[2].name", is("Klaipeda")));
    }

    @Test
    public void testAddRecord() throws Exception {
        Municipality record = new Municipality("Vilnius", Schedule.MONTHLY, LocalDate.of(2020, Month.NOVEMBER, 01), LocalDate.of(2020, Month.NOVEMBER, 30), new BigDecimal(0.5));

        Mockito.when(municipalityRepository.save(record)).thenReturn(record);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("api/v1/municipalities").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(record));
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue())).andExpect((ResultMatcher) jsonPath("$.name", is("Vilnius")));
    }

    @Test
    public void testGetRecord() throws Exception {
        Mockito.when(municipalityRepository.findMunicipalityByNameAndStartDateBeforeAndEndDateAfter(municipalityRecord1.getName(), municipalityRecord1.getStartDate(), municipalityRecord1.getEndDate())).thenReturn(java.util.Optional.of(municipalityRecord1));

        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/municipalities/municipality?name=Copenhagen&date=2020-01-31").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue())).andExpect((ResultMatcher) jsonPath("$.name", is("Kaunas")));
    }

}