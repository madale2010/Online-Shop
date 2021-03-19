/**
 * Project: bmh
 * Package: filters
 * File: GZipServletOutputStream.java
 * 
 * Author: madal
 * Date: Jul 14, 2016
 * 
 * Description: 
 * 
 */
package filters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * 
 */
class GZipServletOutputStream extends ServletOutputStream {
	  private GZIPOutputStream    gzipOutputStream = null;

	  public GZipServletOutputStream(OutputStream output)
	        throws IOException {
	    super();
	    this.gzipOutputStream = new GZIPOutputStream(output);
	  }

	  @Override
	  public void close() throws IOException {
	    this.gzipOutputStream.close();
	  }

	  @Override
	  public void flush() throws IOException {
	    this.gzipOutputStream.flush();
	  }

	  @Override
	  public void write(byte b[]) throws IOException {
	    this.gzipOutputStream.write(b);
	  }

	  @Override
	  public void write(byte b[], int off, int len) throws IOException {
	    this.gzipOutputStream.write(b, off, len);
	  }

	  @Override
	  public void write(int b) throws IOException {
	     this.gzipOutputStream.write(b);
	  }

	/* (non-Javadoc)
	 * @see javax.servlet.ServletOutputStream#isReady()
	 */
	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletOutputStream#setWriteListener(javax.servlet.WriteListener)
	 */
	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub
		
	}
	}
