package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FileReaderServer  {

    private static final Logger LOG = LoggerFactory.getLogger(FileReaderServer.class);
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server= ServerBuilder.forPort(50052)
                .addService(new FileReaderServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();

    }

}
