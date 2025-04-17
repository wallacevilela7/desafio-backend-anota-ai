package tech.wvs.anotaaiapi.service.aws;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class AwsSnsService {
    private final String catalogTopicArn;
    private final SnsClient snsClient;

    public AwsSnsService(SnsClient snsClient, @Qualifier("catalogEventsTopic") String catalogTopicArn) {
        this.snsClient = snsClient;
        this.catalogTopicArn = catalogTopicArn;
    }

    public void publish(MessageDto message) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(catalogTopicArn)
                .message(message.toString())
                .build();

        this.snsClient.publish(request);
    }
}
