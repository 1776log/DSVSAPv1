import DSVdata.Load;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class SlackBot {
    private static String webHookURL;
    private static String oAuthToken;
    private static String slackChannel;
    private static WebhookResponse wbResp;
    public SlackBot() {

//        /*=====testRoom=========================*/
        webHookURL = "https://hooks.slack.com/services/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";
        oAuthToken = "xoxb-3987420323763-3990004125444-PKWx2uqrjynjVajLxnCrLtRh";
        slackChannel = "automationdemochannel";
        /*=========expidite main==================*/
//        webHookURL = "https://hooks.slack.com/services/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";
//        oAuthToken = "xoxb-1823299652327-3990749579332-MabwPGMcdmb3wZFM0e2XxboK";
//        slackChannel = "expedite-main";

    }

    public static void sendMessageToSlack(String message){

        try {
            StringBuilder messageBuilder = new StringBuilder();

            messageBuilder.append(message);

            Payload payload = Payload.builder().channel(slackChannel).text(messageBuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHookURL, payload);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void sendLoadInfo(Load l){

        try {
            StringBuilder messageBuilder = new StringBuilder();

            messageBuilder.append("****Attention: Please check if any matches (DSV load) \n");
            messageBuilder.append("Pay number: " + l.getPayNumber());
            messageBuilder.append("\n");
            messageBuilder.append("Origin: " + l.getOrigin());
            messageBuilder.append("\n");
            messageBuilder.append("Destination: " + l.getDestination());
            messageBuilder.append("\n");
            messageBuilder.append("Distance: " + l.getDistance());
            messageBuilder.append("\n");
            messageBuilder.append("Weight: " + l.getWeight());
            messageBuilder.append("\n");
            messageBuilder.append("Pickup Date: " + l.getPickupDate());
            messageBuilder.append("\n");
            messageBuilder.append("Eqipment type: " + l.getLoadType());
            messageBuilder.append("\n");
            messageBuilder.append("Phone Number: " + l.getPhoneNumber());
            Payload payload = Payload.builder().channel(slackChannel).text(messageBuilder.toString()).build();
            wbResp = Slack.getInstance().send(webHookURL, payload);
            //System.out.println(messageBuilder.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
/*=========expidite main==================*/
//        String webHookURL = "https://hooks.slack.com/services/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";
//        String oAuthToken = "xoxb-1823299652327-3990749579332-MabwPGMcdmb3wZFM0e2XxboK";
//        String slackChannel = "expedite-main";