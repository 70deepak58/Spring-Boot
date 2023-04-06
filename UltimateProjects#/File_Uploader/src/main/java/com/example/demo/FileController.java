package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@RestController
@RequestMapping("/db")
public class FileController {
	@Autowired
	private FileAdapter fa;
	//IMG upload section
	//@PostMapping("/img_upload")
	
	
	
	
	
	//CSV upload section
	@PostMapping("/upload")
	public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
				HashMap<Integer,Account> accountList=new HashMap<>();
				InputStream inputstream = file.getInputStream();
				CsvParserSettings settings =new CsvParserSettings();
				settings.setHeaderExtractionEnabled(true);
				CsvParser parser =new CsvParser(settings);
				List<Record> parseAllRecords = parser.parseAllRecords(inputstream);
				parseAllRecords.forEach(x-> {
					Account account = new Account();
					int acc_no = Integer.parseInt(x.getString("account_number"));
					//account.setId(Integer.parseInt(x.getString("id")));
					account.setAccount_number(Integer.parseInt(x.getString("account_number")));
					account.setFirst_name(x.getString("first_name"));
					account.setLast_name(x.getString("last_name"));
					account.setAccount_type(x.getString("account_type"));
					account.setAddress(x.getString("address"));
					account.setEmail(x.getString("email"));
					account.setZipcode(x.getString("zipcode"));
					accountList.put(acc_no,account);
					
				});
		
		fa.saveAll(new ArrayList<>(accountList.values()));
		return "upload Successfull  !!";
	}
	
	//CSV download section
	@GetMapping(value="/download", produces="text/csv")
	public ResponseEntity<Resource> downloadData() {
		//return "dwd";
		String[] csvHeader = {
			"sno",	"account_number", "first_name", "last_name", "account_type", "address", "email", "zipcode"
	    };
		List<Account> la=fa.findAll();
		List<List<String>> csvBody = new ArrayList<>();
		for(int i=0;i<la.size();i++) {
			String sno=Integer.toString(i);
			String acn=  Integer.toString(la.get(i).getAccount_number());
			String fn=la.get(i).getFirst_name();
			String ln=la.get(i).getLast_name();
			String act=la.get(i).getAccount_type();
			String ad=la.get(i).getAddress();
			String em=la.get(i).getEmail();
			String zp=la.get(i).getZipcode();
			csvBody.add(Arrays.asList(sno,acn,fn,ln,act,ad,em,zp));
		}
		
		ByteArrayInputStream byteArrayOutputStream;
		
	    try (
	            ByteArrayOutputStream out = new ByteArrayOutputStream();	    		
	            CSVPrinter csvPrinter = new CSVPrinter(
	                    new PrintWriter(out),
	                    CSVFormat.DEFAULT.withHeader(csvHeader)
	            );
	    ) {
	        // populating the CSV content
	        for (List<String> record : csvBody)
	            csvPrinter.printRecord(record);

	        // writing the underlying stream
	        csvPrinter.flush();

	        byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	        throw new RuntimeException(e.getMessage());
	    }

	    InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

	    String csvFileName = "people.csv";

	    // setting HTTP headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
	    // defining the custom Content-Type
	    headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

	    return new ResponseEntity<>(
	            fileInputStream,
	            headers,
	            HttpStatus.OK
	    );	
	}
	


}
