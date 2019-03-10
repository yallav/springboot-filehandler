Project Description:

This project is designed to accept text and font format from the client and generates image of the text. Later this image will be uploaded to the specified AWS S3 bucket.

Project requires the following dependencies
	
	1. JAVA sdk 1.8 or later
	2. AWS S3 access key, access secret key, S3 bucket
	3. Manage public access control lists (ACLS) on S3 bucket should be set to "False"
	4. Local folder to write image file as backup as well as to act as destination folder for downloading the files from AWS S3 through REST API
	
We have to follow the below steps to use this sample code 

1. Clone this project with git clone https://github.com/yallav/springboot-filehandler.git

2. Give the values to application properties

3. mvn package

4. java -jar springboot-filehandler 0.0.1-SNAPSHOT

With the above, we are set to trigger REST API to create and upload file to S3 bucket & download the file from S3 bucket

I tired all below mentioned REST APIs in POSTMAN client. Also, I developed Angular client to create image file and upload the file to AWS S3. 

1. Post REST API details are as follows:

URL: http://localhost:8000/api/createanduploadfile

Request Headers: Content-Type : application/json

Request body format: 

[
	
	{
		"text": "Hello",
		"header": true,
		"fontName": "Arial",
		"fontType": 0,
		"fontWeight": 40
		
	},
	
	{
		"text": "message vdsgsfdgs sdfdsfdsfdsfdfdsfdfdsfsdfdsfdsfdsfsdfdsfdsfdsfsdfsdfsdfdsfdsfdsfsdfsfdsfdsfsdfsdfsdf",
		"header": false,
		"fontName": "Arial",
		"fontType": 1,
		"fontWeight": 10
	}
]

Click Send button on POSTMAN

Response: {
    "message": "File upload was successful",
    "fileName": "Text.png"
}

On the successful execution of the above REST, you will get "Test.png" file in the local directory and in S3 bucket

2. GET REST API details are as follows.
	 
URL: http://localhost:8000/api/downloadfile/Text.png

Request Headers: Content-Type : application/json

Click Send button on POSTMAN

Response: Yet to implement, but please check the directory to find the downloaded file i.e. inside "filedownload.dir"

3. Post REST API details for uploading file into AWS S3 bucket are as follows:

URL: http://localhost:8000/api/uploadfile

Request Headers: Content-Type : "multipart/form-data"

Form-data : 
	1. Enter key name as file and type as "File"
	2. Choose the file to upload file

Click Send button on POSTMAN

Thank you for your interest in my code. Happy coding.