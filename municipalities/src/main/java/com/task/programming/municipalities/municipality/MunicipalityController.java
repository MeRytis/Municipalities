package com.task.programming.municipalities.municipality;

import com.task.programming.municipalities.exception.IncorrectRequestParameterException;
import com.task.programming.municipalities.exception.MunicipalityDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/municipalities")
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    @Autowired
    public MunicipalityController(MunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }

    @GetMapping
    public List<Municipality> getMunicipalities() {
        return municipalityService.getMunicipalities();
    }

    @GetMapping("municipality")
    public ResponseEntity getMunicipalityByNameAndDate(@RequestParam(value = "name") String name, @RequestParam(value = "date") String date) {
        try {
            return new ResponseEntity(municipalityService.getMunicipalityByNameAndDate(name, date), HttpStatus.OK);
        } catch (MunicipalityDoesNotExistException doesNotExistException) {
            return new ResponseEntity(doesNotExistException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IncorrectRequestParameterException badParameterException) {
            return new ResponseEntity(badParameterException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public void addMunicipality(@RequestBody Municipality municipality) {
        municipalityService.addMunicipality(municipality);
    }

}
