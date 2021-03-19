/**
 * Project: bmh
 * Package: utils
 * File: HTMLParser.java
 * 
 * Author: madal
 * Date: Aug 23, 2016
 * 
 * Description: 
 * 
 */
package utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 
 */
public class HTMLParser {
		  
		  public static String toHtml( String string )
		  {
		    if(string.isEmpty())
		      return "";
		    
		    BufferedReader st = new BufferedReader( new StringReader( string ) );
		    StringBuffer buf = new StringBuffer( "<p>" );

		    try
		    {
		      String str = st.readLine();

		      while( str != null )
		      {
		        if( str.equalsIgnoreCase( "<br/>" ) )
		        {
		          str = "<br>";
		        }

		        buf.append( str );

		        if( !str.equalsIgnoreCase( "<br>" ) )
		        {
		          buf.append( "<br>" );
		        }

		        str = st.readLine();
		      }
		    }
		    catch( IOException e )
		    {
		      e.printStackTrace();
		    }

		    buf.append( "</p>" );
		    string = buf.toString();
		    return string;
		  }
}
