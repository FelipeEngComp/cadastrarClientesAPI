package com.felipe.uolChallenge.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.felipe.uolChallenge.client.LocationByCoordinates;
import com.felipe.uolChallenge.client.LocationByIpCliente;
import com.felipe.uolChallenge.client.LocationByWoiedAndDate;
import com.felipe.uolChallenge.domain.CityInformation;
import com.felipe.uolChallenge.domain.DadosDoCadastrante;
import com.felipe.uolChallenge.domain.DadosTemperatura;
import com.felipe.uolChallenge.domain.IpVigilante;


public class Aplicacao {
	@Test
	public DadosDoCadastrante getInformacoesDoCadastrante() throws IOException  {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					whatismyip.openStream()));
			String  ip = in.readLine();

			LocationByIpCliente location = new LocationByIpCliente("https://ipvigilante.com/json/", ip);
			IpVigilante ipVigilante = location.listar();
			//recolhe informacoes sobre a cidade ou cidade mais proxima...
			CityInformation cityInformation= getCityOrNextCityByLatAndLong(ipVigilante.getData().getLatitude(),
					ipVigilante.getData().getLongitude());
			
			//recolhe informacoes da temperatura...
			DadosTemperatura dadosTemperatura = getTemInformationByWoeidAndDate(cityInformation.getWoeid(),
												cityInformation.getTitle());
			
			DadosDoCadastrante dadosDoCadastrante =  new DadosDoCadastrante();
			dadosDoCadastrante.setCidade(cityInformation.getTitle());
			dadosDoCadastrante.setMin_temp(dadosTemperatura.getMin_temp());
			dadosDoCadastrante.setMax_temp(dadosTemperatura.getMax_temp());
			
			
			return dadosDoCadastrante;
			
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private CityInformation getCityOrNextCityByLatAndLong(String latitude, String longitude) {
		String url = "https://www.metaweather.com/";
		String urn = "api/location/search/?lattlong="+latitude+","+longitude;
		System.out.println(url+urn);
		LocationByCoordinates location = new LocationByCoordinates(url, urn);
		
		List<CityInformation> cityInformations = location.listar();
		
		return cityInformations.get(0);
			
	}

	private DadosTemperatura getTemInformationByWoeidAndDate(Long woeid, String city) {
		Calendar c = Calendar.getInstance();
		String dataAtual = c.get(Calendar.YEAR)+"/"+c.get(Calendar.MONTH)+1+"/"+c.get(Calendar.DAY_OF_MONTH);
		
		String url = "https://www.metaweather.com/";
		String urn = "api/location/"+woeid+"/"+dataAtual;
		System.out.println(url+urn);
		LocationByWoiedAndDate locationByWoiedAndDate = new LocationByWoiedAndDate(url, urn);
		List<DadosTemperatura> dadosTemperatura = locationByWoiedAndDate.listar();
		return dadosTemperatura.get(0);
//		System.out.println(dadosTemperatura.get(0).getMax_temp());
//		System.out.println(dadosTemperatura.get(0).getMin_temp());
	}
}

