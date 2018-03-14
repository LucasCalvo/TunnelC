package com.tinamica.tunnelc.publisherdata.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinamica.tunnelc.global.beans.Ack;
import com.tinamica.tunnelc.global.beans.railwayincidences.TrackTracerGeoIncidences;
import com.tinamica.tunnelc.publisherdata.data.TCBigQueryWrapper;
import com.tinamica.tunnelc.publisherdata.exceptions.TCEDCPublisherDataServiceError;
import com.tinamica.tunnelc.publisherdata.exceptions.TCEDCPublisherDataServiceException;

@Service
public class ProxyService {
	
    private StringBuffer sb;

    private static String _component = "[ProxyService]";

    /** Logger Object **/
    private static Logger _logger = LoggerFactory.getLogger(ProxyService.class);

    @Autowired
    private TCBigQueryWrapper tcBigQueryWrapper;
    
    public Ack consolidateBigQuery(List<TrackTracerGeoIncidences> trackTracerGeoIncidences) throws TCEDCPublisherDataServiceException {
        Ack ack = null;
        try {
            _logger.info("[........TCEDCPublisherDataService:ProxyService:consolidateBigQuery .......Init Sequence]");
            for(int i=0;i<trackTracerGeoIncidences.size();i++) {
            	 ack = tcBigQueryWrapper.consolidateBigQuery(trackTracerGeoIncidences.get(i));
            }
           
            _logger.info("[........TCEDCPublisherDataService:ProxyService:consolidateBigQuery .......Ends Sequence]");
        }
        catch (TCEDCPublisherDataServiceException fdce){
            throw fdce;
        }
        catch (Exception e){
            _logger.error("ERROR .: " + e.fillInStackTrace());
            sb = new StringBuffer();
            TCEDCPublisherDataServiceError error = new TCEDCPublisherDataServiceError(this.getClass().getName(), String.valueOf(TCEDCPublisherDataServiceException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new TCEDCPublisherDataServiceException(error, _component+"[01]", "[consolidateBigQuery]", TCEDCPublisherDataServiceException.ERROR, e);
        }
        return ack;
    }
}
