package org.knowm.xchange.examples.gdax;

import java.io.IOException;
import java.util.Arrays;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.gdax.GDAXExchange;
import org.knowm.xchange.gdax.dto.marketdata.GDAXTrade;
import org.knowm.xchange.gdax.service.GDAXMarketDataServiceRaw;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class GDAXTradesDemo {

  public static void main(String[] args) throws IOException {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(GDAXExchange.class.getName());
    PollingMarketDataService marketDataService = exchange.getPollingMarketDataService();

    generic(marketDataService);
    raw((GDAXMarketDataServiceRaw) marketDataService);
  }

  private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

  public static void generic(PollingMarketDataService marketDataService) throws IOException {

    Trades trades = marketDataService.getTrades(CurrencyPair.BTC_USD, System.currentTimeMillis() - DAY_IN_MILLIS);
    System.out.println(trades);
  }

  public static void raw(GDAXMarketDataServiceRaw marketDataServiceRaw) throws IOException {

    GDAXTrade[] trades = marketDataServiceRaw.getCoinbaseExTrades(CurrencyPair.BTC_USD);
    System.out.println(Arrays.toString(trades));
  }
}
