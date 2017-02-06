package travelsafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.Constants;
import travelsafe.model.*;
import travelsafe.repository.TravelInsuranceRepository;
import travelsafe.service.GenericService;

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

    /**
     * Validation for travel insurance
     * @param object travel insurance object which need to be validated
     * @return true if object is valid or false if object is not valid
     */
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

                if(homeInsurance.getEstimatedValue() <= Constants.minEstimatedValueHI || homeInsurance.getSurfaceArea() < Constants.minSurfaceAreaHI || homeInsurance.getAge() < Constants.minAgeHI
                        || homeInsurance.getEstimatedValue() > Constants.maxEstimatedValueHI || homeInsurance.getAge() > Constants.maxAgeHI || homeInsurance.getSurfaceArea() > Constants.maxSurfaceAreaHI) {
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

                if(carInsurance.getYearOfProduction() > Constants.minYearOfProductionCI || carInsurance.getYearOfProduction() < Constants.maxYearOfProductionCI) {
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
            int numOfCarrier = 0;
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

                    if(participantInInsurance.getItems() != null){
                        for(Item item : participantInInsurance.getItems()){
                            if(item.getName_en() == null || item.getCoef() == null) {
                                LOG.debug("Type of risk: Name or coef is null");
                                return false;
                            }

                            if(item.getCoef() < 0)
                                return false;
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
                    numOfCarrier++;


            }

            if(numOfCarrier != 1) {
                LOG.debug("There is no carrier or there is 2 or more carriers in participantInInsurances");
                return false;
            }
        }

        if(object.getNumberOfPeople() < Constants.minNumberOfPeopleTI || object.getDuration() <= Constants.minDurationTI || object.getTotalPrice() <= 0 || object.getMaxAmount() <= Constants.minMaxAmountTI
                || object.getNumberOfPeople() > Constants.maxNumberOfPeopleTI || object.getMaxAmount() > Constants.maxMaxAmountTI || object.getDuration() > Constants.maxDurationTI) {
            LOG.debug("NumberOfPeople, Duration, TotalPrice or MaxAmount < 0");
            return false;
        }

        LOG.debug("Travel insurance is valid");
        return true;

    }

    /**
     * Validation for serbian personal id
     * @param pin personal id
     * @return true if pin is valid, false if pin isn't valid
     */
    private boolean validateSerbianPin(String pin){
        if(pin.length() == 13){
            if(pin.matches("^[0-9]*$") && pin.length() > 2)
                return true;
            return false;
        }else
            return false;
    }

    /**
     * General validation for personal id
     * @param pin personal id
     * @return true if pin is valid, false if pin isn't valid
     */
    private boolean validatePin(String pin){
        if(pin.length() > 3)
            return true;
        return false;
    }

}
