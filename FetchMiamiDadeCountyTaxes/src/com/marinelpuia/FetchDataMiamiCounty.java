package com.marinelpuia;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FetchDataMiamiCounty {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		try {
				
			System.out.println("Please enter a name or account number:\n");
			
			String USER_SEARCH_KEY_STRING = scanner.nextLine();
			scanner.close();
			
	        String URL = "https://miamidade.county-taxes.com/public/search?search_query=";
	        
	        Document document = Jsoup.connect(URL + USER_SEARCH_KEY_STRING).referrer(URL + USER_SEARCH_KEY_STRING).get();
	        
	        // link to open more account details
	        Elements businessAccount = document.select("div.identifier");
	        String businessAccountLink = businessAccount.select("a").first().text();
	        
	        System.out.println("\n\t" + businessAccountLink);
	        System.out.println("\tMailing address: ");
	        
	        // address of business
	        Elements detailsOfBusiness = document.select("div.data-address");
	        String nameAndAddress = detailsOfBusiness.select("div.data-address").first().text();
	        
	        // account details
	        Elements accountDetails = document.select("div.actions");
	        String accDetails = accountDetails.select("div.actions").first().text();
	        
	        // business profile
	        Elements businessProfile= document.select("div.data-qualifier");
	        String occupationLabel = businessProfile.select("div.headers").first().text();
	        String occupation = businessProfile.select("div.data-qualifier > p").first().text();
	        
	        // pay bill button container	        
	        Elements billStatus = document.select("div.pay-bill-button-container");
	        String bill = billStatus.select("div.pay-bill-button-container").first().text();
	        
	        
	        System.out.println("\t" + nameAndAddress + "\n\t" + accDetails + "\n\t" + occupationLabel + ": " + occupation + "\n\t" + bill);
	        
	        System.out.println("\n*************** ALL COMPANIES FOUND ON THIS PAGE ****************");
	        
	        // all companies found on the first page
	        Elements allBusinessAcc = document.select("div.result");
	        
	        // number of business found
	        int count = 0;
	        // print out all companies found on the first page using for loop
	        for(Element businessList: allBusinessAcc) {
	        	count++;
	        	System.out.println("\n(" + count + ") " + businessList.select("a").first().text() + " \n\t "
	        	                                        + businessList.select("div.data-address").first().text() + " \n\t "
	        	                                        + businessList.select("div.actions").first().text() + " \n\t "
	        	                                        + occupationLabel + ": " + businessList.select("div.data-qualifier > p").first().text() + " \n\t "
	        	                                        + businessList.select("div.pay-bill-button-container").first().text()); 	
	        }
	        
	        
			} catch(IOException e) {
				e.printStackTrace();
			}
	}
}
