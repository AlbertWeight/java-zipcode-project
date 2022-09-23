import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.zipcode.Place;
import com.kenzie.app.data.zipcode.ZipCodeDTO;
import com.kenzie.app.format.AddressFormatUtil;
import com.kenzie.app.http.HttpUtil;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        try {
            //declare variables
            String BASE_URL = "https://www.zippopotam.us/us/";
            Scanner scanner = new Scanner(System.in);
            ObjectMapper objectMapper = new ObjectMapper();
            String recipientName;
            String streetAddress;
            String city;
            String state;
            String zipCode;

            //read in user input - scanner
            System.out.println("Enter recipient name: ");
            recipientName = scanner.nextLine();
            System.out.println("Enter street address: ");
            streetAddress = scanner.nextLine();
            System.out.println("Enter city: ");
            city = scanner.nextLine();
            System.out.println("Enter state: ");
            state = scanner.nextLine();

            //clean the city
            String tempCity = city.replaceAll(" ", "%20");

            //format URL with user city and state
            String finalURL = BASE_URL + state + "/" + tempCity;
            System.out.println(finalURL);

            //Call GET
            String httpResponse = HttpUtil.sendGET(finalURL);
            System.out.println(httpResponse);

            //if return String contains 404, don't object map
            //ObjectMapper to retrieve zip code

            if (httpResponse.contains("GET request failed")) {
                System.out.println("No zip code found");
                zipCode = "";
            } else {
                ZipCodeDTO zipCodeObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);
                if(zipCodeObj.getPlaces().size() == 1) {
                    zipCode = zipCodeObj.getPlaces().get(0).getPostCode();
                } else {
                    for (int i = 0; i < zipCodeObj.getPlaces().size(); i++) {
                        System.out.println("(" + i + ") " + zipCodeObj.getPlaces().get(i).getPostCode());
                    }
                }
                System.out.println("Choose a zipcode");
                int choice = scanner.nextInt();
                scanner.nextLine();

                zipCode = zipCodeObj.getPlaces().get(choice).getPostCode();
            }
            System.out.println(AddressFormatUtil.formatAddress(recipientName));
            System.out.println(AddressFormatUtil.formatStreetAddress(streetAddress));
            System.out.println(AddressFormatUtil.formatStreetAddress(city + "," + state + " " + zipCode));

        }catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
        }
    }



    public static void main_backup(String[] args) {
        try {
            String URL = "https://api.zippopotam.us/us/ca/concord";
            String httpResponse;

            httpResponse = HttpUtil.sendGET(URL);

            System.out.println(httpResponse);

            ObjectMapper objectMapper = new ObjectMapper();
            ZipCodeDTO zipObj;
            zipObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);

            System.out.println();
            System.out.println("State: " + zipObj.getState());
            System.out.println("City: " + zipObj.getPlaces().get(0).getPlace_name());
            System.out.println("ZipCode: " + zipObj.getPlaces().get(0).getPostCode());

            if (zipObj.getPlaces().size() == 1) {
                System.out.println(zipObj.getPlaces().get(0).getPostCode());

            } else if (zipObj.getPlaces().size() > 1) {
                for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                    System.out.println("Zone " + i);
                    System.out.println("State: " + zipObj.getState());
                    System.out.println("City: " + zipObj.getPlaces().get(i).getPlace_name());
                    System.out.println("ZipCode: " + zipObj.getPlaces().get(i).getPostCode());
                }
            }

            String testStr = "123 Main St.";
            AddressFormatUtil.initCodeTable();
            System.out.println(AddressFormatUtil.replaceAbbreviaton(testStr));



        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
        }
    }
}
