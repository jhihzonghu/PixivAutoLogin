package test.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class PixivAutoLogin 
{
	static String PostURL = "https://www.secure.pixiv.net/login.php";
	static String PageURL = "http://www.pixiv.net/setting_user.php";
	static HttpPost Post ;
	static HttpClientContext PixivContext ;
	static CloseableHttpClient PixivClient ;
	static CloseableHttpResponse Pixiv_Response;
	static HttpGet GetURL ;
	static HttpEntity PixivEntity;
    public static void main( String[] args ) throws Exception
    {
    	PixivContext = HttpClientContext.create();
    	PixivClient= HttpClients.createDefault();
    	Post = new HttpPost(PostURL);
    	
    	List<NameValuePair> logininfo = new ArrayList<NameValuePair>();
    	logininfo.add(new BasicNameValuePair("mode","login"));
    	logininfo.add(new BasicNameValuePair("return_to","/"));
    	logininfo.add(new BasicNameValuePair("pixiv_id",""));
    	logininfo.add(new BasicNameValuePair("pass",""));
    	logininfo.add(new BasicNameValuePair("skip","1"));

        Post.setEntity(new UrlEncodedFormEntity(logininfo,Consts.UTF_8));
        Pixiv_Response = PixivClient.execute(Post, PixivContext);
        int status = Pixiv_Response.getStatusLine().getStatusCode();
        if(status == HttpStatus.SC_MOVED_TEMPORARILY){
        	System.out.println("Login success!");
        	List<Cookie> ContextOutput = PixivContext.getCookieStore().getCookies();
        	for(int i=0;i<ContextOutput.size();i+=1){
        		System.out.println(ContextOutput.get(i));
        	}
        	
        	//CloseableHttpClient GetPixivClient = HttpClients.createDefault();
        	GetURL= new HttpGet(PageURL);
        	Pixiv_Response = PixivClient.execute(GetURL);
        	PixivEntity = Pixiv_Response.getEntity();
        	String InputPixviv =EntityUtils.toString(PixivEntity);
        	System.out.println(InputPixviv);
        	
        }else{
        	System.out.println("PWD or Account ERROR !");
        }
    }
	
}
