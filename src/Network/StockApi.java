package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import asset.Share;

public class StockApi {
	private URL financeurl;
	private InputStream iputstream;
	private Reader freader;
	private BufferedReader bfreader;
	private ArrayList<String> sharelist = new ArrayList<String>();
	private Logger outputlogger = Logger.getLogger(StockApi.class.getName());

	@SuppressWarnings("deprecation")
	public Share[] startRateUpdate(String[] sharenames) throws IOException{
		String namebuffer = "";
		Share[] returnshare = new Share[sharenames.length];
		int counter = 0;
		for (int i = 0; i < sharenames.length; i++) {
			namebuffer += sharenames[i]+",";
		}
		namebuffer = URLEncoder.encode(namebuffer);
		financeurl = new URL("http://download.finance.yahoo.com/d/quotes.csv?s="+namebuffer+"&f=nl1c4&e=.csv");
		try {
			iputstream = financeurl.openStream();
		} catch (UnknownHostException e) {
			return alternativProvider(sharenames, namebuffer);
		}
		freader = new InputStreamReader(iputstream);
		bfreader = new BufferedReader(freader);
		String sbuffer = bfreader.readLine();
		while(sbuffer != null){
			sbuffer = sbuffer.replaceAll("\"", "");
			String[] sbuf = sbuffer.split(",");
			sbuffer.split(",");
			double parser = Double.parseDouble(sbuf[1]) * 100;
			returnshare[counter] = new Share(sbuf[0], (long)parser);
			returnshare[counter].setFinanceName(sharenames[counter]);
			returnshare[counter].setExchange(sbuf[2]);
			sharelist.add(sbuffer);
			sbuffer =  bfreader.readLine();
			counter++;
		}
		iputstream.close();
		return returnshare;
	}
	
	private Share[] alternativProvider(String[] sharenames, String url) throws IOException{
		Share[] returnshare = new Share[sharenames.length];
		outputlogger.warning("Side"+"http://download.finance.yahoo.com/d/quotes.csv?s="+url+"&f=nl1c4&e=.csv Can't be found");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		writer.write("Game is running in Random Stock Price Provider Mode info see log \r\n");
		writer.flush();
		for (int i = 0; i < sharenames.length; i++) {
			Random r = new Random();
			returnshare[i] = new Share(sharenames[i], (r.nextLong() % 300)+400);
		}
		return returnshare;
	}
}
