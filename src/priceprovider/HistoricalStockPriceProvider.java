package priceprovider;

import Exception.WrongNameException;

import asset.Share;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

public class HistoricalStockPriceProvider extends StockPriceProvider {
    
    private Map <String, List<Long>> historyData = new TreeMap <String, List<Long>>();
    private int flag = 1;
    
    public HistoricalStockPriceProvider(){
        super();
        setHistoryData();
    }
    

    @Override
    protected void updateShareRate(Share share)
    {
        
        List<Long> history = historyData.get(share.name);
        if(flag < history.size()){
            share.setActualSharePrice(history.get(flag));
        }else{
            flag = 1;
        }
    }
    @Override
    protected void  updateShareRates (){
        for (int i = 0; i < availableShare.size(); i++){
            if (availableShare.get(i) != null){
                updateShareRate(availableShare.get(i));
            }
        }
        flag++;
    }
    private void setHistoryData(){
        try {
            Share [] sharebuffer = super.getAvailableShare();
            for (int i = 0; i < sharebuffer.length; i++){
                List<Long> values = new ArrayList<Long>();
                BufferedReader in = new BufferedReader(new FileReader("../Java_Boersenmanager/data/" + sharebuffer[i].name));
                String file = "";
                String buffer = "";
                while(true){
                    buffer = in.readLine();
                    if(buffer == null)
                        break;
                    file = file + buffer + "\n";
                }
                String[] fileSplit = file.split("\n");
                for(int c = 0; c < fileSplit.length; c++){
                    values.add(Long.parseLong(fileSplit[c]));
                }
                historyData.put(sharebuffer[i].name, values);
                in.close();
            }
        } catch (IOException e){
            ;
        }
        
    }

}
