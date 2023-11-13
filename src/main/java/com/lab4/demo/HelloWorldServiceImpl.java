package com.lab4.demo;

import com.lab4.demo.mariadb.MariaDBController;
import company.Hello;
import company.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloWorldServiceImpl
        extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    MariaDBController mariaDBController = new MariaDBController();
    @Override
    public void hello(
            Hello.HelloRequest request,
            StreamObserver<Hello.HelloResponse> responseObserver) {
        System.out.println(
                "Handling hello endpoint: " + request.toString());

        System.out.println("da?");
        if(request.getText().equals("up"))
        {
            mariaDBController.up();
        }

        if(request.getText().equals("down"))
        {
            mariaDBController.down();
        }

        String text = request.getText() + " World";
        Hello.HelloResponse response =
                Hello.HelloResponse.newBuilder()
                        .setText(text).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
