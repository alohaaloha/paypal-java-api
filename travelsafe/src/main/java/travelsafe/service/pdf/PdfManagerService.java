package travelsafe.service.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import travelsafe.model.ParticipantInInsurance;
import travelsafe.model.TravelInsurance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dorian on 2/1/2017.
 */
@Service
public class PdfManagerService {

    private static final Logger LOG = LoggerFactory.getLogger(PdfManagerService.class);

    public void createPDF(TravelInsurance travelInsurance){

        Document document = new Document();
        try
        {
            File f = new File("pdfs\\");
            f.mkdirs();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdfs\\InsuranceReport"+travelInsurance.getId()+".pdf"));
            writer.setPageEvent(new PdfFooter());
            document.open();
            document = setAttributestoPdf(document);

            document.add(loadImage());

            Paragraph titlePar = new Paragraph("TRAVEL INSURANCE PURCHASE",FontFactory.getFont(FontFactory.HELVETICA_BOLD, 30, Font.BOLD, new CMYKColor(255, 255, 255, 0)));
            titlePar.setAlignment(Element.ALIGN_CENTER);
            document.add(titlePar);

            document.add(new Paragraph("\n"));

            String durationHelper = "";

            if(travelInsurance.getDuration() == 1)
                durationHelper = " day";
            else
                durationHelper = " days";


            Paragraph durationPar = new Paragraph("Duration: " + travelInsurance.getDuration() + durationHelper,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
            durationPar.setAlignment(Element.ALIGN_LEFT);
            document.add(durationPar);

            document.add(new Paragraph("\n"));

            Paragraph numOfPeoplePar = new Paragraph("Number of people: " + travelInsurance.getNumberOfPeople(),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
            numOfPeoplePar.setAlignment(Element.ALIGN_LEFT);
            document.add(numOfPeoplePar);

            document.add(new Paragraph("\n"));

            for(ParticipantInInsurance pii : travelInsurance.getParticipantInInsurances()){

                String content = new String();

                if(pii.isCarrier()){
                    content = "\t\t\t\t\tParticipant " + " (carrier): "+ pii.getName() + " " + pii.getSurname();
                }else{
                    content = "\t\t\t\t\tParticipant " + ": "+ pii.getName() + " " + pii.getSurname();
                }

                Paragraph participantPar = new Paragraph(content,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                participantPar.setAlignment(Element.ALIGN_LEFT);
                document.add(participantPar);
            }

            document.add(new Paragraph("\n"));

            Paragraph maxAmountPar = new Paragraph("Maximum amount: " + travelInsurance.getMaxAmount() + " $",FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
            maxAmountPar.setAlignment(Element.ALIGN_LEFT);
            document.add(maxAmountPar);

            document.add(new Paragraph("\n"));

            String region = "";

            if(travelInsurance.getRegion().getName_en() == null)
                region = "Region: " + travelInsurance.getRegion().getName_srb();
            else
                region = "Region: " + travelInsurance.getRegion().getName_en();


            Paragraph regionPar = new Paragraph("Region: " + region,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
            regionPar.setAlignment(Element.ALIGN_LEFT);
            document.add(regionPar);

            document.add(new Paragraph("\n"));

            if(travelInsurance.getInsuranceRebate() != null) {
                Paragraph rebatePar = new Paragraph("Rebate: " + travelInsurance.getInsuranceRebate().getAmount(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                rebatePar.setAlignment(Element.ALIGN_LEFT);
                document.add(rebatePar);
                document.add(new Paragraph("\n"));
            }

            Chunk separator = new Chunk(new DottedLineSeparator());
            document.add(separator);

            //HOME INSURANCE
            if(travelInsurance.getHomeInsurances() != null) {

                if(travelInsurance.getHomeInsurances().size() != 0) {

                    LOG.debug("Home insurances not null");

                    Paragraph homeInsuranceMainPar = new Paragraph("HOME INSURANCE ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    homeInsuranceMainPar.setAlignment(Element.ALIGN_CENTER);
                    document.add(homeInsuranceMainPar);

                    document.add(new Paragraph("\n"));

                    Paragraph homeInsuranceSurfacePar = new Paragraph("\t\t\t\t\tSurface area: " + travelInsurance.getHomeInsurances().get(0).getSurfaceArea(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    homeInsuranceSurfacePar.setAlignment(Element.ALIGN_LEFT);
                    document.add(homeInsuranceSurfacePar);

                    Paragraph homeInsuranceAgePar = new Paragraph("\t\t\t\t\tAge: " + travelInsurance.getHomeInsurances().get(0).getAge(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    homeInsuranceAgePar.setAlignment(Element.ALIGN_LEFT);
                    document.add(homeInsuranceAgePar);

                    Paragraph homeInsuranceEstValPar = new Paragraph("\t\t\t\t\tEstimated value: " + travelInsurance.getHomeInsurances().get(0).getEstimatedValue() + " $", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    homeInsuranceEstValPar.setAlignment(Element.ALIGN_LEFT);
                    document.add(homeInsuranceEstValPar);

                    Paragraph homeInsuranceAddressPar = new Paragraph("\t\t\t\t\tAddress: " + travelInsurance.getHomeInsurances().get(0).getAddress(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    homeInsuranceAddressPar.setAlignment(Element.ALIGN_LEFT);
                    document.add(homeInsuranceAddressPar);

                    Paragraph homeInsuranceOwnerPar = new Paragraph("\t\t\t\t\tOwners informations: " + travelInsurance.getHomeInsurances().get(0).getOwnersName() + " " + travelInsurance.getHomeInsurances().get(0).getOwnersSurname(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    homeInsuranceOwnerPar.setAlignment(Element.ALIGN_LEFT);
                    document.add(homeInsuranceOwnerPar);

                    document.add(new Paragraph("\n"));
                    document.add(separator);
                }
            }
            //HOME INSURANCE END

            //CAR INSURANCE
            if(travelInsurance.getCarInsurances() != null) {

                if(travelInsurance.getCarInsurances().size() != 0) {

                    LOG.debug("Car insurances not null");

                    if (travelInsurance.getHomeInsurances() != null)
                        document.newPage();

                    Paragraph carInsuranceMainPar = new Paragraph("CAR INSURANCE ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    carInsuranceMainPar.setAlignment(Element.ALIGN_CENTER);
                    document.add(carInsuranceMainPar);

                    document.add(new Paragraph("\n"));

                    Paragraph carInsuranceNamePar = new Paragraph("\t\t\t\t\tCar brand name: " + travelInsurance.getCarInsurances().get(0).getBrand(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    carInsuranceNamePar.setAlignment(Element.ALIGN_LEFT);
                    document.add(carInsuranceNamePar);

                    Paragraph carInsuranceTypePar = new Paragraph("\t\t\t\t\tCar type: " + travelInsurance.getCarInsurances().get(0).getType(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    carInsuranceTypePar.setAlignment(Element.ALIGN_LEFT);
                    document.add(carInsuranceTypePar);

                    Paragraph carInsuranceYearPar = new Paragraph("\t\t\t\t\tCar year of production: " + travelInsurance.getCarInsurances().get(0).getYearOfProduction(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    carInsuranceYearPar.setAlignment(Element.ALIGN_LEFT);
                    document.add(carInsuranceYearPar);

                    Paragraph carInsuranceRegistrationPar = new Paragraph("\t\t\t\t\tCar registration number: " + travelInsurance.getCarInsurances().get(0).getRegistrationNumber(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    carInsuranceRegistrationPar.setAlignment(Element.ALIGN_LEFT);
                    document.add(carInsuranceRegistrationPar);

                    Paragraph carInsuranceChassisPar = new Paragraph("\t\t\t\t\tCar chassis number: " + travelInsurance.getCarInsurances().get(0).getChassisNumber(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    carInsuranceChassisPar.setAlignment(Element.ALIGN_LEFT);
                    document.add(carInsuranceChassisPar);

                    Paragraph carInsuranceOwnerPar = new Paragraph("\t\t\t\t\tOwners informations: " + travelInsurance.getCarInsurances().get(0).getOwnersName() + " " + travelInsurance.getCarInsurances().get(0).getOwnersSurname(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
                    carInsuranceOwnerPar.setAlignment(Element.ALIGN_LEFT);
                    document.add(carInsuranceOwnerPar);

                    document.add(new Paragraph("\n"));
                    document.add(separator);
                }
            }
            //CAR INSURANCE END

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            Paragraph pricePar = new Paragraph("\t\t\t\t\tPRICE: " + travelInsurance.getTotalPrice() + " $", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.NORMAL, new CMYKColor(255, 255, 255, 0)));
            pricePar.setAlignment(Element.ALIGN_RIGHT);
            document.add(pricePar);

            LOG.debug("PDF with {} ID made.",travelInsurance.getId());

            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Document setAttributestoPdf(Document document){
        document.addCreationDate();
        document.addAuthor("Management team");
        document.addCreator("TravelsafeManagementTeam.com");
        document.addTitle("Travelsafe™");
        document.addSubject("Purchase");

        return document;
    }

    private Image loadImage(){
        try {
            Image image = Image.getInstance("src\\main\\resources\\images\\ts.jpg");
            image.setAlignment(Element.ALIGN_CENTER);
            return image;
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
