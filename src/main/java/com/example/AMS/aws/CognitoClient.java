//package com.example.AMS.aws;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.http.apache.utils.ApacheUtils;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
//import com.amazonaws.services.cognitoidp.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
////helps to send data to cognito
//
//public class CognitoClient {
//    private final AWSCognitoIdentityProvider client;
//    private final String clientId="2lkpl89mi3bdj2ebj4jeisr9su";
//    private final String userPool="eu-north-1_rY0zvgWYH";
//
//    CognitoClient(){
//        client= createCognitoClient();
//    }
//    private AWSCognitoIdentityProvider createCognitoClient(){
//        AWSCredentials cred= new BasicAWSCredentials("AKIAWDUX6SCCZYDYQVPK","J4mlqwDcgkW3pdzKph+SKmoUPl87jxb+CedmQkBf");
//        AWSCredentialsProvider credProvider =new AWSStaticCredentialsProvider(cred);
//        return AWSCognitoIdentityProviderClientBuilder.standard()
//                .withCredentials(credProvider)
//                .withRegion(Regions.AP_NORTHEAST_1)
//                .build();
//    }
//
//    public SignUpResult signup(String name,String email, String password){
//        SignUpRequest request=new SignUpRequest().withClientId(clientId).withUsername(email).withPassword(password);
//        SignUpResult result=client.signUp(request);
//        return result;
//    }
//
//    public ConfirmSignUpResult confirmSignUp(String email,String confirmationCode){
//        ConfirmSignUpRequest confirmSignUpRequest=new ConfirmSignUpRequest().withClientId(clientId).withUsername(email).withConfirmationCode(confirmationCode);
//        return client.confirmSignUp(confirmSignUpRequest);
//    }
//
//    public Map<String,String> login(String email,String password){
//        Map<String,String> authparams =new LinkedHashMap<String,String>(){{
//            put("USERNAME",email);
//            put("PASSWORD",password);
//        }};
//        AdminInitiateAuthRequest authRequest= new AdminInitiateAuthRequest()
//                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
//                .withUserPoolId(userPool)
//                .withClientId(clientId)
//                .withAuthParameters(authparams);
//        AdminInitiateAuthResult authResult=client.adminInitiateAuth(authRequest);
//        AuthenticationResultType resultType=authResult.getAuthenticationResult();
//        return new LinkedHashMap<String,String>(){{
//           put("idToken", resultType.getIdToken());
//           put("accessToken",resultType.getAccessToken());
//           put("refreshToken",resultType.getRefreshToken());
//           put("message","Successfully login");
//        }};
//    }
//
//}
