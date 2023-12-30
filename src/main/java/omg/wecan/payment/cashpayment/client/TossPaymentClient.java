package omg.wecan.payment.cashpayment.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.payment.cashpayment.dto.response.CashPaymentResponse;
import omg.wecan.payment.cashpayment.dto.response.CashPaymentResultResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class TossPaymentClient {
    @Value("${payments.toss.secret_key}")
    private String secretKey;

    public CashPaymentResultResponse fetch(String paymentKey,
                                           String orderId,
                                           Long amount) throws JSONException, IOException, ParseException {

        String encodedSecretKey = new String(Base64.getEncoder().encode(
                (secretKey+":").getBytes(StandardCharsets.UTF_8)
        ));

        HashMap<String, String> result = new LinkedHashMap<>();

        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + encodedSecretKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200 ? true : false;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        if(!isSuccess)
            throw new CustomException(ErrorCode.TOSS_FAIL);

        BufferedReader br = new BufferedReader(new InputStreamReader(responseStream));
        String strRet = br.readLine();
        return convertToResultResponse(strRet);
    }

    private CashPaymentResultResponse convertToResultResponse(String result) throws ParseException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HashMap responseData = parseStringToJson(result);
        JSONObject jsonObject = new JSONObject(responseData);
        String json = jsonObject.toString();

        return objectMapper.readValue(json, CashPaymentResultResponse.class);
    }
    private HashMap parseStringToJson(String s) throws ParseException {
        JSONParser parser = new JSONParser(s);
        return (HashMap) parser.parse();
    }
}
