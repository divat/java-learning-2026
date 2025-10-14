package com.diva.basics.java8.functionalprogramming;

public class CallbackFunction {

    public static void main(String[] args) {
        DataFetcher dataFetcher = new DataFetcher();
        dataFetcher.fetchData(new FetcherCallback() {

            @Override
            public void onSuccess(String message) {
               System.out.println(message);
            }
            
        });

        //Lambda way

        FetcherCallback cb = (msg) -> System.out.println(msg);
        dataFetcher.fetchData(cb);
    }

}

class DataFetcher {

    public void fetchData(FetcherCallback callback) {
        System.out.println("Fetching the data to do transaction...");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        callback.onSuccess("Account details fetched to do the transaction....");
    }

}

interface FetcherCallback {
    void onSuccess(String message);
}
