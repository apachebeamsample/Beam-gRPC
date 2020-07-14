# Beam-gRPC
Getting data through gRPC and applying the transforms

Steps:
1. Read the json file and pass it to the client using server side streaming in gRPC
2. Capturing the json data in list of strings.
3. Creating a Pcollection from the list
4. applying transforms to get the userId of the users(involves mapping to object and filtering of data)
