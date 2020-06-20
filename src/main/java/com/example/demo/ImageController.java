package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
	@ResponseBody
	@RequestMapping(value="/imageProcessing")
   public HashMap<String, String> imageProcessing(@RequestBody HashMap<String,String> map) throws Exception {
      System.out.println("## imageProcessing Controller ##");
     // String data = request.getParameter("name");
      String data=map.get("imageData");
      System.out.println(data);
      
      String[] strings = data.split(",");

        String extension;
        switch (strings[0]) {
            case "\"data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "\"data:image/png;base64":
                extension = "png";
                break;
            case "data:image/svg+xml;base64":
               extension = "xml";
               break;
            default:
                extension = "jpg";
                break;
        }
        
        //encode 데이터 -> decode 후 저장
        byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(strings[1]);
        
      String path = "C:/Users/mkrice/Desktop/testimg/test." + extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
        System.out.println("Return Method");
		HashMap<String, String> map2 = new HashMap();
		map2.put("bizno", "215-81-71839");
		return map2;
     
        
   }
}