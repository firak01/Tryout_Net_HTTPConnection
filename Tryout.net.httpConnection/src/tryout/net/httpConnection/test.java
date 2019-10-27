package tryout.net.httpConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Aus dem Buch 
 * "Herb Schildt´s Java Programming Cookbook"
 * Chapter 9, Page 474ff
 *  
 * @author lindhaueradmin
 *
 * lindhaueradmin, 11.04.2014
 */
class HttpConDemo   
{   
  public static void main(String args[]) {   
  
    URL url;  
    HttpURLConnection httpCon;  
  
    try {  
  
      // Create a URL.  
      url = new URL("http://www.herbschildt.com");  
  
      // Open an HTTP connection using url.  
      httpCon = (HttpURLConnection) url.openConnection();   
  
    } catch(MalformedURLException exc) {  
      System.out.println("Invalid URL.");  
      return;  
    } catch(IOException exc) {  
      System.out.println("Error opening connection.");  
      return;  
    }  
   
    // Display information about the resource,  
    // beginning with the date.  
    long date = httpCon.getDate();    
    if(date == 0)   
      System.out.println("No date information.");   
    else   
      System.out.println("Date: " + new Date(date));   
    
    // Show the expiration date.  
    date = httpCon.getExpiration();   
    if(date == 0)   
      System.out.println("No expiration information.");   
    else   
      System.out.println("Expires: " + new Date(date));   
   
    // Show the last-modified date.   
    date = httpCon.getLastModified();   
    if(date == 0)   
      System.out.println("No last-modified information.");   
    else   
      System.out.println("Last-Modified: " + new Date(date));  
   
    // Show the content type.   
    System.out.println("Content-Type: " + httpCon.getContentType());   
  
    // Show the content length.  
    int len = httpCon.getContentLength();   
    if(len == -1)   
      System.out.println("Content length unavailable.");   
    else   
      System.out.println("Content-Length: " + len);   
   
    // Display request method.   
    System.out.println("Request method is " +   
                       httpCon.getRequestMethod());   
  
    try {   
      // Display response code.   
      System.out.println("Response code is " +   
                         httpCon.getResponseCode());   
   
      // Display response message.   
      System.out.println("Response Message is " +   
                         httpCon.getResponseMessage());   
    } catch(IOException exc) {  
      System.out.println("Cannot obtain response info " +  
                         "because the connection failed.");  
      return;  
    }  
   
    // Display header fields.  
    Map<String, List<String>> hdrs = httpCon.getHeaderFields();   
    Set<String> hdrKeys = hdrs.keySet();   
   
    System.out.println("\nHere is the header info:");   
   
    // Display the header.  
    for(String k : hdrKeys)   
      System.out.println("Key: " + k +   
                         "  Value: " + hdrs.get(k));   
  
    // Download and display the content. This will be  
    // displayed as text. It is not rendered as HTML.  
    if(len != 0) {   
      InputStream inStrm; 
 
      try {  
        // Open a stream to the content.  
        inStrm = httpCon.getInputStream();   
      } catch(IOException exc) {  
        System.out.println("Can't open input stream. " + exc);  
        return; 
      }  
  
      System.out.println("\nContent at " + url);  
 
      try { 
        int ch;  
 
        // Display the content.   
        while(((ch = inStrm.read()) != -1)) 
          System.out.print((char) ch);   
 
      } catch(IOException exc) {  
        System.out.println("Error reading content. " + exc);  
      }  
 
      try { 
        inStrm.close();   
      } catch(IOException exc) {  
        System.out.println("Error closing stream. " + exc);  
      }  
    } else    
      System.out.println("No content available.");    
  }   
}
