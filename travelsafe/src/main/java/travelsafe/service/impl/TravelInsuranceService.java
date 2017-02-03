package travelsafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.*;
import travelsafe.repository.TravelInsuranceRepository;
import travelsafe.service.GenericService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class TravelInsuranceService implements GenericService<TravelInsurance> {

    private static final Logger LOG = LoggerFactory.getLogger(TravelInsuranceService.class);

    @Autowired
    private TravelInsuranceRepository travelInsuranceRepository;

    @Override
    public List<TravelInsurance> getAll() {
        return travelInsuranceRepository.findAll();
    }

    @Override
    public TravelInsurance save(TravelInsurance entity) {
         return travelInsuranceRepository.save(entity);
    }

    public boolean validation(TravelInsurance object){

        if(object.getDuration() == null ||
           object.getMaxAmount() == null ||
           object.getNumberOfPeople() == null ||
           object.getPrice() == null ||
           object.getRegion() == null) {
            LOG.debug("Duration, maxAmount, NumberOfPeople, Price or Region is null");
            return false;
        }

        if(object.getHomeInsurances() != null){

            LOG.debug("Home insurance is not null");

            for(HomeInsurance homeInsurance : object.getHomeInsurances()){
                if(homeInsurance.getOwnersName() == null ||
                   homeInsurance.getOwnersSurname() == null ||
                   homeInsurance.getAddress() == null ||
                   homeInsurance.getEstimatedValue() == null ||
                   homeInsurance.getOwnersPIN() == null ||
                   homeInsurance.getInsuranceDescription() == null ||
                   homeInsurance.getSurfaceArea() == null ||
                   homeInsurance.getAge() == null) {
                    LOG.debug("Home insurance: OwnersNameSurnamePIN, Address, EstimatedValue, InsuranceDescription, SurfaceArea or Age is null");
                    return false;
                }

                if(!validatePin(homeInsurance.getOwnersPIN())) {
                    LOG.debug("Home insurance: Owners PIN not valid");
                    return false;
                }

                if(homeInsurance.getEstimatedValue() <= 0) {
                    LOG.debug("Home insurance: Estimated Value <= 0");
                    return false;
                }
            }

        }

        if(object.getCarInsurances() != null){

            LOG.debug("Car insurance is not null");

            for(CarInsurance carInsurance : object.getCarInsurances()){
                if(carInsurance.getOwnersName() == null ||
                   carInsurance.getOwnersSurname() == null ||
                   carInsurance.getBrand() == null ||
                   carInsurance.getChassisNumber() == null ||
                   carInsurance.getOwnersPIN() == null ||
                   carInsurance.getRegistrationNumber() == null ||
                   carInsurance.getYearOfProduction() == null) {
                    LOG.debug("Car insurance: OwnersNameSurnamePIN, Brand, ChassisNumber, RegistrationNumber or YearOfProduction is null");
                    return false;
                }

                if(!validatePin(carInsurance.getOwnersPIN())) {
                    LOG.debug("Car insurance: Owners PIN not valid");
                    return false;
                }

                if(carInsurance.getYearOfProduction() > Calendar.getInstance().get(Calendar.YEAR)) {
                    LOG.debug("Car insurance: Year of production > Current year");
                    return false;
                }
            }
        }

        if(object.getParticipantInInsurances() == null) {
            LOG.debug("Participant in insurances is null");
            return false;
        }else{
            boolean carrier = false;
            for(ParticipantInInsurance participantInInsurance : object.getParticipantInInsurances()){
                if(participantInInsurance.getName() == null ||
                   participantInInsurance.getSurname() == null ||
                   participantInInsurance.getDateOfBirth() == null ||
                   participantInInsurance.getEmail() == null ||
                   participantInInsurance.getPassportNumber() == null ||
                   participantInInsurance.getPin() == null) {
                    LOG.debug("Participant in insurance: Name, Surname, DateOfBirth, Email, PassportNumber or PIN is null");
                    return false;
                }

                    if(participantInInsurance.getTypeOfRisks() != null){
                        for(TypeOfRisk typeOfRisk : participantInInsurance.getTypeOfRisks()){
                            if(typeOfRisk.getName() == null) {
                                LOG.debug("Type of risk: Name is null");
                                return false;
                            }

                            if(typeOfRisk.getItems() != null){
                                for(Item item : typeOfRisk.getItems()){
                                    if(item.getName() == null ||
                                       item.getCoef() == null) {
                                        LOG.debug("Item: Name or Coef is null");
                                        return false;
                                    }

                                    if(item.getCoef() <= 0) {
                                        LOG.debug("Item: Coef cannot be negative");
                                        return false;
                                    }

                                }
                            }

                        }
                    }

                if(!(participantInInsurance.getEmail().contains("@") && participantInInsurance.getEmail().contains("."))) {
                    LOG.debug("Participant in insurance: Invalid email format");
                    return false;
                }

                if(!validatePin(participantInInsurance.getPin())) {
                    LOG.debug("Participant in insurance: Invalid PIN");
                    return false;
                }

                if(participantInInsurance.getDateOfBirth().after(new Date())) {
                    LOG.debug("Participant in insurance: Date of Birth > current date");
                    return false;
                }

                if(participantInInsurance.getCarrier())
                    carrier = true;

            }

            if(!carrier) {
                LOG.debug("No carrier in participantInInsurances");
                return false;
            }
        }

        if(object.getNumberOfPeople() <= 0 || object.getDuration() <= 0 || object.getTotalPrice() <= 0 || object.getMaxAmount() <= 0) {
            LOG.debug("NumberOfPeople, Duration, TotalPrice or MaxAmount < 0");
            return false;
        }

        LOG.debug("Travel insurance is valid");
        return true;

    }

    private boolean validatePin(String pin){
        if(pin.length() == 13){
            if(pin.matches("^[0-9]*$") && pin.length() > 2)
                return true;
            return false;
        }else
            return false;
    }

}
