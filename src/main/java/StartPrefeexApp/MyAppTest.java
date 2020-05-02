package StartPrefeexApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import MethodsRepo.MyMethods;

public class MyAppTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//MyMethods.FileBackupAction();

		VariableModule.StartModule();

		/*
		String str="[ \r\n" + 
				"		{\r\n" + 
				"			\"name\" : \"Beef petti\",\r\n" + 
				"			\"price\" : 10.89\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"name\" : \"Chicken petti\",\r\n" + 
				"			\"price\" : 1.89\r\n" + 
				"		}\r\n" + 
				"	]";

		MyMethods.JsonParser(str);
		*/
	}
	
}
