package com.task.programming.municipalities.municipality;

import com.task.programming.municipalities.exception.IncorrectRequestParameterException;
import com.task.programming.municipalities.exception.MunicipalityDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class MunicipalityService {

    private static final String MUNICIPALITY_NOT_FOUND_ERROR_MESSAGE = "Municipality does not found!";
    private static final String INCORRECT_REQUEST_PARAMETER_EXCEPTION = "Incorrect request parameter defined!";

    private final MunicipalityRepository municipalityRepository;

    @Autowired
    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    public List<Municipality> getMunicipalities() {
        return municipalityRepository.findAll();
    }

    public void addMunicipality(Municipality municipality) {
        municipalityRepository.save(municipality);
    }

    public Municipality getMunicipalityByNameAndDate(String name, String date) throws MunicipalityDoesNotExistException, IncorrectRequestParameterException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            LocalDate convertedDate = simpleDateFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (isNameParameterDefined(name)) {
                return getMunicipality(name, convertedDate);
            } else {
                throw new IncorrectRequestParameterException(INCORRECT_REQUEST_PARAMETER_EXCEPTION);
            }
        } catch (ParseException parseException) {
            throw new IncorrectRequestParameterException(INCORRECT_REQUEST_PARAMETER_EXCEPTION);
        }

    }

    private boolean isNameParameterDefined(String name) {
        return StringUtils.hasText(name);
    }

    private Municipality getMunicipality(String name, LocalDate date) throws MunicipalityDoesNotExistException {
        Optional<Municipality> municipality = municipalityRepository.findMunicipalityByNameAndStartDateBeforeAndEndDateAfter(name, date, date);
        if (municipality.isPresent()) {
            return municipality.get();
        } else {
            throw new MunicipalityDoesNotExistException(MUNICIPALITY_NOT_FOUND_ERROR_MESSAGE);
        }
    }
}
