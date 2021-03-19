package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import etsy.EtsyException;
import etsy.EtsyService;
import etsy.Listing;
import utils.Constants;
import utils.FileHelper;

/**
 * Servlet implementation class DebugServlet
 */

public class DebugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DebugServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(FileHelper.getMethodName());
//		String scope = "?scope=email_r%20listings_r%20listings_w%20listings_d%20transactions_r%20transactions_w%20billing_r%20profile_r%20profile_w%20address_r%20address_w%20favorites_rw%20shops_rw%20cart_rw%20recommend_rw%20feedback_r%20treasury_r%20treasury_w";
//				String client_id = Constants.Etsy.sandbox_client_id;
//				String client_secret = Constants.Etsy.sandbox_client_secret;
//				//Now we will invoke the access method.
//				try {
//					EtsyService.accessEtsyAccount(client_id,client_secret, null,null,scope);
//				} catch (Throwable e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost uploadFile = new HttpPost("...");
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);

		// This attaches the file to the POST:
		File f = new File("[/path/to/upload]");
		builder.addBinaryBody(
		    "file",
		    new FileInputStream(f),
		    ContentType.APPLICATION_OCTET_STREAM,
		    f.getName()
		);

		HttpEntity multipart = builder.build();
		uploadFile.setEntity(multipart);
		CloseableHttpResponse response1 = httpClient.execute(uploadFile);
		HttpEntity responseEntity = response1.getEntity();
		 System.out.println(EtsyService.getService("/shops/__SELF__/listings/active"));



			System.out.println("finished");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
