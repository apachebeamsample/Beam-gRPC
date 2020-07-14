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
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        List<String> inputStreams =new ArrayList<>();
        FileReaderServiceGrpc.FileReaderServiceBlockingStub stub=FileReaderServiceGrpc.newBlockingStub(channel);
        System.out.println("start reading file");
        String fileName="sample.txt";
        //Getting the lines in client as soon as it is read from the server side using server streaming
        stub.readFile(FileReaderRequest.newBuilder().setFileName(fileName).build())
                .forEachRemaining(fileReaderResponse -> {
              //  System.out.println(fileReaderResponse.getLine());
                // Adding the read lines in String list object to form PCollection once every input is read
                inputStreams.add(fileReaderResponse.getLine().toString());
                });

        System.out.println("Shutting down channel");
        channel.shutdown();
        //After capturing all the input calling the beam transform.
        PipelineMain.applyTransforms(inputStreams);

    }
}
