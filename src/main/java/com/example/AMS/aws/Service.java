//package com.example.AMS.aws;
//
//
//import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
//import com.amazonaws.services.cognitoidp.model.SignUpResult;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.Map;
//
//public class Service {
//    CognitoClient client=new CognitoClient();
//    public Response authentication(Request request){
//        Response response=new Response();
//        String resource=request.getResource();
//        switch(resource){
//            case "/signup":
//                response= signUp(request.getBody());
//                break;
//            case "/confirmSignUp":
//                response=confirmSignUp(request.getBody());
//                break;
//            case "/login":
//                response=login(request.getBody());
//                break;
//        }
//        return response;
//    }
//    public Response signUp(String body){
//        Response response=new Response();
//        try{
//            ObjectMapper mapper=new ObjectMapper();
//            Map<String,String> parameters = mapper.readValue(body, new TypeReference<Map<String , String>>(){});
//            String name=parameters.get("fullname");
//            String email=parameters.get("email");
//            String password=parameters.get("password");
//            SignUpResult result=client.signup(name,email,password);
//            if(result!=null){
//                response.setStatusCode(200);
//                response.setBody("Confirmation Code is sent to the registered email address");
//            }
//            else{
//                response.setStatusCode(400);
//                response.setBody("Please try again");
//            }
//        }catch(Exception e){
//            response.setStatusCode(500);
//            response.setBody(e.getMessage());
//        }
//        return response;
//    }
//
//
//    public Response confirmSignUp(String body){
//        Response response=new Response();
//        try{
//            ObjectMapper mapper=new ObjectMapper();
//            Map<String,String> parameters = mapper.readValue(body, new TypeReference<Map<String , String>>(){});
//            String email=parameters.get("email");
//            String confirmationCode=parameters.get("confirmationCode");
//            ConfirmSignUpResult result=client.confirmSignUp(email,confirmationCode);
//            if(result!=null){
//                response.setStatusCode(200);
//                response.setBody("Confirmation Code is sent to the registered email address");
//            }
//            else{
//                response.setStatusCode(400);
//                response.setBody("Please try again");
//            }
//        }catch(Exception e){
//            response.setStatusCode(500);
//            response.setBody(e.getMessage());
//        }
//        return response;
//    }
//
//    public Response login(String body){
//        Response response=new Response();
//        try{
//            ObjectMapper mapper=new ObjectMapper();
//            Map<String,String> parameters = mapper.readValue(body, new TypeReference<Map<String , String>>(){});
//            String email=parameters.get("email");
//            String password=parameters.get("password");
//            Map<String, String> token=client.login(email,password);
//            if(token!=null){
//                response.setStatusCode(200);
//                response.setBody("Confirmation Code is sent to the registered email address");
//            }
//            else{
//                response.setStatusCode(400);
//                response.setBody("Please try again");
//            }
//        }catch(Exception e){
//            response.setStatusCode(500);
//            response.setBody(e.getMessage());
//        }
//        return response;
//    }
//}
