package Network;

import java.beans.Encoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import asset.Share;

public class StockApi {
	private URL financeurl;
	private InputStream iputstream;
	private Reader freader;
	private BufferedReader bfreader;
	private ArrayList<String> sharelist = new ArrayList<String>();
	
	public Share[] startRateUpdate(String[] sharenames) throws IOException {
		String namebuffer = "";
		for (int i = 0; i < sharenames.length; i++) {
			namebuffer += sharenames[i]+",";
		}
		namebuffer = URLEncoder.encode(namebuffer);
		financeurl = new URL("http://download.finance.yahoo.com/d/quotes.csv?s="+namebuffer+"&f=nl1c4&e=.csv");
		iputstream = financeurl.openStream();
		freader = new InputStreamReader(iputstream);
		bfreader = new BufferedReader(freader);
		String sbuffer = bfreader.readLine();
		Share[] returnshare = new Share[sharenames.length];
		int counter = 0;
		while(sbuffer != null){
			sbuffer = sbuffer.replaceAll("\"", "");
			String[] sbuf = sbuffer.split(",");
			sbuffer.split(",");
			double parser = Double.parseDouble(sbuf[1]) * 100;
			returnshare[counter] = new Share(sbuf[0], (long)parser);
			sharelist.add(sbuffer);
			sbuffer =  bfreader.readLine();
			counter++;
		}
		iputstream.close();
		return returnshare;
	}
}
