package org.test;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class App {

    private static final String connectionString = "<value>";
    private static final String storageAccountName = "<value>";
    private static final String storageAccessKey = "<value>";
    private static final String storageContainerName = "<value>";

    private static Main main = new Main();

    public static void main(String[] args) throws Exception {

        String eventHubUri = String.format("azure-eventhubs:?connectionString=RAW(%s)&blobAccountName=%s&blobAccessKey=RAW(%s)&blobContainerName=%s", connectionString, storageAccountName, storageAccessKey, storageContainerName);

        main.configure().addRoutesBuilder(new RouteBuilder() {
            public void configure() {
                from(eventHubUri)
                        .process(exchange -> {
                            final String body = new String((byte[]) exchange.getIn().getBody());
                            System.out.println("Processing event: " + body);
                        });
            }
        });

        main.run();
    }
}
