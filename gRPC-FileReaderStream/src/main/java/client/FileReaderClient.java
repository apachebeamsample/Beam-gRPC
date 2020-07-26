package client;

import com.fileReader.generated.stubs.FileReaderRequest;
import com.fileReader.generated.stubs.FileReaderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.avro.generic.GenericData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FileReaderClient {

    private static final Logger LOG = LoggerFactory.getLogger(FileReaderClient.class);

    //private static final Logger LOG = LoggerFactory.getLogger(StudentClient.class);
    public static ManagedChannel getChannelInstance(){
        System.out.println("Eastablishing connection with server");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        System.out.println("Connection successful");
        return channel;

    }
}
