//System.out.print("\n\n onDisconnect \n------------------------------------\n");
package com.cehis.wms.module;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;


import com.wowza.wms.application.*;
import com.wowza.wms.amf.*;
import com.wowza.wms.client.*;
import com.wowza.wms.module.*;
import com.wowza.wms.request.*;
import com.wowza.wms.stream.*;
import com.wowza.wms.vhost.IVHost;
import com.wowza.wms.rtp.model.*;
import com.wowza.wms.httpstreamer.model.*;
import com.wowza.wms.httpstreamer.cupertinostreaming.httpstreamer.*;
import com.wowza.wms.httpstreamer.smoothstreaming.httpstreamer.*;


public class Pandora_Stats extends ModuleBase 
{
	public void doSomething(IClient client, RequestFunction function,
			AMFDataList params) {
		getLogger().info("doSomething");
		sendResult(client, params, "Hello Wowza");
	}
	
	public void onConnectAccept(IClient client) throws IOException 
	{
		getLogger().info("onConnectAccept: " + client.getClientId());
		

		Conexion cliente = new Conexion();
		IApplication Aplicacion = client.getApplication();
		java.util.List streams = client.getAppInstance().getPublishStreamNames();
		
		cliente.Aplicacion = Aplicacion.getName();
		cliente.Client_Id = client.getClientId();
		cliente.Concurrencias = client.getAppInstance().getStreamCount(); 
		cliente.IOtype = "in";
		cliente.Ip = client.getIp();
		cliente.TiempoCorriendo = (double) 0;
		cliente.URL = client.getPageUrl();
		cliente.UserAgent = client.getFlashVer();

		if (!streams.isEmpty())
		{
			cliente.TipoConexion = "Web";
			cliente.Stream = streams.get(0).toString();
			java.util.List obj = client.getPublishStreams();
			//String ubj = obj.get(0).toString();;
		}
		else
		{
			cliente.TipoConexion = "Encoder";
			cliente.Stream = "NULL";
		}
		ConectarDesconectar(cliente, client.getAppInstance().getProperties().getPropertyStr("broncoURL"));
	}

	public void onConnectReject(IClient client) 
	{
		getLogger().info("onConnectReject: " + client.getClientId());
	}
		
	public void onDisconnect(IClient client) throws IOException 
	{
		getLogger().info("onDisconnect: " + client.getClientId());

		Conexion cliente = new Conexion();
		IApplication Aplicacion = client.getApplication();
		java.util.List streams = client.getAppInstance().getPublishStreamNames();
		
		cliente.Aplicacion = Aplicacion.getName();
		cliente.Client_Id = client.getClientId();
		cliente.Concurrencias = client.getAppInstance().getStreamCount(); 
		cliente.IOtype = "out";
		cliente.Ip = client.getIp();
		cliente.TiempoCorriendo = client.getTimeRunningSeconds();
		cliente.URL = client.getPageUrl();
		cliente.UserAgent = client.getFlashVer();

		if (!streams.isEmpty())
		{
			cliente.TipoConexion = "Web";
			cliente.Stream = streams.get(0).toString();
		}
		else
		{
			cliente.TipoConexion = "Encoder";
			cliente.Stream = "NULL";
		}
		
		ConectarDesconectar(cliente, client.getAppInstance().getProperties().getPropertyStr("broncoURL"));
	}

	public void onHTTPSessionCreate(IHTTPStreamerSession httpSession) throws IOException 
	{
		getLogger().info("onHTTPSessionCreate: " + httpSession.getSessionId());
		
		Conexion cliente = new Conexion();
		IApplication Aplicacion = httpSession.getAppInstance().getApplication();
		java.util.List streams = httpSession.getAppInstance().getPublishStreamNames();
		
		cliente.Aplicacion = Aplicacion.getName();
		cliente.Client_Id = httpSession.getSessionId();
		cliente.Concurrencias = httpSession.getAppInstance().getStreamCount(); 
		cliente.IOtype = "in";
		cliente.Ip = httpSession.getIpAddress();
		cliente.Stream = streams.get(0).toString();
		cliente.TiempoCorriendo = (double)0;
		cliente.URL = "";
		cliente.UserAgent = httpSession.getUserAgent();
		cliente.TipoConexion = "http";
		
		ConectarDesconectar(cliente, httpSession.getAppInstance().getProperties().getPropertyStr("broncoURL"));
	}

	public void onHTTPSessionDestroy(IHTTPStreamerSession httpSession) throws IOException 
	{
		getLogger().info("onHTTPSessionDestroy: " + httpSession.getSessionId());
		
		Conexion cliente = new Conexion();
		IApplication Aplicacion = httpSession.getAppInstance().getApplication();
		java.util.List streams = httpSession.getAppInstance().getPublishStreamNames();
		
		cliente.Aplicacion = Aplicacion.getName();
		cliente.Client_Id = httpSession.getSessionId();
		cliente.Concurrencias = httpSession.getAppInstance().getStreamCount(); 
		cliente.IOtype = "out";
		cliente.Ip = httpSession.getIpAddress();
		cliente.Stream = streams.get(0).toString();
		cliente.TiempoCorriendo = httpSession.getTimeRunningSeconds();
		cliente.URL = "";
		cliente.UserAgent = httpSession.getUserAgent(); 
		cliente.TipoConexion = "http";
		
		ConectarDesconectar(cliente, httpSession.getAppInstance().getProperties().getPropertyStr("broncoURL"));
	}

	public void onRTPSessionCreate(RTPSession rtpSession) throws IOException 
	{
		getLogger().info("onRTPSessionCreate: " + rtpSession.getSessionId());
		
		Conexion cliente = new Conexion();
		IApplication Aplicacion = rtpSession.getAppInstance().getApplication();
		java.util.List streams = rtpSession.getAppInstance().getPublishStreamNames();
		
		cliente.Aplicacion = Aplicacion.getName();
		cliente.Client_Id = rtpSession.getSessionId();
		cliente.Concurrencias = rtpSession.getAppInstance().getStreamCount(); 
		cliente.IOtype = "in";
		cliente.Ip = rtpSession.getIp();
		cliente.Stream = streams.get(0).toString();
		cliente.TiempoCorriendo = (double)0;
		cliente.URL = "";
		cliente.UserAgent = rtpSession.getUserAgent();
		cliente.TipoConexion = "rtsp";
		
		ConectarDesconectar(cliente, rtpSession.getAppInstance().getProperties().getPropertyStr("broncoURL"));
	}

	public void onRTPSessionDestroy(RTPSession rtpSession) throws IOException 
	{
		getLogger().info("onRTPSessionDestroy: " + rtpSession.getSessionId());
		
		Conexion cliente = new Conexion();
		IApplication Aplicacion = rtpSession.getAppInstance().getApplication();
		java.util.List streams = rtpSession.getAppInstance().getPublishStreamNames();
		
		cliente.Aplicacion = Aplicacion.getName();
		cliente.Client_Id = rtpSession.getSessionId();
		cliente.Concurrencias = rtpSession.getAppInstance().getStreamCount(); 
		cliente.IOtype = "out";
		cliente.Ip = rtpSession.getIp();
		cliente.Stream = streams.get(0).toString();
		cliente.TiempoCorriendo = rtpSession.getTimeRunningSeconds();
		cliente.URL = "";
		cliente.UserAgent = rtpSession.getUserAgent();
		cliente.TipoConexion = "rtsp";
		
		ConectarDesconectar(cliente, rtpSession.getAppInstance().getProperties().getPropertyStr("broncoURL"));
	}
		
	public void ConectarDesconectar(Conexion client, String Servidor) throws IOException 
	{
		String Parametros = "";
		if (client.IOtype == "out")
		{
			client.Concurrencias--;
		}
				Calendar d = Calendar.getInstance();
				String dia = Integer.toString(d.get(Calendar.DATE));
				String mes = Integer.toString(d.get(Calendar.MONTH)+1);
				String annio = Integer.toString(d.get(Calendar.YEAR));
				String hora = Integer.toString(d.get(Calendar.HOUR_OF_DAY));
				String minuto = Integer.toString(d.get(Calendar.MINUTE));
				String segundos = Integer.toString(d.get(Calendar.SECOND));
				String Fecha = annio + "-" + mes + "-" + dia;
				String Hora = hora + ":" + minuto + ":" + segundos;
				
				Parametros = "Id=:=NULL" +
						",!,Ip=:=" + client.Ip +
						",!,client_id=:=" + client.Client_Id + 
						",!,Aplicacion=:=" + client.Aplicacion +
						",!,Stream=:=" + client.Stream +
						",!,Fecha=:=" + Fecha + 
						",!,Hora=:=" + Hora + 
						",!,Concurrencias=:=" + client.Concurrencias + 
						",!,IO=:=" + client.IOtype +
						",!,TiempoCorriendo=:=" + client.TiempoCorriendo +
						",!,UserAgent=:=" + client.UserAgent + 
						",!,URL=:=" + client.URL +
						",!,TipoConexion=:=" + client.TipoConexion;
				
				EnviaraServidor(Servidor, "php/pandora", Parametros);
	}
	
	public void EnviaraServidor(String Servidor, String ArchivoPHP, String Parametros) throws IOException
	{
		PeticionPost post = new PeticionPost(Servidor + ArchivoPHP + ".php");
		String[] arrayParametros = Parametros.split(",!,");
		
		for (int i = 0; i < arrayParametros.length; i++) 
		{
			String[] arrayParametro = arrayParametros[i].split("=:=");
			if (arrayParametro.length > 1)
			{
				post.add(arrayParametro[0], arrayParametro[1]);
			}
			else
			{
				post.add(arrayParametro[0], "NULL");
			}
		}
		String respuesta = post.getRespueta();
	}
}