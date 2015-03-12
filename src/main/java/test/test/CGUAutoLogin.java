package test.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class CGUAutoLogin {
	static String POSTURL = "http://webpac.cgu.edu.tw:81/ipac20/ipac.jsp?profile=cgul&menu=account";
	static String POSTURL_2="http://ids.cgu.edu.tw:8080/nidp/idff/sso?id=3&sid=0&option=credential&sid=0";
	static String GETURL = "http://ck101.com/";
	static String _VIEWSTATE ="ZPNQUUSPYAdyAk9N3GJ8pWY3gaQ53NGV7zpZSmHSqYcq5IZN1aHe8JdYVMuWoBAQzA+IDuKfy3MzaY2SvdU7f2Sn7N99R8rDCGZPe23aLymEV5jS3YFQd8h5Y8bTvAdlTNOopStmrJO0Q5cDY7vkNh6dK2HOHSKyGNNQRv9LKglp1i/eXYY/IZvVqqxcTfJsVdCVU+1VLvbJjmZRNMAAOowszTb5+yG+aBxZZs8CQFogu5l4pYSxf3iG6Xy1UA4C7oC4hTUIPUTDTb2y1xpC7G1DDDUv38Nhb2ZgKoNL33XJBBuDIwagyx8zi9ygGBzgSfyWa8Vw9nw+iFMHCnPXhlBBxJv2e57Li+i+auBMzb2+4iQV6ZY8PQDLOJhpfr0VJlhH+QmJyUe57o4DaCRTjsw25iP54P/w9PX1hREQX+jiFvYct9oT+17Tl7On8h2skyd9jNjUQa9DC2yOK9B3EDjTZpajB9lfrZABH7ImkO/l98HTvOG+UzcVEaLP3o5TZKkcYwuRQDnljwcv+8DIjZu9hEX1pKj5xDEiDj+DkX+s1WRQYiKPylj7LaMhKzYIQRGJ3QLNnd6AO2+ZRuywHsxzB49K/6421k1fesbWYeoequXOqvkOZrjr8b0uKdx+6D2DQUqFRZdjXI6bzgInjW8c26Idy3x+LLy6OCizQm5I0vqE2xo/VeoxM2spcCtLQgszOnqTE1ZC18Sfs5DXrYQW+hEqPIG1D1eIzmH8tdx4VaLV/s6j3gAJHQ32igujlW7B44sx7Fx9VZKhPecM0Dv+hnTtjfg1o5hJIj7L4ljhWrMDhy8tv5awEV4i5jqIfmqgr+Wrf8Q9WzEa2/YOZ3LAGwWtdIo7a+wQG+j0FEfkDBiNZ7EMvdj1+7lq6Wuhjw32OMB5JyUfoOnrSN2sALzOw95bCfFIih8vfidtyNOX91VAHXJXw6qeE4ahPSu9PjYQUf8AAIP59NZmxYCmsQ3pQlb/aPA4HXOHB5Grr8oAGFMDU9HUzrI2B87DgjniZDmlkzXDiw0t0yl3tk4yUFX6uRA/95MZm37RhNCbFDbhSITWCXOVLzLdI8krTdcrzGLnJUBSUGrrL6r3YOxFjXSXJuyxqjEkJ+3XG8nWhhd89HIy9zfl1pzRnJBkp77Qa3t//xGZBi8q9owGLJpPLtkmlA9lJagDYCNFrota4hfpsJKsycm22nYACZBOSlS55M7/JQ==";
	static String _VIEWGENTOR="60D46A81";
	static List<NameValuePair> CGUAutoLoginInfo = new ArrayList<NameValuePair>();
	static HttpClientContext CGUCollectCookie;
	static CloseableHttpClient CGUAutoLoginClient;
	static CloseableHttpResponse CGUAutoLoginResponse;
	static HttpEntity CGUAutoLoginGetEntity;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
           CGUCollectCookie = HttpClientContext.create();
           CGUAutoLoginClient=HttpClients.createDefault();
           HttpPost POSTurl = new HttpPost(POSTURL); 
           CGUAutoLoginInfo.add(new BasicNameValuePair("__VIEWSTATE",_VIEWSTATE));
           CGUAutoLoginInfo.add(new BasicNameValuePair("__VIEWSTATEGENERATOR",_VIEWGENTOR));
           CGUAutoLoginInfo.add(new BasicNameValuePair("Signin1:account",""));
           CGUAutoLoginInfo.add(new BasicNameValuePair("Signin1:password",""));
           CGUAutoLoginInfo.add(new BasicNameValuePair("Signin1:SigninBtn","登入 Sign in"));
           POSTurl.setEntity(new UrlEncodedFormEntity(CGUAutoLoginInfo));
           CGUAutoLoginResponse = CGUAutoLoginClient.execute(POSTurl,CGUCollectCookie);
           int status = CGUAutoLoginResponse.getStatusLine().getStatusCode();
           System.out.println(status);
           if(status==302)
           {
        	 //HttpGet GETurl = new HttpGet(GETURL);
        	 //CGUAutoLoginResponse = CGUAutoLoginClient.execute(GETurl,CGUCollectCookie);
        	 CGUAutoLoginGetEntity = CGUAutoLoginResponse.getEntity();
        	 String Output = EntityUtils.toString(CGUAutoLoginGetEntity);
        	 System.out.println(Output);
           }
	}

}
