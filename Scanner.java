package cilico.tools.barcode;
   
import java.io.IOException;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Scanner {
	static public final int BARCODE_READ= 10;
	static public final int BARCODE_NOREAD = 12;
	 static Boolean m_bASYC=false;	  
	 static int m_nCommand=0;
	 static public Handler m_handler=null;
	 /**
	  * ͬ��ɨ��һά�룬ɨ�費�����ȴ�һ��ʱ�䣬Ħ��������ɨ��ͷ�ȴ��3�룬
	  * �����з�ɨ��ͷ��ȴ�10�룬�Զ�ʶ���������з�ɨ��ͷ����Ħ��������ɨ��ͷ
	  * @return 		������ɨ�赽�����룬���ûɨ����Ϊ���ַ�
	  */
	 static public native String ReadSCAAuto();
	 /**
	  * ͬ��ɨ��һά�룬ɨ�費�����ȴ�һ��ʱ�䣬Ħ��������ɨ��ͷ�ȴ��3�룬
	  * �����з�ɨ��ͷ��ȴ�10��
	  * @param 	nCode 	��ɨ��ͷ��ʾ�������з�ɨ��ͷ0x55,Ħ������ɨ��ͷ0x00
	  * @return			������ɨ�赽�����룬���ûɨ����Ϊ���ַ�
	  */
	 static public native String ReadSCA(int nCode);
	 /**
	  * ��ʼ���豸
	  * 
	  * @return			���ɹ�����0
	  */
	 static public native int InitSCA();
	 /**
	  * �첽ɨ�裬���øú���ᴴ��һ��ɨ���߳�ȥɨ�裬
	  * ɨ�赽�������ɨ�賬ʱ����ͨ��handle������Ϣ������ߣ�
	  * ����ʹ���첽ɨ��Ļ���������Ľ��մ����ﴴ����handle,
	  * ����handle����m_handler��ɨ�赽���뷢��Message BARCODE_READ��
	  * ��ʱ����Message BARCODE_NOREAD
	  */
	 static public void Read()
	   {
		   if(m_bASYC)
		   {			 
		       return;
		   }
		   else
		   {
			   //m_nCommand=nCode;
			   StartASYC();  
		   }
	   }
	 /**
	  * ɨ���߳�
	  */
	   static  void StartASYC()
      {
		   m_bASYC=true;		   
		   Thread thread = new Thread(new Runnable() {
  		   public void run() {  		  
  			  
  			   if(m_handler!=null)
  			   {    
  
  				 String str=ReadSCAAuto();   
  				   Message msg=new Message();  
  				   msg.what=str.length()>0?BARCODE_READ:BARCODE_NOREAD;
  				   msg.obj=str;   
  				          
  				   m_handler.sendMessage(msg);   
  				 
  			   }         
  				   
  			m_bASYC=false;
  		   }  
  		 });
		   thread.start();
      }
	   /**
	    * ���������Ϣ�����԰�String��������Ϣ��ʽ���͸�ϵͳ
	    * @param str		��Ҫ���͵��ַ�
	    */
	 static public  void SendString(String str)               
	    {    			     
	        try {  
	        	Runtime.getRuntime().exec("input keyevent 66"); 
	            Runtime.getRuntime().exec("input text "+str);  	

	        } catch (IOException e) { 
	            // TODO Auto-generated catch block             
	            e.printStackTrace();            
	            Log.i("run",e.toString());                                            
	        				}    
	    }  
	  static 	private void showToast(String str){ 
    	    Toast.makeText(null, str, Toast.LENGTH_SHORT).show();}
    	

	static{
		//System.loadLibrary("Cilico-Scan");    
		System.loadLibrary("tiny-tools");  
	}	

}
