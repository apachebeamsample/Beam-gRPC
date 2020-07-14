package client;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.extensions.jackson.ParseJsons;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.List;

public class PipelineMain {

    public static void applyTransforms(List<String> input){
        //System.out.println("in pipeline main"+input);
        System.out.println("Started applying Transforms");
        Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.fromArgs().withValidation().create());
        //Converting the String object into PCollection to apply transform
        PCollection<String> users=pipeline.apply(Create.of(input));
        PCollection<UserDetails> userList=users.apply(ParseJsons.of(UserDetails.class)).setCoder(SerializableCoder.of(UserDetails.class));
        userList.apply(MapElements.into(TypeDescriptors.strings()).via(u -> u.getUserId()))
                .apply(TextIO.write().to("./src/output/userId.txt").withNumShards(1));
        System.out.println("Completed the operations, please check the output in Output folder");
        pipeline.run().waitUntilFinish();
    }
}
