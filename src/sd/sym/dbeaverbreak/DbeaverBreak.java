package sd.sym.dbeaverbreak;


import java.util.Scanner;

import sd.sym.dbeaversecurity.DbeaverSecurity;

/**
 * This code write to decrypt dbeaver encrypt password
 * 
 * as long as last deployement i had forgot my dev enviroment password
 * but that database passowrd is store in dbeaver & i can easily find & connect in 
 * dbeaver 
 * so i writed this code for encrypt same
 * <br>
 * that .dbeaver-data-sources.xml find on below location 
 * for Windows :- C:/User/<User Name>/.dbeaver4/General/.dbeaver-data-sources.xml & 
 * for Linux :- /Home/<User Name>/.dbeaver4/General/.dbeaver-data-sources.xml 
 * <br>
 * @author Mahesh.m
 * @since 22-09-2018
 *
 */
public class DbeaverBreak {
	
	//main method
    public static void main(String[] args) throws Exception{
    	DbeaverSecurity dbeaverSecurity = new DbeaverSecurity();
    	System.out.println("Please enter encrypted dbeaver password from file:.dbeaver-data-sources.xml : ");
    	Scanner sym = new Scanner(System.in);
    	String password = sym.next();
        System.err.println("You have entered : " + password + " & decrypted password is : " + dbeaverSecurity.decrypt(password));
        System.err.println("");
        sym.close();
    }
}

