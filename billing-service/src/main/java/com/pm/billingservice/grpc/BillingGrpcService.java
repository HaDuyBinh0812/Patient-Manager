package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseStreamObserver){

        log.info("createBillingAccount request received {}", billingRequest.toString());

        String accountId = "ACC-" + billingRequest.getPatientId();

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId(accountId)
                .setStatus("SUCCESS")
                .build();

        // Gửi kết quả về client
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}
