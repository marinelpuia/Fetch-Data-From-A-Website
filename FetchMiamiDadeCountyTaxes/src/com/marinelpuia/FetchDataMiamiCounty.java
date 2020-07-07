package com.marinelpuia;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FetchDataMiamiCounty {

	public static void main(String[] args) {

		try {
		
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter a name:\n");
			
			String USER_SEARCH_KEY_STRING = scanner.nextLine();
			scanner.close();
			
	        String URL = "https://miamidade.county-taxes.com/public/search?search_query=";
	        
	        Document document = Jsoup.connect(URL + USER_SEARCH_KEY_STRING).referrer(URL + USER_SEARCH_KEY_STRING).get();
	        
	        Elements businessAccount = document.select("div.identifier");
	        String businessAccountLink = businessAccount.select("a").first().text();
	        
	        System.out.println("\n\t" + businessAccountLink);
	        System.out.println("\tMailing address: ");
	        
	        Elements detailsOfBusiness = document.select("div.data-address");
	        String nameAndAddress = detailsOfBusiness.select("div.data-address").first().text();
	        
	        System.out.println("\t" + nameAndAddress);
	        
	        
			} catch(IOException e) {
				e.printStackTrace();
			}
	}
}
