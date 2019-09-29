import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Calculator {

    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(options().port(8070).extensions(CalculatorResponseTransformer.class));
        wireMockServer.start();


        wireMockServer.stubFor(get(urlMatching("/calculator\\?operation=([a-z]*)&left=([0-9]+)&right=([0-9]+)"))
                .willReturn(
                        aResponse()
                            .withTransformer("calculator-response-transformer","","")
                            .withStatus(200)
                            .withHeader("Content-Type", "text/html")
        ));
    }

}
