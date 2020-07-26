package server;

import com.fileReader.generated.stubs.FileReaderRequest;
import com.fileReader.generated.stubs.FileReaderResponse;
import com.fileReader.generated.stubs.FileReaderServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.fileReader.generated.stubs.FileReaderServiceGrpc.getReadFileMethod;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

public class FileReaderServiceImpl extends FileReaderServiceGrpc.FileReaderServiceImplBase {

    @Override
    public void readFile(FileReaderRequest request,
                         StreamObserver<FileReaderResponse> responseObserver) {
        //asyncUnimplementedUnaryCall(getReadFileMethod(), responseObserver);
        System.out.println("Request received");
        try (FileReader fr = new FileReader("./src/main/resources/sampleRest.json");
             BufferedReader br = new BufferedReader(fr)) {
            String nextLine;
            while ((nextLine = br.readLine()) != null) {
                //System.out.println(nextLine+"this is line");
                responseObserver.onNext(FileReaderResponse.newBuilder()
                        .setLine(nextLine)
                        .build());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //localTmpFile.delete();
        }
        responseObserver.onCompleted();
        System.out.println("Response sent successfully");
    }
 }

