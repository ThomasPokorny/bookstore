package org.tp.proxy;

import org.tp.proxy.api.BookStoreProxyController;
import org.tp.proxy.model.Book;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Thomas Pokorny
 * PRECONDITION: running bookservice, awaits until the service is started and reachable.
 * POSTCONDITION: created one initial default book entity.
 */
public class InitialDataFactory {
    private final int maxAttempts;
    private final int timeout;
    private final int sleep;

    public InitialDataFactory(int maxAttempts, int timeout, int sleep) {
        this.maxAttempts = maxAttempts;
        this.timeout = timeout;
        this.sleep = sleep;
    }

    public void createInitialData() {
        boolean isBookServiceRunning = checkForServiceAvailability();
        if (isBookServiceRunning) {
            BookStoreProxyController c = new BookStoreProxyController();
            c.createBook(createInitialBook());

            System.out.println(c.getBook());
        }
    }

    private static Book createInitialBook() {
        Book initialBook = new Book();
        initialBook.setAuthor("Hermann Hesse");
        initialBook.setTitle("Der Steppenwolf");
        initialBook.setIsbn("9780312278670");
        return initialBook;
    }

    private boolean checkForServiceAvailability() {

        System.out.println("establishing connection to server..");

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < maxAttempts; i++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(BookStoreProxyController.BOOK_STORE_URL, new Integer(BookStoreProxyController.BOOK_STORE_PORT)), timeout);
                System.out.println("[OK] connection to bookstore established!");
                return true;
            } catch (IOException ignored) {
            }
        }
        System.out.println("[FAILURE] cannot establish connection to bookstore");
        return false;
    }

    public static final int CONNECTION_ATTEMPTS = 20;
    public static final int TIMEOUT = 5;
    public static final int AWAIT_TIMEOUT = 10000;
}
