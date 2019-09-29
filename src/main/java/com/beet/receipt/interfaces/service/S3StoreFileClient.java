package com.beet.receipt.interfaces.service;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3StoreFileClient {
	@Value("${cloud.aws.credentials.accessKey}")
	private String key;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
	
	private S3Client s3Client;
	
	@PostConstruct
	public void initialize() {
		AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(key, secretKey);

	    this.s3Client = S3Client.builder().credentialsProvider(StaticCredentialsProvider
	            .create(awsCredentials)).region(Region.US_EAST_1).build();
	}
	
	public void uploadFile(String bucket, String key, Resource content) throws S3Exception, 
    			AwsServiceException, SdkClientException, URISyntaxException, IOException {
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
		        .bucket(bucket).key(key).build();
		RequestBody requestBody = RequestBody.fromInputStream(content.getInputStream(), content.contentLength());
		
		this.s3Client.putObject(putObjectRequest, requestBody);
		
	}

	public InputStreamResource downloadFile(String bucket, String key) throws S3Exception,
					NoSuchKeyException, SdkClientException, AwsServiceException{
		ResponseInputStream<GetObjectResponse> content = this.s3Client.getObject(GetObjectRequest.builder().bucket(bucket).key(key).build(), ResponseTransformer.toInputStream());
		
		return new InputStreamResource(content);
	}

}