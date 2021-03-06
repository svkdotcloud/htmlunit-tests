import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class test {

public static void main(String[] args) throws Exception {

    Properties prop = new Properties();
    InputStream input = null;
    boolean title_success = false;
    boolean text_success = false;
    WebClient client = new WebClient();

    // load properties file
    input = new FileInputStream("config.properties");
    prop.load(input);
    String address = prop.getProperty("website_address");
    String title = prop.getProperty("website_title");
    String string = prop.getProperty("website_text");


    System.out.println("1..2");
    HtmlPage currentPage = client.getPage(address);
    try {
    	WebAssert.assertTitleEquals(currentPage, title);
	title_success = true;
	}
    catch (AssertionError e) {
	System.out.println("not ok 1 - " + e);
	System.exit(1);
	}
    finally {
	if (title_success==true) System.out.println("ok 1 - Title: Successfully Tested");
	}
    try {
    	WebAssert.assertTextPresent(currentPage, string);
	text_success = true;
	}
    catch (AssertionError e) {
	System.out.println("not ok 2 - " + e);
	System.exit(2);
	}
    finally {
	if (text_success) System.out.println("ok 2 - Text: Successfully Tested");
	}

	//if (text_success && title_success) System.out.println("ok 3 - Success is 100%");
}

}
