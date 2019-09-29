import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

public class CalculatorResponseTransformer extends ResponseTransformer {


    @Override
    public Response transform(Request request, Response response, FileSource files, Parameters parameters) {

        String operation = request.queryParameter("operation").firstValue();
        int left = Integer.parseInt(request.queryParameter("left").firstValue());
        int right = Integer.parseInt(request.queryParameter("right").firstValue());

        float result = 0;

        switch (operation) {
            case "plus": result = left + right; break;
            case "minus": result = left - right; break;
            case "multiply": result = left * right; break;
            case "divide": result = (float) left / right; break;
        }

        return Response.Builder.like(response)
                .but()
                .body(String.valueOf(result))
                .build();
    }



    @Override
    public String getName() {
        return "calculator-response-transformer";
    }
}