package org.test;

import org.apache.camel.builder.RouteBuilder;

public class App extends RouteBuilder {

    private static final String connectionString = "<value>";
    private static final String storageAccountName = "<value>";
    private static final String storageAccessKey = "<value>";
    private static final String storageContainerName = "<value>";

    @Override
    public void configure() throws Exception {

        String eventHubUri = String.format("azure-eventhubs:?connectionString=RAW(%s)&blobAccountName=%s&blobAccessKey=RAW(%s)&blobContainerName=%s", connectionString, storageAccountName, storageAccessKey, storageContainerName);

        from(eventHubUri)
                .process(exchange -> {
                    final String body = new String((byte[]) exchange.getIn().getBody());
                    System.out.println("Processing event: " + body);
                });
    }
}
