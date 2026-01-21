package com.lc.patient_service.grpc;

import billing_service.BillingRequest;
import billing_service.BillingResponse;
import billing_service.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingGrpcServiceClient {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcServiceClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub serviceBlockingStub;

    public BillingGrpcServiceClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9091}") int serverPort
    ) {
        log.info("Connecting to Billing Service GRPC Service at {}:{}", serverAddress, serverPort);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        serviceBlockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId).setName(name).setEmail(email).build();
        BillingResponse response = serviceBlockingStub.createBillingAccount(request);
        log.info("Received Response from Billing service via GRPC: {}", response);
        return response;
    }
}
