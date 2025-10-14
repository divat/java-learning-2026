package com.diva.basics.collections;

import java.util.LinkedList;

public class LinkedListDemo {

    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();
        history.visit("diva.com");
        history.visit("jahan.com");
        history.visit("world.com");

        System.out.println("Most recently seen first web page :: " +history.current.webPageUrl);

        history.back();

        //System.out.println("Current web page :: " +history.current.webPageUrl);

        System.out.println("Previous web page :: " +history.current.prev.webPageUrl);

    }

}

class BrowserHistory {

    WebPage current;

    void visit(String url) {
        WebPage newPage = new WebPage(url);
        if(current != null) {
            current.next = newPage;
            newPage.prev = current;
        }
        current = newPage;
    }

    void back() {
        if(current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Back to: " + current.webPageUrl);
        }
    }

}

class WebPage {
    String webPageUrl;
    WebPage prev, next;

    public WebPage(String webPageUrl) {
        this.webPageUrl = webPageUrl;
    }
}
