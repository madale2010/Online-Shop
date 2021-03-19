package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBManager;
import utils.Document;
import utils.FileHelper;

/**
 * Servlet implementation class UpdateDocument
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)  
public class UpdateDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDocument() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(FileHelper.getMethodName());
		db = (DBManager) getServletContext().getAttribute("db");
		String action = request.getParameter("action")!=null ?request.getParameter("action"):"";
		if(request.getParameter("action")!=null){
			
			if(action.equalsIgnoreCase("change")) {
				String documentName=request.getParameter("documentName");
				Document currentDocument =Document.load(documentName);
				request.setAttribute("document", currentDocument);
				request.setAttribute("docName", currentDocument.getName());
				request.setAttribute("docURL", currentDocument.getUrl());
				request.getRequestDispatcher("updateSingleDocument.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("save")) {
				Document saveDocument = new Document();
				saveDocument.setName(request.getParameter("docName"));
				saveDocument.setUrl(request.getParameter("docURL"));
				saveDocument.setData(request.getParameter("tinymce"));
				saveDocument.insertToDatabase();
				
				//Last thing is redirect back to page listing
				response.sendRedirect("listDocuments.jsp");
			}
			else if(action.equalsIgnoreCase("update")) {
				Document saveDocument = new Document();
				saveDocument.setName(request.getParameter("docName"));
				saveDocument.setUrl(request.getParameter("docURL"));
				saveDocument.setData(request.getParameter("tinymce"));
				saveDocument.updateToDatabase();
				
				//Last thing is redirect back to page listing
				response.sendRedirect("listDocuments.jsp");
			}
			else if(action.equalsIgnoreCase("delete")) {
				Document saveDocument = new Document();
				String docName = request.getParameter("documentName");
				saveDocument.setName(docName);
				saveDocument.deleteFromDatabase();
				
				//Last thing is redirect back to page listing
				response.sendRedirect("listDocuments.jsp");
			}
			
		} 
		
		else {
			//We are just listing all the documents from the db
			pullAllRecords(request);
		}
		
	}

	/**
	 * Method: pullAllRecords
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	private void pullAllRecords(HttpServletRequest request) {
		Connection conn = db.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement("select * from BMH_DOCUMENTS");
			ResultSet resultSet = statement.executeQuery();
			ArrayList<Document> documentList = new ArrayList<Document>();
			while (resultSet.next()) {
				documentList.add(new Document(resultSet));
			}
			resultSet.close();
			statement.close();
			request.setAttribute("documentList", documentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
