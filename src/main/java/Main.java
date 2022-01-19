import model.CryptoCurrencies;
import model.DataSet;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String USDExchangeRatesAddress = "https://api.coinbase.com/v2/exchange-rates?currency=USD";
    private static final String BinanceAddress = "https://api2.binance.com/api/v3/ticker/24hr";
    private static final String currenciesUri = "src/main/resources/currencies.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a ISO-4217 code: ");
        String iso4217 = scanner.nextLine();
        DataSet dataSet = Utils.GetCurrencies(USDExchangeRatesAddress, iso4217);
        Utils.CleanDataSet(dataSet, currenciesUri);
        List<CryptoCurrencies> cryptos = Utils.GetCryptoCurrencies(BinanceAddress);
        //Display data
        OutputList(dataSet, cryptos);


    }

    private static void OutputList(DataSet dataSet, List<CryptoCurrencies> cryptos) {
        String symbol = "";
        System.out.printf("\n\n\n%-10s  %-25s  %-10s\n" ,"Symbol" ,"Value in chosen currency" ,"Change vs BTC");
        for(CryptoCurrencies cc : cryptos){
            symbol = cc.getSymbol().substring(0,cc.getSymbol().length()-3);
            if(cc.getSymbol().substring(cc.getSymbol().length() - 3).equals("BTC") && dataSet.getData().getRates().containsKey(symbol)){
                double amountInChosenCurrency = 1 / dataSet.getData().getRates().get(symbol);
                System.out.printf("%-10s  %-25s  %-10s\n" ,symbol ,amountInChosenCurrency ,cc.getPriceChangePercent());
            }
        }
    }


}
