package com.tinamica.tunnelc.publisherdata.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tinamica.tunnelc.global.beans.Ack;
import com.tinamica.tunnelc.global.beans.TCMetricsBean;
import com.tinamica.tunnelc.global.beans.railwayincidences.TrackTracerGeoIncidences;
import com.tinamica.tunnelc.global.metrics.TCMetricsProxyService;
import com.tinamica.tunnelc.publisherdata.endpoint.ProxyService;
import com.tinamica.tunnelc.publisherdata.exceptions.TCEDCPublisherDataServiceError;
import com.tinamica.tunnelc.publisherdata.exceptions.TCEDCPublisherDataServiceException;
import com.tinamica.tunnelc.publisherdata.utils.ServiceUtils;

import io.swagger.annotations.ApiOperation;

@Controller
public class ServiceController {

	private static Logger _logger = Logger.getLogger(ServiceController.class);
	private StringBuffer sb;
	private static final String JSON = "application/json";


	private static TCMetricsProxyService tcMetricsProxyService;
	private static TCMetricsBean tcMetricsBean;
	private static final String _COMPONENT = "[Controller]";
	
	@Autowired
	ProxyService proxyService;

	@ApiOperation(value = "Publisher data in BigQuery")
	@RequestMapping(value = "/TChubedc/consolidateData/", method = RequestMethod.POST, produces = { JSON })
	public @ResponseBody Ack consolidateData(@RequestBody byte[] trackTracerGeoIncidences) throws TCEDCPublisherDataServiceException {
		Ack ack = null;
		List<TrackTracerGeoIncidences> bean = new ArrayList<TrackTracerGeoIncidences>();
		try {
			_logger.info(
					"[........TCEDCPublisherData:ServiceController:consolidateData .......Init Sequence]");
			long ini = ServiceUtils.getTimeCurrentMilis();
			JSONArray jsonArray = new JSONArray(new String(trackTracerGeoIncidences));
            for(int i=0; i < jsonArray.length(); i++) {
            	bean.add(new Gson().fromJson(jsonArray.get(i).toString(), TrackTracerGeoIncidences.class));
            	ack = proxyService.consolidateBigQuery(bean);
            }

			long fin = ServiceUtils.getTimeCurrentMilis();
			tcMetricsBean = new TCMetricsBean();
			tcMetricsBean.setComponent(_COMPONENT);
			tcMetricsBean.setFecCreateMetric(ServiceUtils.getDateString());
			tcMetricsBean.setDataCenter("[EDC]");
			tcMetricsBean.setFlowData("TCEDCPublisherIncidenceService - Consolidate");
			tcMetricsBean.setService("ConsolidateIncidence");
			long tResponse = fin - ini;
			tcMetricsBean.setTimeResponse(String.valueOf(tResponse));
			tcMetricsBean.setObservation("[Time Response : Events Catalog - Initial Load Process.]");
			tcMetricsProxyService = TCMetricsProxyService.getTCMetricsProxyService();
			tcMetricsProxyService.consolidateMetricsProcess(tcMetricsBean);
			_logger.info(
					"[........TCEDCPublisherData:ServiceController:consolidateData .......End Sequence]");

			_logger.info(ack.toString());
		} catch (TCEDCPublisherDataServiceException fdce) {
			throw fdce;
		} catch (Exception e) {
			_logger.error("ERROR .: " + e.fillInStackTrace());
			sb = new StringBuffer();
			TCEDCPublisherDataServiceError error = new TCEDCPublisherDataServiceError(this.getClass().getName(),
					String.valueOf(TCEDCPublisherDataServiceException.ERROR),
					sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
			throw new TCEDCPublisherDataServiceException(error, _COMPONENT + "[01]", "[consolidateData]",
					TCEDCPublisherDataServiceException.ERROR, e);
		}
		return ack;
	}
}
