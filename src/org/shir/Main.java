package org.shir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    public static void main(String ... args)
    {
        LinkedList<String> resultlist = new LinkedList<>();
        boolean onemoretime =true;
        System.out.println("Welcome to currency converter");
        while (onemoretime) {
            System.out.println("Please choose an option (1/2):");
            System.out.println("1. Dollars to Shekels");
            System.out.println("2. Shekels to Dollars");

            Scanner scanner = new Scanner(System.in);
            System.out.println("");
            String input = scanner.next().trim();
            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("The input is not correct,enter 1 or 2");
                input = scanner.next().trim();
            }
            System.out.println("Please enter an amount to convert");

            String amount = "";
            Double parsedouble = 0.0;
            while (parsedouble <= 0) {
                try {
                    amount = scanner.next().trim();
                    parsedouble = Double.parseDouble(amount);
                    if (parsedouble <= 0) {
                        System.out.println("Error, enter a positive number:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error, this is not a number");
                }
            }
            System.out.println("Your amount after converse:");
            Double result=null;
            if (input.equals("1")) {
                result= CoinFactory.getCoininstance(Coins.USD).calculate(parsedouble);
                System.out.println(result.toString().format("%.2f%n",result));
            } else if (input.equals("2")) {
                result= CoinFactory.getCoininstance(Coins.ILS).calculate(parsedouble);
                System.out.println(result.toString().format("%.2f%n",result));
            }

            resultlist.add(result.toString().format("%.2f%n",result));

            System.out.println("YOu want to start over Y / N ?");
            String iWontOneMoreTime = scanner.next().trim().toUpperCase(Locale.ROOT);
            while (! iWontOneMoreTime.equals("Y") && !  iWontOneMoreTime.equals("N") )
            {
                System.out.println("Enter Y / N ?");
                iWontOneMoreTime = scanner.next().trim().toUpperCase(Locale.ROOT);

            }

            onemoretime = "Y".equals(iWontOneMoreTime);
        }

        System.out.println("Thanks for using our currency converter");
        String resultToSave = String.join(",", resultlist);
        System.out.println(resultToSave);

        try {
            File logFile=new File("./result.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.write (resultToSave);
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
